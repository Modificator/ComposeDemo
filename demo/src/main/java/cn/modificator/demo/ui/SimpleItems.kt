package cn.modificator.demo.ui

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.foundation.clickable
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Divider
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.TextUnit
import androidx.ui.unit.dp

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