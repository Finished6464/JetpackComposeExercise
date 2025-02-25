package com.example.businesscard


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
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
    Column (
        modifier = modifier
//            .fillMaxSize()
            .background(Color(0xFFd2e8d4))
            .padding(20.dp)
    ){
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .weight(3.5f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            val image = painterResource(R.drawable.android_logo)
            Image(
                painter = painterResource(R.drawable.android_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .background(Color(0xFF00695C))
                )
            Text(
                text = "Jennifer Doe",
                fontSize = 50.sp,
            )
            Text(
                text = "Android Developer Extraordinaire",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color(0xFF006d3b)
            )
        }

        Column(
            modifier = Modifier
//                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .weight(0.5f),
            verticalArrangement = Arrangement.SpaceAround,
        ) {
                ContactInfo(
                    Icons.Rounded.Call,
                    "+11 (123) 4444 555 666",
                )
                ContactInfo(
                    Icons.Rounded.Share,
                    "@AndroidDev"
                )
                ContactInfo(
                    Icons.Rounded.Email,
                    "jen.doe@android.com"
                )
            }

    }
}

@Composable
fun ContactInfo(ico: ImageVector, txt: String) {
    Row {
        Icon(
            painter = rememberVectorPainter(image = ico),
            contentDescription = null,
            tint = Color(0xFF006d3b),
            modifier = Modifier.padding(end = 20.dp)
        )
        Text(
            text = txt,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
        Greeting("Android")
    }
}