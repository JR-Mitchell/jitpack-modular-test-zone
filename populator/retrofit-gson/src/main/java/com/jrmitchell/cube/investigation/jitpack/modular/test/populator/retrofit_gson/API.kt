package com.jrmitchell.cube.investigation.jitpack.modular.test.populator.retrofit_gson

import com.google.gson.JsonElement
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit interface for getting a [JsonElement] from a page
 */
interface API {
	@GET("{url}")
	suspend fun getContentByLink(@Path("url") url: String): JsonElement
}