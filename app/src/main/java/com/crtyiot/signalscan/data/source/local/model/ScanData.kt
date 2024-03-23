package com.crtyiot.signalscan.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scan_data")
data class ScanData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val vdaMat: String,
    val cmsMat: String,
    val vdaPkg: String,
    val submitTime: String,
    val taskNumber: String
)