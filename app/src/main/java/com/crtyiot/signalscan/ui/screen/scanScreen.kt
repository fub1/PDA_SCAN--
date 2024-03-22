package com.crtyiot.signalscan.ui.screen


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.crtyiot.signalscan.R
import com.crtyiot.signalscan.ui.screen.inputField.CmsMatField
import com.crtyiot.signalscan.ui.screen.inputField.VdaMatField
import com.crtyiot.signalscan.ui.screen.inputField.VdaPkgField


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanScreen(viewModel: ScanViewModel) {
    val isScanning = viewModel.scanning.collectAsState().value
    val scanstepindex by viewModel.scanstepindex.collectAsState()

    Surface(modifier = Modifier
        .fillMaxWidth()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(
                            text = stringResource(R.string.scanning)
                        )
                    },

                    navigationIcon = {
                        IconButton(onClick = { /* TODO:*/  }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Localized description"
                            )
                        }
                    },

                    actions = {
                        IconButton(onClick = { /* TODO:*/ }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Localized description"
                            )
                        }
                    },

                    )
            },
        ) { innerPadding ->
            Column (
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth()

            ){


                StatusCard(viewModel = viewModel)

                Spacer(Modifier.height(2.dp))

                StatusDataGrid(viewModel = viewModel)

                if (scanstepindex == 0 ) {
                    CmsMatField(viewModel = viewModel)
                } else if (scanstepindex == 1) {
                    VdaMatField(viewModel = viewModel)
                } else if (scanstepindex == 2) {
                    VdaPkgField(viewModel = viewModel)
                }


                Row(
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                    ,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (scanstepindex == 3) {
                        Button(
                            onClick = { viewModel.submitScanData() },
                            modifier = Modifier.width(IntrinsicSize.Max)
                        ) {
                            Text("提交")
                        }

                        Button(
                            onClick = { viewModel.resetScanData() },
                            modifier = Modifier.width(IntrinsicSize.Max)
                        ) {
                            Text("重置")
                        }


                    }

                    if (scanstepindex < 3) {

                        Button(
                            onClick = { viewModel.resetScanData() },
                            modifier = Modifier.fillMaxWidth(0.5f).width(IntrinsicSize.Max)
                        ) {
                            Text("重置")
                        }

                    }
                }
            }

        }

    }

}

