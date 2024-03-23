package com.crtyiot.signalscan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.crtyiot.signalscan.data.repository.ScanDataRepository
import com.crtyiot.signalscan.data.source.local.AppDatabase
import com.crtyiot.signalscan.ui.screen.ScanScreen
import com.crtyiot.signalscan.ui.screen.ScanViewModel
import com.crtyiot.signalscan.ui.screen.ScanViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var repository: ScanDataRepository
    private lateinit var viewModel: ScanViewModel
    private val scanViewModel by viewModels<ScanViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Instantiate the repository and the ViewModel
        val scanDataDao = AppDatabase.getDatabase(this).scanDataDao()
        repository = ScanDataRepository(scanDataDao)
        val factory = ScanViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[ScanViewModel::class.java]

        setContent {
            ScanScreen(viewModel = scanViewModel)
        }
    }
}