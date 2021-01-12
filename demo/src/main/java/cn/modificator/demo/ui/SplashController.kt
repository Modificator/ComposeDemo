package cn.modificator.demo.ui

import androidx.compose.material.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import cn.modificator.demo.R
import com.patchself.compose.navigator.PageController
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
            Row(
                Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Splash",fontSize = 30.sp)
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