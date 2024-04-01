package com.crtyiot.signalscan.ui.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.math.roundToLong


@Composable
fun StatusCard() {
    val viewModel: ScanViewModel = viewModel(factory = ScanViewModel.Factory)
    val scaningProcess = ((viewModel.scanstepindex.collectAsState().value).toFloat())/3
    var scanProcesspercent = String.format("%.1f", scaningProcess*100)

    Card {

        LinearProgressIndicator(
        progress = scaningProcess,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
     )
        Spacer(Modifier.height(1.dp))
        Text(
            text = "Scanning Process  $scanProcesspercent %",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        )
    }
}
