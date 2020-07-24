package cn.modificator.demo.ui

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.fillMaxSize
import androidx.ui.material.Button
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import cn.modificator.demo.PageController
import cn.modificator.demo.R

class HomeController : PageController() {
    override fun getId(): Int {
        return R.id.controller_home
    }

    @Composable
    override fun screenContent() {
        Scaffold(topBar = {
            TopAppBar(title = { Text(text = "Home") })
        }) {
            VerticalScroller(modifier = Modifier.fillMaxSize()) {
                Button(onClick = { navigateTo(ButtonShowController()) }) {
                    Text(text = "Button")
                }
            }
        }
    }
}