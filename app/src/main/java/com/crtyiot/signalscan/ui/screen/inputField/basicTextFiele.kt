package com.crtyiot.signalscan.ui.screen.inputField

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BasicTextField(
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