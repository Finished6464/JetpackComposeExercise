package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceLayout(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

data class Artwork(val title: String, val artist: String, val year: Int, val imageId: Int)

val list = listOf(
    Artwork("Bridge", "zekai zhu", 2021, R.drawable.bridge_zekai_zhu),
    Artwork("Lightning", "guilherme", 2022, R.drawable.lightning_guilherme),
    Artwork("volcano", "clive_kim", 2023, R.drawable.volcano_clive_kim)
)

@Composable
fun ArtSpaceLayout(modifier: Modifier = Modifier) {
    var index by remember { mutableIntStateOf(0) }
    Column (
        modifier = modifier
            .fillMaxWidth()
            .background(Color.DarkGray)
            .padding(10.dp)
            .background(Color.Red),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (
            modifier = Modifier
                .background(Color.Magenta)
                .width(IntrinsicSize.Min)
                .weight(1f),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ArtworkWall(imageResource = list[index].imageId, modifier = Modifier.weight(1f))
            ArtworkDescriptor(
                title = list[index].title,
                artist = list[index].artist,
                year = list[index].year
            )
        }
        DisplayController(
            onPreviousClick = { index = if (index > 0) index - 1 else list.lastIndex},
            onNextClick = { index = if (index < list.lastIndex) index + 1 else 0 },
            modifier = Modifier.padding(top = 10.dp)
            )
    }
}

@Composable
fun ArtworkWall(@DrawableRes imageResource: Int, modifier: Modifier = Modifier) {
    Box (
        modifier = modifier
            .shadow(elevation = 6.dp)
            .background(Color.Blue),
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = null,
            modifier = Modifier.padding(20.dp),
        )
    }
}

@Composable
fun ArtworkDescriptor(title: String, artist: String, year: Int, modifier: Modifier = Modifier) {
    Box (
        modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .background(Color.White)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
            )
            Row {
                Text(
                    text = artist,
                    //                style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = "($year)",
                    //                style = MaterialTheme.typography.bodyLarge,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }
        }
    }
}

@Composable
fun DisplayController(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier.padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = onPreviousClick, modifier.requiredWidth(150.dp)) {
            Text(
                text = "Previous",
//                style = MaterialTheme.typography.bodyLarge
            )
        }
        Spacer(Modifier.weight(1f))
        Button(onClick = onNextClick, modifier.requiredWidth(150.dp)) {
            Text(
                text ="Next",
//                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}
