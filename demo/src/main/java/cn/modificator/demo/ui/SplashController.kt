package cn.modificator.demo.ui

import androidx.compose.Composable
import androidx.ui.foundation.Text
import androidx.ui.layout.Row
import androidx.ui.material.Button
import androidx.ui.material.Scaffold
import androidx.ui.tooling.preview.Preview
import cn.modificator.demo.PageController
import cn.modificator.demo.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashController : PageController() {
    override fun getId(): Int {
        return R.id.controller_splash
    }

    @Preview
    @Composable
    override fun screenContent() {
        Scaffold {
            Row {
                Text(text = "Splash")
                Button(onClick = {
                    setController(HomeController())
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
            setController(HomeController())
        }
    }
}