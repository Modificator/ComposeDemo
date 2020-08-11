package cn.modificator.demo.ui.widgets

import android.view.ViewConfiguration
import androidx.animation.*
import androidx.compose.Composable
import androidx.compose.remember
import androidx.compose.state
import androidx.core.view.ViewCompat
import androidx.ui.animation.animatedFloat
import androidx.ui.core.*
import androidx.ui.foundation.*
import androidx.ui.foundation.animation.FlingConfig
import androidx.ui.foundation.gestures.DragDirection
import androidx.ui.foundation.gestures.ScrollableState
import androidx.ui.foundation.gestures.draggable
import androidx.ui.foundation.gestures.scrollable
import androidx.ui.layout.Arrangement
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxSize
import androidx.ui.util.fastForEach
import androidx.ui.util.fastMaxBy
import cn.modificator.demo.PageController

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
                dragDirection = DragDirection.Horizontal,
                onDragStarted = {
                },
                onDragStopped = { velocity ->
                    val target = ExponentialDecay().getTarget(offset.value,velocity)
                    val pageOffset = target % width
                    var scrollOffset = 0f
                    if (pageOffset > width/2f){
                        scrollOffset = width - pageOffset
                    }else{
                        scrollOffset = -pageOffset
                    }
                    offset.animateTo(target+scrollOffset)
                },
                onDragDeltaConsumptionRequested = { fl ->
                    val old = offset.value
                    offset.snapTo(offset.value + fl)
                    offset.value - old
                },
                enabled = true
        )
        Box(draggable) {
            Layout(children = {
                for (i in 0 until pageCount) {
                    Box(Modifier.tag(i)){
                        pageCreater(i)
                    }
                }
            }, measureBlock = { list, constraints, layoutDirection ->
                val placeables = list.map { it.measure(constraints) to it.tag }
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
