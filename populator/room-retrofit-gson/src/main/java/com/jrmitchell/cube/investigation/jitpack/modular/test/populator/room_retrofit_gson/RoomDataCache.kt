package com.jrmitchell.cube.investigation.jitpack.modular.test.populator.room_retrofit_gson

import android.content.Context
import androidx.room.Room
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.DisplayData
import com.jrmitchell.cube.investigation.jitpack.modular.test.populator.retrofit_gson.RetrofitGsonPopulator

/**
 * [RetrofitGsonPopulator.DisplayDataCache] implementation for cacheing display data in a Room database
 *
 * @param context the context to create this cache from
 * @param nullKey the [String] key to use in place of null, as Room cannot handle null primary keys -
 *  this should be something that no page URI is expected to be
 */
class RoomDataCache(context: Context, val nullKey: String = "NULL_PAGE_KEY") : RetrofitGsonPopulator.DisplayDataCache {
	
	private val displayDataDatabase = Room.databaseBuilder(context, DisplayDataDatabase::class.java, "displaydata").build()
	private val buttonDataDatabase = Room.databaseBuilder(context, ButtonDataDatabase::class.java, "buttondata").build()
	
	override suspend fun set(key: String?, value: DisplayData) {
		val displayEntity = DisplayDataEntity(key ?: nullKey, value)
		displayDataDatabase.dao().insertData(displayEntity)
		buttonDataDatabase.dao().deletePreviousData(key ?: nullKey)
		val buttonEntities = value.buttonData.map {
			ButtonDataEntity(key ?: nullKey, it)
		}.toTypedArray()
		buttonDataDatabase.dao().insertData(*buttonEntities)
	}
	
	override suspend fun get(key: String?): DisplayData? {
		val buttons = buttonDataDatabase.dao().getData(key ?: nullKey).map { it.toButtonData() }
		val displayEntity = displayDataDatabase.dao().getData(key ?: nullKey)
		return displayEntity?.toDisplayData(buttons)
	}
}