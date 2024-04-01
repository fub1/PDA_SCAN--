package com.crtyiot.signalscan.ui.screen.inputField

import android.content.BroadcastReceiver
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.crtyiot.signalscan.R
import com.crtyiot.signalscan.ui.screen.ScanViewModel
import com.crtyiot.signalscan.ui.utils.ScanReceiver

@Composable
fun VdaMatField() {
    val viewModel: ScanViewModel = viewModel(factory = ScanViewModel.Factory)
    // flow注册监听state
    val scandata by viewModel.scanData.collectAsState()
    val scanstepindex by viewModel.scanstepindex.collectAsState()

    // 广播接收器内容容器
    val context_barcode = LocalContext.current

    // 在这里注册BroadcastReceiver
    val receiver = remember { mutableStateOf<BroadcastReceiver?>(null) }

    receiver.value = remember {
        ScanReceiver.register(context_barcode) { data ->
            viewModel.addScanData(data)
            // 扫描后立即销毁receiver
            receiver.value?.let {
                context_barcode.unregisterReceiver(it)
                receiver.value = null
            }
        }
    }

    DisposableEffect(context_barcode) {
        onDispose {
            // 在组件销毁时，也需要销毁receiver
            receiver.value?.let {
                context_barcode.unregisterReceiver(it)
                receiver.value = null
            }
        }
    }


    BasicTextField(value = scandata, label = stringResource(id = R.string.VDA_MAT_CODE))


}


