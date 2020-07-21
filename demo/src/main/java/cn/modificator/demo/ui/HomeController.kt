package cn.modificator.demo.ui

import androidx.compose.Composable
import androidx.ui.foundation.Text
import androidx.ui.material.Scaffold
import cn.modificator.demo.PageController

class HomeController : PageController() {

    @Composable
    override fun screenContent() {
        Scaffold {
            Text(text = "Home")
        }
    }
}