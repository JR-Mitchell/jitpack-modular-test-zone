package com.jrmitchell.cube.investigation.jitpack.modular.test.populator.room_retrofit_gson

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
/**
 * Room Data Access Object implementation for storing and retrieving display data for a given page
 */
interface DisplayDataDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertData(displayDataEntity: DisplayDataEntity)
	
	@Query("SELECT * FROM DisplayDataEntity WHERE page_uri=:pageUri")
	suspend fun getData(pageUri: String): DisplayDataEntity?
}