package com.crtyiot.signalscan.ui.screen

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.crtyiot.signalscan.R
import androidx.lifecycle.viewmodel.compose.viewModel
import com.crtyiot.signalscan.ui.utils.ScanReceiver

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanScreen() {
    val context = LocalContext.current

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
                        IconButton(onClick = { triggerVibrate(context) }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Localized description"
                            )
                        }
                    },

                    actions = {
                        IconButton(onClick = { triggerVibrate(context)}) {
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
                    .padding(2.dp)
                    .fillMaxWidth()
                    .offset(y = 60.dp)

            ){
                SimpleFilledTextFieldSample()

            }

        }

    }




}


// only test UI
@Composable
fun ScrollContentCopy(innerPadding: PaddingValues) {
    val range = 1..100

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),

        contentPadding = innerPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(range.count()) { index ->
            Text(text = "- List item number ${index + 1}")
        }
    }
}


@Preview
@Composable
fun PreviewScanScreen(){
    ScanScreen()
}

@Composable
fun SimpleFilledTextFieldSample(scanViewModel: ScanViewModel = viewModel()) {

    // 从ViewModel中注册state-扫描数据
    // todo:使用combine转换函数将所有StateFlow合并为一个Flow，
    //  这个Flow会在任何一个StateFlow的值改变时发射一个包含所有当前值的List
    val text1 by scanViewModel.scanDatalist[0].collectAsState()
    val text2 by scanViewModel.scanDatalist[1].collectAsState()
    val text3 by scanViewModel.scanDatalist[2].collectAsState()
    val text4 by scanViewModel.scanDatalist[3].collectAsState()

    // 从ViewModel中注册state-扫描步骤
    val scanStep by scanViewModel.scanStep.collectAsState()
    // 广播接收器内容容器
    val context_barcode = LocalContext.current
    // 注册扫码状态
    val scanstatus by scanViewModel.scanstatus.collectAsState()

    // 在这里注册BroadcastReceiver

    // 只有在扫码状态下才注册
        val receiver = ScanReceiver.register(context_barcode) {
            data ->
            scanViewModel.addScanData(data)
        }
        DisposableEffect(context_barcode) {
            onDispose {
                context_barcode.unregisterReceiver(receiver)
            }
        }



    // TODo：需要进一步封装
    InputTextField(value = text1, label = stringResource(id = R.string.CMS_MAT_Series))
    InputTextField(value = text2, label = stringResource(id = R.string.CMS_MAT_Series))
    InputTextField(value = text3, label = stringResource(id = R.string.VDA_MAT_CODE))
    InputTextField(value = text4, label = stringResource(id = R.string.VDA_PKG_CODE) )
    Button(onClick = { scanViewModel.resetScanData() }) {
        Text(text = "Reset")

    }



}

// 函数来触发震动
fun triggerVibrate(    context: Context) {

    val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        // TODO：需要更新到协程中调用，否则会影响界面交互
        Thread.sleep(1199)
        val effect = VibrationEffect.createOneShot(120, VibrationEffect.DEFAULT_AMPLITUDE)
        vibrator.vibrate(effect)
        Thread.sleep(180)
        vibrator.vibrate(effect)
        Thread.sleep(180)
        vibrator.vibrate(effect)
    } else {
        @Suppress("DEPRECATION")
        vibrator.vibrate(70)
    }
}

@Composable
fun InputTextField(
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    TextField(
        enabled = false,
        value = value,
        onValueChange = {} ,
        label = { Text(label) },
        maxLines = 1,
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
    )
}
