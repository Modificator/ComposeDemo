package cn.modificator.demo.ui

import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview

@Composable
fun SimpleListItem(title:String, onClick: () -> Unit){
    Column(modifier = Modifier.fillMaxWidth().height(50.dp).clickable(
        onClick = onClick
    )) {
        Text(
            text = title,
            color = Color.Black,
            fontSize = TextUnit.Sp(16),
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(12.dp)
        )
        Divider(Modifier.fillMaxWidth().height(1.dp))
    }
}

@Preview
@Composable
fun SimpleListItemPreview(){
    Column(modifier = Modifier.fillMaxSize()) {
        SimpleListItem(title = "Imitate other Apps", onClick = {
        })
        SimpleListItem(
            title = "Widgets Demo",
            onClick = {  })
    }
}