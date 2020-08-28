package cn.modificator.demo

import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview
import cn.modificator.demo.theme.DemoTheme

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
}