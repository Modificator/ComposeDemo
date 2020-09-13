package cn.modificator.demo

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.ui.tooling.preview.Preview
import cn.modificator.demo.ui.HomeController
import cn.modificator.demo.ui.SplashController

val navigationController = NavigationController()

class NavigationController() {
    //    @Composable
    private val stack = arrayListOf<PageController>(SplashController())
    private var current: NavigationMode by mutableStateOf(NavigationMode.Rebase(HomeController()))//:ScreenController by state<ScreenController>{ SplashController() }
    private var currentIndex=0
    @Preview
    @Composable
    fun viewContent() {
        navigationWrapper(current = current)
    }

    fun navigateBack(): Boolean {
        stack.remove(current.current)
        currentIndex--
        current = NavigationMode.Backward(stack.last())
        return true
    }

    fun navigateTo(controller: PageController): Boolean {
        stack.add(controller)
        currentIndex++
        current = NavigationMode.Forward(stack.last())
        return true
    }

    fun initController(controller: PageController){
        stack.forEach { it.destory() }
        stack.clear()
        currentIndex=-1
        navigateTo(controller)
    }

    fun findLastById(id: Int):PageController?{
        return stack.findLast { it->it.getId() == id }
    }

    fun updateCurrentScreen(){
//        current = stack.get(currentIndex)
    }

    fun onBackPressed(): Boolean {
        if (stack.last().onBackPressed(false)){
            return true
        }
        if (stack.size>1){
            navigateBack()
            return true
        }
        return false
    }
}

sealed class NavigationMode(var current: PageController?=null) {
    class Forward(current: PageController) :NavigationMode(current)
    class Backward(current: PageController) :NavigationMode(current)
    class Rebase(current: PageController):NavigationMode(current)
    class Fade(current: PageController):NavigationMode(current)
}