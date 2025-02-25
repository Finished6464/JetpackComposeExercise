package com.example.basicscodelab

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicscodelab.ui.theme.BasicsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicsCodelabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    if (shouldShowOnboarding) {
        OnboardingScreen(
            onClick = { shouldShowOnboarding = false },
            modifier = modifier
        )
    }
    else {
        Greetings(modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    BasicsCodelabTheme {
        MyApp()
    }
}

@Composable
fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "$it" }
) {
    LazyColumn (modifier = modifier.fillMaxWidth()) {
        items(items = names) { name ->
            Greeting2(name = name)
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "GreetingPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingsPreview() {
    BasicsCodelabTheme {
        Greetings()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var more by rememberSaveable { mutableStateOf(false) }
//    val morePadding by animateDpAsState(
//        targetValue = if (more) 40.dp else 0.dp,
//        animationSpec = spring(
//            dampingRatio = Spring.DampingRatioMediumBouncy,
//            stiffness = Spring.StiffnessMedium
//        )
//    )
//    val moreIcon = rememberVectorPainter(
//        image = if (more) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp
//    )
    val moreIconImage = when (more) {
        true -> Icons.Filled.KeyboardArrowUp
        false -> Icons.Filled.KeyboardArrowDown
    }
    val moreIconDesc = if (more) R.string.show_less else R.string.show_more

    Row(
        modifier = modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(8.dp))
//            .shadow(elevation = 50.dp)
            .background(MaterialTheme.colorScheme.primary)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
            .padding(15.dp),
//            .padding(
//                start = 15.dp,
//                end = 15.dp,
//                bottom = morePadding.coerceAtLeast(0.dp)
//            ),
        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 20.dp)
                .weight(1f)
        ) {
            Text(text = "Hello, ")
            Text(
                text = name,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (more) {
                Text(text = stringResource(R.string.long_string))
            }
        }
//        ElevatedButton(onClick = { more = !more }) {
//            Text(text = "Show ${if (more) "less" else "more"}")
//        }
        IconButton(onClick = { more = !more }, modifier = Modifier.padding(top=10.dp)) {
            Icon(
                imageVector=moreIconImage,
//                painter = rememberVectorPainter(moreIconImage),
                contentDescription = stringResource(moreIconDesc)
            )
        }
    }
}
@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    var more by rememberSaveable { mutableStateOf(false) }
    val moreIconImage = when (more) {
        true -> Icons.Filled.KeyboardArrowUp
        false -> Icons.Filled.KeyboardArrowDown
    }
    val moreIconDesc = if (more) R.string.show_less else R.string.show_more

    Card(
        modifier = modifier.padding(horizontal = 8.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
    ){
        Row(
            modifier = modifier
//                .padding(5.dp)
//                .clip(RoundedCornerShape(8.dp))
//                .background(MaterialTheme.colorScheme.primary)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                ),
//                .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 20.dp)
                    .weight(1f)
            ) {
                Text(text = "Hello, ")
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                if (more) {
                    Text(text = stringResource(R.string.long_string))
                }
            }
            IconButton(onClick = { more = !more }, modifier = Modifier.padding(top = 10.dp)) {
                Icon(
                    imageVector = moreIconImage,
                    contentDescription = stringResource(moreIconDesc)
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() {
    BasicsCodelabTheme {
        Greeting2("Android")
    }
}

@Composable
fun OnboardingScreen(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onClick
        ) {
            Text("Continue")
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    BasicsCodelabTheme {
        OnboardingScreen({})
    }
}

