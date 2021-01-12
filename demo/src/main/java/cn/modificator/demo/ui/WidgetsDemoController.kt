package cn.modificator.demo.ui

import androidx.compose.material.Icon
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.ScrollableRow
import androidx.compose.material.Text
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cn.modificator.demo.R
import cn.modificator.demo.ui.widgets.ButtonShowController
import cn.modificator.demo.ui.widgets.ViewPagerController
import com.patchself.compose.navigator.PageController

class WidgetsDemoController : PageController() {
    override fun getId(): Int {
        return R.id.controller_widgetsDemo
    }

    @Composable
    override fun screenContent() {
        Scaffold(topBar = {
            TopAppBar(title = { Text(text = "Widgets Demo") },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(Icons.Filled.ArrowBack)
                    }
                })
        }) {
            ScrollableColumn(modifier = Modifier.fillMaxSize()) {
                SimpleListItem(title = "Text", onClick = {
                    navigateTo(ButtonShowController())
                })
                SimpleListItem(title = "Button", onClick = {
                    navigateTo(ButtonShowController())
                })
                SimpleListItem(title = "ViewPager", onClick = {
                    navigateTo(ViewPagerController())
                })
            }
        }
    }
}