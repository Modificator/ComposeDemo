package cn.modificator.demo.ui

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cn.modificator.demo.R
import com.patchself.compose.navigator.PageController

class HomeController : PageController() {
    override fun getId(): Int {
        return R.id.controller_home
    }

    @Composable
    override fun screenContent() {
        Scaffold(topBar = {
            TopAppBar(title = { Text(text = "Comepose Demo") }, elevation = 4.dp)
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