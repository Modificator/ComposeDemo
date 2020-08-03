package cn.modificator.demo.ui

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.height
import androidx.ui.material.Divider
import androidx.ui.material.ListItem
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.unit.dp
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
                SimpleListItem(title = "Imitate other Apps", onClick = {})
                SimpleListItem(
                    title = "Widgets Demo",
                    onClick = { navigateTo(WidgetsDemoController()) })
            }
        }
    }
}