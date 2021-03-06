package cn.modificator.demo.ui.imitate

import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cn.modificator.demo.R
import com.patchself.compose.navigator.PageController

class FlutterDemoController: PageController() {

    override fun getId(): Int {
        return R.id.controller_flutterDemo
    }

    @Composable
    override fun screenContent() {
        var _counter by remember { mutableStateOf(0) }

        Scaffold(topBar = {
            TopAppBar(title = { Text(text = "Welcom to Compose", color = Color.White) }, actions = {
                IconButton(onClick = { navigateBack() }) {
                    Icon(Icons.Filled.Close, tint = Color.White)
                }
            }, elevation = 4.dp, backgroundColor = Color.Blue)
        }, floatingActionButton = {
            FloatingActionButton(onClick = { _counter++ }) {
                Icon(Icons.Default.Add)
            }
        }) {
            Column(verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.fillMaxSize()) {
                Text(
                    text ="You have pushed the button this many times:"
                )
                Text(
                    text ="$_counter"
                )
            }
        }
    }
}