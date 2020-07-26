package cn.modificator.demo.ui

import androidx.compose.*
import androidx.core.view.ViewCompat
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.Close
import androidx.ui.material.icons.filled.Search
import androidx.ui.material.icons.filled.Star
import androidx.ui.unit.dp
import cn.modificator.demo.PageController

class ButtonShowController: PageController() {
    override fun getId(): Int {
        return ViewCompat.generateViewId()
    }

    @Composable
    override fun screenContent() {
        Scaffold(topBar = {
            TopAppBar(title = { Text(text = "Button") })
        }) {
            var toggleState by state{false}
            VerticalScroller(modifier = Modifier.fillMaxSize().padding(20.dp)) {
                Spacer(modifier = Modifier.size(20.dp))
                Button(onClick = {  } ) {
                    Text(text = "Normal Button")
                }
                Spacer(modifier = Modifier.size(10.dp))
                Button(onClick = {}) {
                    Row {
                        Image(asset = Icons.Filled.Search)       
                        Text(text = "Image Button")
                    }
                }
                Spacer(modifier = Modifier.size(10.dp))
                IconToggleButton(checked = toggleState, onCheckedChange = { toggleState = it } ) {
                    Icon(asset = if (toggleState) Icons.Filled.Star else Icons.Filled.Close)
                }
            }
        }
    }
    
    fun clickToast(){
        
    }
}