package com.jrmitchell.cube.investigation.jitpack.modular.test.populator.retrofit_gson

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.abstract.DisplayPopulator
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.abstract.DisplayTarget
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.abstract.LoadingIndicator
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.ActionData
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.ButtonData
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.DisplayData
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.ImageData
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitGsonPopulator(val baseUrl: String, val coroutineScopeGetter: () -> CoroutineScope, val uriSuffix: String = "", val displayDataCache: DisplayDataCache? = null) : DisplayPopulator {
	
	interface DisplayDataCache {
		suspend fun set(key: String?, value: DisplayData)
		suspend fun get(key: String?): DisplayData?
	}
	
	constructor(baseUrl: String, coroutineScopeGetter: () -> CoroutineScope, uriSuffix: String, cacheMap: MutableMap<String?, DisplayData>) :
			this(baseUrl, coroutineScopeGetter, uriSuffix,
				object : DisplayDataCache {
					override suspend fun set(key: String?, value: DisplayData) = cacheMap.set(key, value)
					override suspend fun get(key: String?): DisplayData? = cacheMap[key]
				})
	
	companion object {
		private fun jsonObjectToImageData(obj: JsonObject): ImageData {
			val width = obj.get("width").asInt
			val height = obj.get("height").asInt
			val uri = obj.get("uri").asString
			return ImageData(width, height, uri)
		}
		
		private fun jsonElementToButtonData(obj: JsonElement) = obj.asJsonObject.let {
			ButtonData(
				it.get("buttonText").asString,
				ActionData(it.get("actionData").asJsonObject.get("actionId").asString)
			)
		}
		
		private fun jsonObjectToDisplayData(elem: JsonElement?): DisplayData {
			val obj = elem!!.asJsonObject
			val imageData = obj.getAsJsonObject("image").let(this::jsonObjectToImageData)
			val buttonData = obj.getAsJsonArray("buttonData").map(this::jsonElementToButtonData)
			return DisplayData(
				imageData,
				obj.get("imageContentDescription").asString,
				obj.get("titleText").asString,
				buttonData
			)
		}
	}
	
	val api: API
		get() {
			val okHttpClient = OkHttpClient()
				.newBuilder()
				.build()
			return Retrofit.Builder()
				.client(okHttpClient)
				.baseUrl(baseUrl)
				.addConverterFactory(GsonConverterFactory.create())
				.build()
				.create(API::class.java)
		}
	
	private fun coroutineExceptionHandler(pageUri: String?, target: DisplayTarget, loadingIndicator: LoadingIndicator) = CoroutineExceptionHandler { _, throwable ->
		coroutineScopeGetter().launch(Dispatchers.Main + CoroutineExceptionHandler { _, throwable ->
			loadingIndicator.setLoadingState(false)
			target.displayError(throwable)
		}) {
			val cachedVal = displayDataCache?.get(pageUri)
			loadingIndicator.setLoadingState(false)
			if (cachedVal != null) {
				target.displayData(cachedVal)
			}
			else {
				target.displayError(throwable)
			}
		}
	}
	
	override fun populateDisplayFromUri(pageUri: String?, target: DisplayTarget, loadingIndicator: LoadingIndicator) {
		loadingIndicator.setLoadingState(true)
		coroutineScopeGetter().launch(Dispatchers.Main + coroutineExceptionHandler(pageUri, target, loadingIndicator)) {
			if (pageUri.isNullOrBlank()) {
				throw Throwable()
			}
			val data = withContext(Dispatchers.IO) {
				val response = api.getContentByLink(pageUri + uriSuffix)
				jsonObjectToDisplayData(response)
			}
			loadingIndicator.setLoadingState(false)
			target.displayData(data)
			displayDataCache?.set(pageUri, data.copy(titleText = data.titleText + " (from cache)"))
		}
	}
}