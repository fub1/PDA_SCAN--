package com.crtyiot.signalscan.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun ScanDataList() {
    val viewModel: ScanViewModel = viewModel(factory = ScanViewModel.Factory)
    val scanDataList by viewModel.allScanData.collectAsState(initial = emptyList())

    LazyColumn {
        item {
            // 添加表头
            Row {
                Text(text = "VDA Mat", modifier = Modifier.weight(1f))
                Divider(color = Color.Gray, modifier = Modifier.width(1.dp))
                Text(text = "CMS Mat", modifier = Modifier.weight(1f))
                Divider(color = Color.Gray, modifier = Modifier.width(1.dp))
                Text(text = "VDA Pkg", modifier = Modifier.weight(1f))
            }
            Divider(color = Color.Gray, thickness = 1.dp)
        }

        items(scanDataList) { scanData ->
            Row {
                Text(text = scanData.vdaMat, modifier = Modifier.weight(1f))
                Divider(color = Color.Gray, modifier = Modifier.width(1.dp))
                Text(text = scanData.cmsMat, modifier = Modifier.weight(1f))
                Divider(color = Color.Gray, modifier = Modifier.width(1.dp))
                Text(text = scanData.vdaPkg, modifier = Modifier.weight(1f))
            }
            Divider(color = Color.Gray, thickness = 1.dp)
        }
    }
}