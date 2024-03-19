package com.crtyiot.signalscan.ui.screen

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.crtyiot.signalscan.R


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
fun SimpleFilledTextFieldSample() {
    var text1 by remember { mutableStateOf("12") }
    var text2 by remember { mutableStateOf("34") }
    var text3 by remember { mutableStateOf("34") }
    var text4 by remember { mutableStateOf("34") }
    TextField(
        enabled = false,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        value = text1,
        onValueChange = { text1 = it },
        maxLines = 1,
        label = { Text("CMS MAT CODE") }
    )

    TextField(
        enabled = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        value = text2,
        maxLines = 1,
        onValueChange = { text2 = it },
        label = { Text("CMS Label Barcode") }
    )

    TextField(
        enabled = false,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        value = text3,
        maxLines = 1,
        onValueChange = { text3 = it },
        label = { Text("VDA Mat Code") }
    )

    TextField(
        enabled = false,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        value = text4,
        maxLines = 1,
        onValueChange = { text4 = it },
        label = { Text("VDA BarCode") }
    )



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
