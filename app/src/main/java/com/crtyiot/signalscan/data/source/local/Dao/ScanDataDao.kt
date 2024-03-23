package com.crtyiot.signalscan.data.source.local.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.crtyiot.signalscan.data.source.local.model.ScanData

@Dao
interface ScanDataDao {
    @Query("SELECT * FROM scan_data")
    fun getAll(): Flow<List<ScanData>>

    @Insert
    suspend fun insert(scanData: ScanData)

    @Delete
    suspend fun delete(scanData: ScanData)
}