package com.crtyiot.signalscan
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.crtyiot.signalscan.ui.screen.ScanScreen
import com.crtyiot.signalscan.ui.screen.ScanViewModel


class MainActivity : ComponentActivity() {
    // 实例化VM

    private val scanViewModel by viewModels<ScanViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScanScreen(viewModel = scanViewModel)
        }
    }


}

