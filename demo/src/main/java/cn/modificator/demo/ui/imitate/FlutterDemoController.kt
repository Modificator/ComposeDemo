package cn.modificator.demo.ui.imitate

import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.compose.state
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.layout.Arrangement
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.fillMaxSize
import androidx.ui.material.FloatingActionButton
import androidx.ui.material.IconButton
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.Add
import androidx.ui.material.icons.filled.ArrowBack
import androidx.ui.material.icons.filled.Close
import androidx.ui.unit.dp
import cn.modificator.demo.PageController
import cn.modificator.demo.R

class FlutterDemoController: PageController() {

    override fun getId(): Int {
        return R.id.controller_flutterDemo
    }

    @Composable
    override fun screenContent() {
        var _counter by state { 0 }

        Scaffold(topBar = {
            TopAppBar(title = { Text(text = "Welcom to Compose", color = Color.White) }, actions = {
                IconButton(onClick = { navigateBack() }) {
                    Icon(asset = Icons.Filled.Close, tint = Color.White)
                }
            }, elevation = 4.dp, backgroundColor = Color.Blue)
        }, floatingActionButton = {
            FloatingActionButton(onClick = { _counter++ }) {
                Icon(asset = Icons.Default.Add)
            }
        }) {
            Column(verticalArrangement = Arrangement.Center,horizontalGravity = Alignment.CenterHorizontally,modifier = Modifier.fillMaxSize()) {
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