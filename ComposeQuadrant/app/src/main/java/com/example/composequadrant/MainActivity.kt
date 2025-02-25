package com.example.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeQuadrantTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column (modifier = modifier.fillMaxHeight()) {
        Row(modifier = Modifier.weight(1f)) {
            Card(
                "Text",
                stringResource(R.string.text_desc),
                modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFFEADDFF))
            )
            Card(
                "Image",
                stringResource(R.string.image_desc),
                modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFFD0BCFF))
            )
        }
        Row(modifier = Modifier.weight(1f)) {
            Card(
                "Row",
                stringResource(R.string.row_desc),
                modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFFB69DF8))
            )
            Card(
                "Column",
                stringResource(R.string.column_desc),
                modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFFF6EDFF))
            )
        }
    }
}


@Composable
fun Card(name: String, desc: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "$name composable",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)//.background(Color.Green)
        )
        Text(
            text = desc,
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeQuadrantTheme {
        Greeting("Android")
    }
}