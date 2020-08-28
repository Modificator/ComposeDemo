package cn.modificator.demo.ui.widgets

import androidx.compose.*
import androidx.compose.animation.animate
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.drawLayer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.ui.tooling.preview.Preview
import cn.modificator.demo.PageController

class ButtonShowController : PageController() {

    override fun getId(): Int {
        return ViewCompat.generateViewId()
    }

    @Preview
    @Composable
    fun preview() {
        screenContent()
    }

    @Composable
    override fun screenContent() {
        Scaffold(topBar = {
            TopAppBar(title = { Text(text = "Button") }, navigationIcon = {
                IconButton(onClick = { navigateBack() }) {
                    Icon(asset = Icons.Filled.ArrowBack)
                }
            })
        }) {
            var toggleState by mutableStateOf<Boolean>(false)
            var toggleButtonIndex by mutableStateOf<Int>(0)
            ScrollableColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp)) {
                Spacer(modifier = Modifier.size(30.dp))
                Text(text = "Text button")
                Spacer(modifier = Modifier.size(5.dp))
                TextButton(onClick = {}) {
                    Text(text = "Button")
                }
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = "Outlined button")
                Spacer(modifier = Modifier.size(5.dp))
                OutlinedButton(onClick = {}) {
                    Text(text = "Button")
                }
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = "Contained button")
                Spacer(modifier = Modifier.size(5.dp))
                Button(onClick = { }) {
                    Text(text = "Button")
                }
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = "Toggle button")
                Spacer(modifier = Modifier.size(5.dp))
                Row {
                    IconToggleButton(
                        checked = toggleButtonIndex == 0,
                        onCheckedChange = { toggleButtonIndex = 0 }) {
                        val tint = animate(
                            if (toggleButtonIndex == 0) Color(0xFFEC407A) else Color(0xFFB0BEC5)
                        )
                        val opacity = animate(if (toggleButtonIndex == 0) 1f else 0.5f)
                        Icon(
                            asset = Icons.Default.Share,
                            tint = tint,
                            modifier = Modifier.drawOpacity(opacity = opacity)
                        )
                    }
                    IconToggleButton(
                        checked = toggleButtonIndex == 1,
                        onCheckedChange = { toggleButtonIndex = 1 }) {
                        val tint = animate(
                            if (toggleButtonIndex == 1) Color(0xFFEC407A) else Color(0xFFB0BEC5)
                        )
                        val opacity = animate(if (toggleButtonIndex == 1) 1f else 0.5f)
                        Icon(
                            asset = Icons.Default.Add,
                            tint = tint,
                            modifier = Modifier.drawOpacity(opacity = opacity)
                        )
                    }
                    IconToggleButton(
                        checked = toggleButtonIndex == 2,
                        onCheckedChange = { toggleButtonIndex = 2 }) {
                        val tint = animate(
                            if (toggleButtonIndex == 2) Color(0xFFEC407A) else Color(0xFFB0BEC5)
                        )
                        val opacity = animate(if (toggleButtonIndex == 2) 1f else 0.5f)
                        Icon(
                            asset = Icons.Default.Send,
                            tint = tint,
                            modifier = Modifier.drawOpacity(opacity = opacity)
                        )
                    }
                }
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = "Image button")
                Spacer(modifier = Modifier.size(5.dp))
                Button(onClick = {}) {
                    Row(modifier = Modifier.wrapContentSize()) {
                        Image(
                            asset = Icons.Filled.Search,
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                        Text(
                            text = "Button",
                            modifier = Modifier.fillMaxHeight().gravity(Alignment.CenterVertically)
                        )
                    }
                }
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = "Floating action button")
                Spacer(modifier = Modifier.size(5.dp))
                FloatingActionButton(onClick = {}) {
                    Icon(asset = Icons.Default.Add)
                }
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = "Toggle button with animation")
                Spacer(modifier = Modifier.size(5.dp))
                IconToggleButton(checked = toggleState, onCheckedChange = { toggleState = it }) {
                    val rotateValue = animate(if (toggleState) 45f else 0f)
                    Icon(asset = Icons.Filled.Add, modifier = Modifier.drawLayer(rotationZ = rotateValue))
                }
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = "RoundedCornerShape")
                Spacer(modifier = Modifier.size(5.dp))
                Button(onClick = {},modifier = Modifier.clip(RoundedCornerShape(8.dp))) {
                    Text(text = "Button")
                }
                Spacer(modifier = Modifier.size(6.dp))
                Button(onClick = {},modifier = Modifier.clip(RoundedCornerShape(100.dp))) {
                    Text(text = "Button")
                }
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = "CutCornerShape")
                Spacer(modifier = Modifier.size(5.dp))
                Button(onClick = {},modifier = Modifier.clip(CutCornerShape(6.dp))) {
                    Text(text = "Button")
                }
                Spacer(modifier = Modifier.size(30.dp))
            }
        }
    }

    fun clickToast() {

    }
}