package cn.modificator.demo.ui

import androidx.compose.*
import androidx.core.view.ViewCompat
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.material.IconToggleButton
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.Close
import androidx.ui.material.icons.filled.Star
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
            var toggleState by state{false}
            IconToggleButton(checked = toggleState, onCheckedChange = { toggleState = it } ) {
                Icon(asset = if (toggleState) Icons.Filled.Star else Icons.Filled.Close)
            }

        }
    }
}