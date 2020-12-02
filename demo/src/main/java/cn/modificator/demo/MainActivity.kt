package cn.modificator.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.patchself.compose.navigator.navigationController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoApp()
        }
    }

    override fun onBackPressed() {
        if (!navigationController.onBackPressed()) {
            super.onBackPressed()
        }
    }
}
