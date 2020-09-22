package cn.modificator.demo

import android.util.Log
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
        val swipeOffset2 = animatedFloat(0f,)
        val minValue = 0f
        val maxValue = constraints.maxWidth.toFloat()
//        val anchors = mapOf(minValue to 0,maxValue to 1)
        val anchors = mapOf(minValue to DrawerValue.Closed, maxValue to DrawerValue.Open)
        var left :PageController?=null
        var right :PageController?=null
        if (state.current==null){
            state.current = current.current
            return@WithConstraints
        }
        left = state.current!!
        right = current.current!!
        onCommit(v1 = current, callback = {

            var autoAnimTargetValue = minValue
            var autoAnimStartValue = maxValue
            if(current is NavigationMode.Backward){
                left = right.also { right = left }
                autoAnimTargetValue = maxValue
                autoAnimStartValue = minValue
            }
            if (left == right){
                return@onCommit
            }else if (left == null){
                state.current = current.current
                return@onCommit
            }else
            {
                if(current is NavigationMode.Backward) {
                    swipeOffset2.snapTo(minValue)
                }else{
                    swipeOffset2.snapTo(autoAnimStartValue)
                }
            }
            swipeOffset2.animateTo(autoAnimTargetValue, onEnd = { _, _ ->
                Log.e("=======","onEnd:${swipeOffset2.value}")
                state.current = current.current!!
            })
        })
//        state = current.current!!
//        state.screenContent()
//        return@WithConstraints

        Box(Modifier.draggable(
            enabled = true,
            orientation = Orientation.Horizontal,
            onDrag = { fl ->
                Log.e("=======","ondrag:$fl ${swipeOffset2.value}")
                val old = swipeOffset2.value
                swipeOffset2.snapTo(min(max((swipeOffset2.value + fl),minValue),maxValue))
                swipeOffset2.value - old
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
               Box(Modifier.layoutId(0)){left?.screenContent()}
               Box(Modifier.layoutId(1)){right?.screenContent()}
            }, measureBlock ={ list, constraints ->
                val placeables = list.map { it.measure(constraints) to it.id }
                val height = placeables.fastMaxBy { it.first.height }?.first?.height ?: 0
                layout(constraints.maxWidth, height) {
                    placeables.fastForEach {(placeable, tag) ->
                        Log.e("=======","value:${swipeOffset2.value}")
                        if (tag is Int) {
                            Log.e("=======","page:$tag offset:${constraints.maxWidth * (tag-1) + swipeOffset2.value.toInt()}")
                            placeable.place(
//                                x = constraints.maxWidth * tag + swipeOffset2.value.toInt(),
                                x = constraints.maxWidth * (tag-1) + swipeOffset2.value.toInt(),
                                y = 0
                            )
                        }
                    }
                }
            })
        }
    }
}
private class NavigationState{
    var current:PageController?=null
}