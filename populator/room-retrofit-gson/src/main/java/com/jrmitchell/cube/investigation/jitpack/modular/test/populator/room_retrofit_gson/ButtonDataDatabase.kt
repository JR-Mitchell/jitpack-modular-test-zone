package com.jrmitchell.cube.investigation.jitpack.modular.test.populator.room_retrofit_gson

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ButtonDataEntity::class], version = 1, exportSchema = false)
/**
 * Room database for storing button data for cached display screens
 */
abstract class ButtonDataDatabase : RoomDatabase() {
	abstract fun dao() : ButtonDataDao
}