package com.crtyiot.signalscan.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue


@Composable
fun ScanDataList(viewModel: ScanViewModel) {
    val scanDataList by viewModel.allScanData.collectAsState(initial = emptyList())

    LazyColumn {
        items(scanDataList) { scanData ->
            Row {
                Text(text = scanData.vdaMat)
                Text(text = scanData.cmsMat)
                Text(text = scanData.vdaPkg)
                Text(text = scanData.submitTime)
                Text(text = scanData.taskNumber)

                Button(onClick = {
                    // 提示用户是否删除
                    // 确认后删除对应的数据
                    viewModel.deleteScanData(scanData)
                }) {
                    Text("删除")
                }
            }
        }
    }
}