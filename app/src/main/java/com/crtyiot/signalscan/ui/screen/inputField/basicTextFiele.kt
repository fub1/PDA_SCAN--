package com.crtyiot.signalscan.ui.screen.inputField

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign


@Composable
fun BasicTextField(
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    TextField(
        enabled = false,
        value = "请录入条码：",
        onValueChange = {} ,
        label = {
            Text(
                text = label,
                modifier = Modifier
                    .wrapContentSize(),
                style = TextStyle(textAlign = TextAlign.Center),
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                color = MaterialTheme.colorScheme.primary,

            )
                },
        maxLines = 1,
        modifier = modifier
            .fillMaxWidth()
            .height(78.dp)
            ,

    )
}