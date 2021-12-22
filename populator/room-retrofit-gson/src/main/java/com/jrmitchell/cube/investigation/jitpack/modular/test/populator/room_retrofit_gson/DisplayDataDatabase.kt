package com.jrmitchell.cube.investigation.jitpack.modular.test.populator.room_retrofit_gson

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DisplayDataEntity::class], version = 1, exportSchema = false)
/**
 * Room database for storing display data for cached display screens
 */
abstract class DisplayDataDatabase : RoomDatabase() {
	abstract fun dao() : DisplayDataDao
}