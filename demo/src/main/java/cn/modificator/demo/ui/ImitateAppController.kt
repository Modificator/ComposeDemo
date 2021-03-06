package cn.modificator.demo.ui

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cn.modificator.demo.R
import cn.modificator.demo.ui.imitate.FlutterDemoController
import cn.modificator.demo.ui.widgets.ButtonShowController
import com.patchself.compose.navigator.PageController

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
                        Icon(Icons.Filled.ArrowBack)
                    }
                })
        }) {
            ScrollableColumn(modifier = Modifier.fillMaxSize()) {
                SimpleListItem(title = "FlutterDemo", onClick = {
                    navigateTo(FlutterDemoController())
                })
            }
        }
    }
}