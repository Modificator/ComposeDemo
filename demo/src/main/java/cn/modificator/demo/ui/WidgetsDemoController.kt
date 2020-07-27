package cn.modificator.demo.ui

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.fillMaxSize
import androidx.ui.material.Button
import androidx.ui.material.IconButton
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.ArrowBack
import cn.modificator.demo.PageController
import cn.modificator.demo.R

class WidgetsDemoController : PageController() {
    override fun getId(): Int {
        return R.id.controller_widgetsDemo
    }

    @Composable
    override fun screenContent() {
        Scaffold(topBar = {
            TopAppBar(title = { Text(text = "Widgets Demo") },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(asset = Icons.Filled.ArrowBack)
                    }
                })
        }) {
            VerticalScroller(modifier = Modifier.fillMaxSize()) {
                Button(onClick = { navigateTo(ButtonShowController()) }) {
                    Text(text = "Button")
                }
            }
        }
    }
}