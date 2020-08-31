package cn.modificator.demo.ui.widgets

import android.util.Log
import android.view.ViewConfiguration
import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.ExponentialDecay
import androidx.compose.foundation.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.state
import androidx.compose.ui.*
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.layout.id
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMaxBy
import androidx.core.view.ViewCompat
import cn.modificator.demo.PageController
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class ViewPagerController : PageController() {
    override fun getId(): Int {
        return 2
    }

    @Composable
    override fun screenContent() {
        ViewPager(pageCount = 10, modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center, horizontalGravity = Alignment.CenterHorizontally) {
                Text(text = "Position $it")
            }
        }
    }

}

@Composable
fun ViewPager(pageCount: Int, modifier: Modifier, pageCreater: @Composable (position: Int) -> Unit) {
    WithConstraints {
        val offset = animatedFloat(initVal = 0f)
        val position = state { 0 }
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
            Layout(children = {
                for (i in 0 until pageCount) {
                    Box(Modifier.layoutId(i)){
                        pageCreater(i)
                    }
                }
            }, measureBlock ={ list, constraints ->
                val placeables = list.map { it.measure(constraints) to it.id }
                val height = placeables.fastMaxBy { it.first.height }?.first?.height ?: 0
                layout(constraints.maxWidth, height) {
                    placeables.fastForEach {(placeable, tag) ->
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
