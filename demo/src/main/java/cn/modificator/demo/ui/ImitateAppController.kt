package cn.modificator.demo.ui

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.AdapterList
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.fillMaxSize
import androidx.ui.material.IconButton
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.ArrowBack
import cn.modificator.demo.PageController
import cn.modificator.demo.R
import cn.modificator.demo.ui.imitate.FlutterDemoController
import cn.modificator.demo.ui.widgets.ButtonShowController

class ImitateAppController : PageController() {
    override fun getId(): Int {
        return R.id.controller_widgetsDemo
    }

    @Composable
    override fun screenContent() {
        Scaffold(topBar = {
            TopAppBar(title = { Text(text = "Imitate other Apps") },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(asset = Icons.Filled.ArrowBack)
                    }
                })
        }) {
            VerticalScroller(modifier = Modifier.fillMaxSize()) {
                SimpleListItem(title = "FlutterDemo", onClick = {
                    navigateTo(FlutterDemoController())
                })
            }
        }
    }
}