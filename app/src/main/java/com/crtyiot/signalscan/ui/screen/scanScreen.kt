package com.crtyiot.signalscan.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.crtyiot.signalscan.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanScreen() {
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
                    IconButton(onClick = { /*  TODO */ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },

                actions = {
                    IconButton(onClick = { /* do something */ }) {
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
                .padding(24.dp)
                .fillMaxWidth()

        ){
            SimpleFilledTextFieldSample()

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
    var text1 by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }
    var text3 by remember { mutableStateOf("") }
    var text4 by remember { mutableStateOf("") }
Column (
    modifier = Modifier
        .fillMaxWidth()
        .padding(48.dp)
){
    TextField(
        value = text1,
        onValueChange = { text1 = it },
        label = { Text("CMS MAT CODE") }
    )

    TextField(
        value = text2,
        onValueChange = { text2 = it },
        label = { Text("CMS Series CODE") }
    )

    TextField(
        value = text3,
        onValueChange = { text3 = it },
        label = { Text("VDA MAT CODE") }
    )

    TextField(
        value = text4,
        onValueChange = { text4 = it },
        label = { Text("VDA Serise CODE") }
    )


}



}