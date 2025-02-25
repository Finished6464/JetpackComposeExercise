package com.example.mysoothe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.mysoothe.ui.theme.MySootheTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MySootheTheme {
                val windowSize = currentWindowAdaptiveInfo().windowSizeClass
                when (windowSize.windowWidthSizeClass) {
                    WindowWidthSizeClass.COMPACT -> {
                        MySootheAppPortrait()
                    }
                    WindowWidthSizeClass.EXPANDED -> {
                        MySootheAppLandscape()
                    }
                }
            }
        }
    }
}
//
//@Composable
//fun MySootheApp(
//    windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
//) {
//    when (windowSizeClass.windowWidthSizeClass) {
//        WindowWidthSizeClass.COMPACT -> {
//            MySootheAppPortrait()
//        }
//        WindowWidthSizeClass.EXPANDED -> {
//            MySootheAppLandscape()
//        }
//    }
//}


@Composable
fun MySootheAppPortrait() {
    Scaffold(
        bottomBar = { SootheBottomNavigation()},
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        HomeScreen(Modifier.padding(innerPadding))
    }
}

@Composable
fun MySootheAppLandscape() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Row (modifier = Modifier.padding(innerPadding)) {
            SootheNavigationRail()
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(vertical = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        SearchBar()
        HomeSection(R.string.align_your_body) {
            AlignYourBodyRow()
        }
        HomeSection(R.string.favorite_collections) {
            FavoriteCollectionsGrid()
        }
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .heightIn(min=56.dp),
//        label = { Text("search") },
        placeholder = { Text(stringResource(R.string.placeholder_search)) },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = null)
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
            focusedContainerColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            text=stringResource(title),
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp),
            style = MaterialTheme.typography.titleMedium
            )
        content()
    }
}

data class AlignYourBodyResItem(
    @DrawableRes val imageResourceId: Int,
    @StringRes val descriptionResourceId: Int
)
//
//fun createAlignYourBodyResItemList(): List<AlignYourBodyResItem> {
//    return listOf(
//        AlignYourBodyResItem(R.drawable.ab1_inversions, R.string.ab1_inversions),
//        AlignYourBodyResItem(R.drawable.ab2_quick_yoga, R.string.ab2_quick_yoga),
//        AlignYourBodyResItem(R.drawable.ab3_stretching, R.string.ab3_stretching),
//        AlignYourBodyResItem(R.drawable.ab4_tabata, R.string.ab4_tabata),
//        AlignYourBodyResItem(R.drawable.ab5_hiit, R.string.ab5_hiit),
//        AlignYourBodyResItem(R.drawable.ab6_pre_natal_yoga, R.string.ab6_pre_natal_yoga),
//    )
//}

private val alignYourBodyData = listOf(
    AlignYourBodyResItem(R.drawable.ab1_inversions, R.string.ab1_inversions),
    AlignYourBodyResItem(R.drawable.ab2_quick_yoga, R.string.ab2_quick_yoga),
    AlignYourBodyResItem(R.drawable.ab3_stretching, R.string.ab3_stretching),
    AlignYourBodyResItem(R.drawable.ab4_tabata, R.string.ab4_tabata),
    AlignYourBodyResItem(R.drawable.ab5_hiit, R.string.ab5_hiit),
    AlignYourBodyResItem(R.drawable.ab6_pre_natal_yoga, R.string.ab6_pre_natal_yoga),
)

@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
){
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(alignYourBodyData) { (imageResId, textResId) ->
            AlignYourBodyElement(imageResId, textResId)
        }
    }
}

@Composable
fun AlignYourBodyElement(
    @DrawableRes imageResId: Int,
    @StringRes textResId: Int,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(imageResId),
            contentDescription = null,
            modifier = Modifier.size(88.dp).clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(
            text = stringResource(textResId),
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

data class FavoriteCollectionsCardResItem(
    @DrawableRes val imageResId: Int,
    @StringRes val textResId: Int
)
//
//fun createFavoriteCollectionsCardResItemList(): List<FavoriteCollectionsCardResItem> {
//    return listOf(
//        FavoriteCollectionsCardResItem(
//            R.drawable.fc1_short_mantras,
//            R.string.fc1_short_mantras
//        ),
//        FavoriteCollectionsCardResItem(
//            R.drawable.fc2_nature_meditations,
//            R.string.fc2_nature_meditations
//        ),
//        FavoriteCollectionsCardResItem(
//            R.drawable.fc3_stress_and_anxiety,
//            R.string.fc3_stress_and_anxiety
//        ),
//        FavoriteCollectionsCardResItem(
//            R.drawable.fc4_self_massage,
//            R.string.fc4_self_massage
//        ),
//        FavoriteCollectionsCardResItem(
//            R.drawable.fc5_overwhelmed,
//            R.string.fc5_overwhelmed
//        ),
//        FavoriteCollectionsCardResItem(
//            R.drawable.fc6_nightly_wind_down,
//            R.string.fc6_nightly_wind_down
//        ),
//    )
//}
private val favoriteCollectionsData = listOf(
    FavoriteCollectionsCardResItem(
        R.drawable.fc1_short_mantras,
        R.string.fc1_short_mantras
    ),
    FavoriteCollectionsCardResItem(
        R.drawable.fc2_nature_meditations,
        R.string.fc2_nature_meditations
    ),
    FavoriteCollectionsCardResItem(
        R.drawable.fc3_stress_and_anxiety,
        R.string.fc3_stress_and_anxiety
    ),
    FavoriteCollectionsCardResItem(
        R.drawable.fc4_self_massage,
        R.string.fc4_self_massage
    ),
    FavoriteCollectionsCardResItem(
        R.drawable.fc5_overwhelmed,
        R.string.fc5_overwhelmed
    ),
    FavoriteCollectionsCardResItem(
        R.drawable.fc6_nightly_wind_down,
        R.string.fc6_nightly_wind_down
    ),
)

@Composable
fun FavoriteCollectionsGrid(modifier: Modifier = Modifier) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = modifier.height(168.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(favoriteCollectionsData) {  (imageResId, textResId) ->
            FavoriteCollectionsCard(imageResId, textResId)
        }
    }
}

@Composable
fun FavoriteCollectionsCard(
    @DrawableRes imageResId: Int,
    @StringRes textResId: Int,
    modifier: Modifier = Modifier
) {
    Card (
        modifier = modifier.height(80.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row (
            modifier = Modifier.width(255.dp),
            verticalAlignment = Alignment.CenterVertically
            ) {
            Image(
                painter = painterResource(imageResId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp),
            )
            Text(
                text = stringResource(textResId),
                modifier = Modifier.padding(start = 10.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_home)
                )
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_profile)
                )
            },
            selected = true,
            onClick = {}
        )
    }
}


@Composable
fun SootheNavigationRail(modifier: Modifier = Modifier) {
    NavigationRail(
        modifier = modifier.padding(vertical = 8.dp),
        containerColor = MaterialTheme.colorScheme.background
    ){
        Column (
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(R.string.bottom_navigation_home)
                    )
                },
                selected = true,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(R.string.bottom_navigation_profile)
                    )
                },
                selected = true,
                onClick = {}
            )
        }
    }
}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MySootheTheme {
//        Greeting("Android")
//    }
//}

@Preview(showBackground = true)
@Composable
fun MySootheAppPortraitPreview() {
    MySootheTheme {
        MySootheAppPortrait()
    }
}

@Preview(showBackground = true, widthDp = 640, heightDp = 360)
@Composable
fun MySootheAppLandscapePreview() {
    MySootheTheme {
        MySootheAppLandscape()
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MySootheTheme {
        HomeScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    MySootheTheme {
        SearchBar(modifier = Modifier.padding(8.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeSectionPreview() {
    MySootheTheme {
        HomeSection(R.string.align_your_body) {
            AlignYourBodyRow()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignYourBodyRowPreview() {
    MySootheTheme {
        AlignYourBodyRow()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignYourBodyElementPreview() {
    MySootheTheme {
        AlignYourBodyElement(
            imageResId = R.drawable.ab1_inversions,
            textResId = R.string.ab1_inversions,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavoriteCollectionsGridPreview() {
    MySootheTheme {
        FavoriteCollectionsGrid()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavoriteCollectionsCardPreview() {
    MySootheTheme {
        FavoriteCollectionsCard(
            imageResId = R.drawable.fc2_nature_meditations,
            textResId = R.string.fc2_nature_meditations,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun SootheBottomNavigationPreview() {
    MySootheTheme {
        SootheBottomNavigation()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun SootheNavigationRailPreview() {
    MySootheTheme {
        SootheNavigationRail()
    }
}
