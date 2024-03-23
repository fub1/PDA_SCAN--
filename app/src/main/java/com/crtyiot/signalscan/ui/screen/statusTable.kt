package com.crtyiot.signalscan.ui.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.crtyiot.signalscan.R


@Composable
fun StatusDataGrid(viewModel: ScanViewModel) {
    val headers = listOf(
        stringResource(id = R.string.CMS_MAT_CODE),
        stringResource(id = R.string.VDA_MAT_CODE),
        stringResource(id = R.string.VDA_PKG_CODE)
    )

    val scanGridRow = viewModel.scanResultlist.collectAsState().value



    Box() {
        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
            //表头
            items(headers) { header ->
                Box(
                    modifier = Modifier
                        .padding(1.dp)
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.primaryContainer,
                            MaterialTheme.shapes.small
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = header)
                }
            }

            //表格内容
            items(scanGridRow) { row ->
                Box(
                    modifier = Modifier
                        .padding(1.dp)
                        ,
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = row)
                }
            }
        }
    }
}

