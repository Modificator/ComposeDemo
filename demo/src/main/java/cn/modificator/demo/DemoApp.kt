package cn.modificator.demo

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cn.modificator.demo.theme.DemoTheme
import cn.modificator.demo.ui.HomeController
import com.patchself.compose.navigator.navigationController

@Composable
fun DemoApp() {
    DemoTheme {
        AppContent()
    }
}

@Preview
@Composable
fun AppContent() {
    navigationController.initController(HomeController())
    navigationController.ViewContent()
}