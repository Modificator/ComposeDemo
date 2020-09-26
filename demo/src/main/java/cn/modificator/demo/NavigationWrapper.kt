package cn.modificator.demo

import android.util.Log
import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.ExponentialDecay
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Box
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.DrawerValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.onCommit
import androidx.compose.runtime.remember
import androidx.compose.ui.Layout
import androidx.compose.ui.Modifier
import androidx.compose.ui.WithConstraints
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.layout.id
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMaxBy
import kotlin.math.max
import kotlin.math.min

@Composable
fun navigationWrapper(current: NavigationMode, modifier: Modifier = Modifier) {
    WithConstraints(modifier = modifier.fillMaxSize()) {
//        var state by mutableStateOf<PageController>(EmptyPage())
        val state = remember { NavigationState() }
        val swipeOffset = animatedFloat(0f)
        val minValue = 0f
        val maxValue = constraints.maxWidth.toFloat()
        var left: PageController? = null
        var right: PageController? = null
        if (state.current == null) {
            state.current = current.current
            return@WithConstraints
        }
        if (state.current == current.current){
            return@WithConstraints
        }
        left = state.current!!
        right = current.current!!
        onCommit(v1 = current, callback = {

            var autoAnimTargetValue = minValue
            var autoAnimStartValue = maxValue
            if (current is NavigationMode.Backward) {
                left = right.also { right = left }
                autoAnimTargetValue = maxValue
                autoAnimStartValue = minValue
            }
            if (left == right) {
                return@onCommit
            } else if (left == null) {
                state.current = current.current
                return@onCommit
            } else {
                if (current is NavigationMode.Backward) {
                    swipeOffset.snapTo(minValue)
                } else {
                    swipeOffset.snapTo(autoAnimStartValue)
                }
            }
            swipeOffset.animateTo(autoAnimTargetValue, onEnd = { _, _ ->
                Log.e("=======", "onEnd:${swipeOffset.value}")
                state.current = current.current!!
            },anim = tween(400))
        })
//        state = current.current!!
//        state.screenContent()
//        return@WithConstraints

        Box(Modifier.draggable(
            enabled = true,
            orientation = Orientation.Horizontal,
            onDrag = { fl ->
                Log.e("=======", "ondrag:$fl ${swipeOffset.value}")
                val old = swipeOffset.value
                swipeOffset.snapTo(min(max((swipeOffset.value + fl), minValue), maxValue))
                swipeOffset.value - old
            },
            onDragStopped = { velocity ->
                val targetValue = if (ExponentialDecay().getTarget(
                        swipeOffset.value,
                        velocity
                    ) > maxValue / 2f
                ) maxValue else minValue
                swipeOffset.animateTo(targetValue, onEnd = { _, _ ->
                    state.current = current.current!!
                })
            }
        )) {
            /*Stack(modifier = Modifier.offsetPx(x=swipeOffset)) {
                left.screenContent()
            }
            Stack(modifier = Modifier.offsetPx(x=swipeOffset)) {
                right.screenContent()
            }*/

            Layout(children = {
                Box(Modifier.layoutId(0)) { left?.screenContent() }
                Box(Modifier.layoutId(1).drawShadow(Dp(8f))) { right?.screenContent() }
            }, measureBlock = { list, constraints ->
                val placeables = list.map { it.measure(constraints) to it.id }
                val height = placeables.fastMaxBy { it.first.height }?.first?.height ?: 0
                layout(constraints.maxWidth, height) {
                    placeables.fastForEach { (placeable, tag) ->
                        if (tag is Int) {
                            placeable.place(
                                x = if (tag == 0) {
                                    ((-constraints.maxWidth + swipeOffset.value * 1f) * 0.3f).toInt()
                                } else {
                                    swipeOffset.value.toInt()
                                },
                                y = 0
                            )
                        }
                    }
                }
            })
        }
    }
}

private class NavigationState {
    var current: PageController? = null
}