package cn.modificator.demo.ui.widgets

import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.ExponentialDecay
import androidx.compose.material.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.layout.id
import androidx.compose.ui.layout.layoutId
import com.patchself.compose.navigator.PageController
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class ViewPagerController : PageController() {
    override fun getId(): Int {
        return 2
    }

    @Composable
    override fun screenContent() {
        Box(Modifier.background(Color.White)) {
            ViewPager(pageCount = 10, modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Position $it")
                }
            }
        }
    }

}

@Composable
fun ViewPager(pageCount: Int, modifier: Modifier, pageCreater: @Composable (position: Int) -> Unit) {
    WithConstraints {
        val offset = animatedFloat(initVal = 0f)
        val position = mutableStateOf(0)
        val width = constraints.maxWidth.toFloat()
        val draggable = modifier.draggable(
                orientation = Orientation.Horizontal,
                onDragStarted = {
                },
                onDragStopped = { velocity ->
                    val target = ExponentialDecay().getTarget(offset.value,velocity)
                    val pageOffset = target + position.value * width
                    var scrollOffset = 0f
                    if (abs(pageOffset) > width/2f){
                        if(pageOffset>0){
                            scrollOffset = -pageOffset
                            position.value--
                        }else {
                            scrollOffset = width - pageOffset
                            position.value++
                        }
                    }
                    position.value = min(position.value,pageCount-1)
                    position.value = max(position.value,0)
//                    offset.animateTo(target+scrollOffset)
                    offset.animateTo(-position.value * width)
                },
                onDrag = { fl ->
                    if (position.value == pageCount - 1 && fl < 0) {
                        0f
                    } else if (position.value == 0 && fl > 0) {
                        0f
                    } else {
                        val old = offset.value
                        offset.snapTo(offset.value + fl)
                        offset.value - old
                    }
                },
                enabled = true
        )
        Box(draggable) {
            Layout(content = {
                for (i in 0 until pageCount) {
                    Box(Modifier.layoutId(i)){
                        pageCreater(i)
                    }
                }
            }, measureBlock ={ list, constraints ->
                val placeables = list.map { it.measure(constraints) to it.id }
                val height = placeables.maxByOrNull { it.first.height }?.first?.height ?: 0
                layout(constraints.maxWidth, height) {
                    placeables.forEach {(placeable, tag) ->
                        if (tag is Int) {
                            placeable.place(
                                    x = constraints.maxWidth * tag + offset.value.toInt(),
                                    y = 0
                            )
                        }
                    }
                }
            })

        }
    }
}
