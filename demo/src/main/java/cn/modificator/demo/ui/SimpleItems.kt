package cn.modificator.demo.ui

import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

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
            modifier = Modifier.fillMaxSize()
                .wrapContentHeight(Alignment.CenterVertically).padding(12.dp)
        )
        Divider(Modifier.fillMaxWidth().height(1.dp))
    }
}