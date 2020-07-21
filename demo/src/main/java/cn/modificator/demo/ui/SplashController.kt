package cn.modificator.demo.ui

import androidx.compose.Composable
import androidx.ui.foundation.Text
import androidx.ui.layout.Row
import androidx.ui.material.Button
import androidx.ui.material.Scaffold
import androidx.ui.tooling.preview.Preview
import cn.modificator.demo.PageController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashController : PageController() {

    @Preview
    @Composable
    override fun screenContent() {
        Scaffold {
            Row {
                Text(text = "Splash")
                Button(onClick = {
                    navigateTo(HomeController())
                }) {
                    "Home"
                }
            }
        }
    }

    override fun onFocus() {
        super.onFocus()
        launch {
            delay(2000)
            navigateTo(HomeController())
        }
    }
}