package com.example.basicstatecodelab

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicstatecodelab.ui.theme.BasicStateCodelabTheme

//@Composable
//fun WellnessTaskItem(
//    text: String,
//    onClose: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    var checkedState by rememberSaveable { mutableStateOf(false) }
//    WellnessTaskItem(
//        text = text,
//        checked = checkedState,
//        onCheckedChange = {checkedState = it},
//        onClose = onClose,
//        modifier
//    )
//}

@Composable
fun WellnessTaskItem(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row (modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(text = text, modifier = Modifier.weight(1f).padding(start = 16.dp))
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        IconButton(onClick = onClose) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WellnessTaskItemPreview() {
    BasicStateCodelabTheme {
        WellnessTaskItem(
            text = "Task N",
            checked = false,
            onCheckedChange = {},
            onClose = {}
            )
    }
}
