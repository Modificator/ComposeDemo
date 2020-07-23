package cn.modificator.demo.ui

import androidx.compose.Composable
import androidx.ui.foundation.Text
import androidx.ui.material.Scaffold
import cn.modificator.demo.PageController
import cn.modificator.demo.R

class HomeController : PageController() {
    override fun getId(): Int {
        return R.id.controller_home
    }

    @Composable
    override fun screenContent() {
        Scaffold {
            Text(text = "Home")
        }
    }
}