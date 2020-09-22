package cn.modificator.demo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.launchInComposition
import androidx.ui.tooling.preview.Preview
import cn.modificator.demo.theme.DemoTheme
import cn.modificator.demo.ui.HomeController
import cn.modificator.demo.ui.SplashController
import kotlinx.coroutines.delay

@Composable
fun DemoApp() {
    DemoTheme {
        AppContent()
    }
}

@Preview
@Composable
fun AppContent() {
    navigationController.viewContent()
    launchInComposition(block = {
        delay(100)
//        navigationController.initController(SplashController())
        navigationController.initController(HomeController())
    })
}