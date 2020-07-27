package cn.modificator.demo.ui

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.ClickableText
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.Divider
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.text.AnnotatedString
import androidx.ui.text.SpanStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.TextUnit
import androidx.ui.unit.dp
import cn.modificator.demo.PageController
import cn.modificator.demo.R

class HomeController : PageController() {
    override fun getId(): Int {
        return R.id.controller_home
    }

    @Composable
    override fun screenContent() {
        Scaffold(topBar = {
            TopAppBar(title = { Text(text = "Comepose Demo") })
        }) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(Modifier.fillMaxWidth().height(50.dp)) {
                    ClickableText(
                        text = AnnotatedString(
                            "Imitate other Apps",
                            spanStyle = SpanStyle(
                                color = Color.Black,
                                fontSize = TextUnit.Companion.Sp(16),
                                fontWeight = FontWeight.Light
                            )
                        ),
                        modifier = Modifier.fillMaxSize()
                            .wrapContentHeight(Alignment.CenterVertically)
                            .padding(12.dp),
                        onClick = {

                        })
                }
                Divider(Modifier.fillMaxWidth().height(1.dp))
                Row(Modifier.fillMaxWidth().height(50.dp)) {
                    ClickableText(
                        text = AnnotatedString(
                            "Widgets Demo",
                            spanStyle = SpanStyle(
                                color = Color.Black,
                                fontSize = TextUnit.Companion.Sp(16),
                                fontWeight = FontWeight.Light
                            )
                        ),
                        modifier = Modifier.fillMaxSize()
                            .wrapContentHeight(Alignment.CenterVertically)
                            .padding(12.dp),
                        onClick = {
                            navigateTo(WidgetsDemoController())
                        })
                }
            }
        }
    }
}