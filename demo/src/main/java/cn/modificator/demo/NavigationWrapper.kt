package cn.modificator.demo

import androidx.compose.animation.animate
import androidx.compose.animation.animatedFloat
import androidx.compose.animation.animatedValue
import androidx.compose.animation.core.AnimatedFloat
import androidx.compose.animation.core.ExponentialDecay
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.Box
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Stack
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.offsetPx
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Layout
import androidx.compose.ui.MeasureBlock
import androidx.compose.ui.Modifier
import androidx.compose.ui.WithConstraints
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.layout.id
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.unit.Bounds
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMaxBy
import cn.modificator.demo.ui.HomeController
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

@Composable
fun navigationWrapper(current:NavigationMode,modifier: Modifier= Modifier){
    WithConstraints(modifier = modifier.fillMaxSize()) {
//        var state by mutableStateOf<PageController>(EmptyPage())
        val state = remember { NavigationState() }
        val swipeOffset = mutableStateOf(0f)
        val swipeOffset2 = animatedFloat(0f)
        val minValue = 0f
        val maxValue = constraints.maxWidth.toFloat()
//        val anchors = mapOf(minValue to 0,maxValue to 1)
        val anchors = mapOf(minValue to DrawerValue.Closed, maxValue to DrawerValue.Open)
        if (state.current==null){
            state.current = current.current
            state.current!!.screenContent()
            return@WithConstraints
        }
        var left = state.current!!
        var right = current.current!!
        var autoAnimTargetValue = minValue
        var autoAnimStartValue = maxValue
        if(current is NavigationMode.Backward){
            left = right.also { right = left }
            autoAnimTargetValue = maxValue
            autoAnimStartValue = minValue
        }
        if (left == right){
            return@WithConstraints
        }else if (left == null){
            state.current = current.current
            state.current!!.screenContent()
            return@WithConstraints
        }else
        {
            if(current is NavigationMode.Backward) {
                swipeOffset2.snapTo(minValue)
            }else{
                swipeOffset2.snapTo(autoAnimStartValue)
            }
        }
//        state = current.current!!
//        state.screenContent()
//        return@WithConstraints

        Stack(Modifier.draggable(
            orientation = Orientation.Horizontal,
            onDrag = { fl ->
                val old = swipeOffset.value
                swipeOffset.value = min(max((swipeOffset.value + fl),minValue),maxValue)
                swipeOffset2.snapTo(swipeOffset.value)
                swipeOffset.value - old
            },
            onDragStopped = { velocity ->
                val targetValue = if (ExponentialDecay().getTarget(
                        swipeOffset.value,
                        velocity
                    ) > maxValue / 2f
                ) maxValue else minValue
//                swipeOffset.value = animate(swipeOffset.value,targetValue,endListener = { bounds: Bounds ->

                swipeOffset2.animateTo(targetValue, onEnd = { _, _ ->
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
               Box(Modifier.layoutId(0)){left.screenContent()}
               Box(Modifier.layoutId(1)){right.screenContent()}
            }, measureBlock ={ list, constraints ->
                val placeables = list.map { it.measure(constraints) to it.id }
                val height = placeables.fastMaxBy { it.first.height }?.first?.height ?: 0
                layout(constraints.maxWidth, height) {
                    placeables.fastForEach {(placeable, tag) ->
                        if (tag is Int) {
                            placeable.place(
                                x = constraints.maxWidth * tag + swipeOffset2.value.toInt(),
                                y = 0
                            )
                        }
                    }
                }
            })
        }
        swipeOffset2.animateTo(autoAnimTargetValue, onEnd = { _, _ ->
            state.current = current.current!!
        })
    }
}
private class NavigationState{
    var current:PageController?=null
}