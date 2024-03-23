package com.crtyiot.signalscan.data.repository

import com.crtyiot.signalscan.data.source.local.Dao.ScanDataDao
import com.crtyiot.signalscan.data.source.local.model.ScanData
import kotlinx.coroutines.flow.Flow

class ScanDataRepository(private val scanDataDao: ScanDataDao) {
    val allScanData: Flow<List<ScanData>> = scanDataDao.getAll()

    suspend fun insert(scanData: ScanData) {
        scanDataDao.insert(scanData)
    }

    suspend fun delete(scanData: ScanData) {
        scanDataDao.delete(scanData)
    }
}