package cn.modificator.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent

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
