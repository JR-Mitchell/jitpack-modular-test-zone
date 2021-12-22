package com.jrmitchell.cube.investigation.jitpack.modular.test.populator.room_retrofit_gson

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
/**
 * Room Data Access Object implementation for storing and retrieving button data for a given page
 */
interface ButtonDataDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertData(vararg buttonDataEntities: ButtonDataEntity)
	
	@Query("DELETE FROM ButtonDataEntity where page_uri=:pageUri")
	suspend fun deletePreviousData(pageUri: String)
	
	@Query("SELECT * FROM ButtonDataEntity where page_uri=:pageUri")
	suspend fun getData(pageUri: String): List<ButtonDataEntity>
}