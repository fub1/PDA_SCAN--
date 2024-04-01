package com.crtyiot.signalscan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.crtyiot.signalscan.data.repository.ScanDataRepository
import com.crtyiot.signalscan.data.AppDatabase
import com.crtyiot.signalscan.ui.screen.ScanScreen
import com.crtyiot.signalscan.ui.screen.ScanViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ScanScreen()
        }
    }
}