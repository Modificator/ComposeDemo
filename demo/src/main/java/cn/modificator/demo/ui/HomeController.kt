package cn.modificator.demo.ui

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cn.modificator.demo.PageController
import cn.modificator.demo.R

class HomeController : PageController() {
    override fun getId(): Int {
        return R.id.controller_home
    }

    @Composable
    override fun screenContent() {
        Scaffold(topBar = {
            TopAppBar(title = { Text(text = "Comepose Demo") })
        }) {
            Column(modifier = Modifier.fillMaxSize()) {
                SimpleListItem(title = "Imitate other Apps", onClick = {
                    navigateTo(ImitateAppController())
                })
                SimpleListItem(
                    title = "Widgets Demo",
                    onClick = { navigateTo(WidgetsDemoController()) })
            }
        }
    }
}