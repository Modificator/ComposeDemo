package cn.modificator.demo.ui

import androidx.compose.Composable
import androidx.core.view.ViewCompat
import androidx.ui.foundation.Text
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import cn.modificator.demo.PageController

class ButtonShowController: PageController() {
    override fun getId(): Int {
        return ViewCompat.generateViewId()
    }

    @Composable
    override fun screenContent() {
        Scaffold(topBar = {
            TopAppBar(title = { Text(text = "Button") })
        }) {

        }
    }
}