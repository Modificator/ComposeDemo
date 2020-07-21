package cn.modificator.demo

import androidx.animation.TweenBuilder
import androidx.compose.*
import androidx.ui.animation.Crossfade
import androidx.ui.material.Surface
import androidx.ui.tooling.preview.Preview
import cn.modificator.demo.ui.SplashController

val navigationController = NavigationController()

class NavigationController() {
    //    @Composable
    private val stack = arrayListOf<PageController>(SplashController())
    private var current: PageController by mutableStateOf<PageController>(stack[0])//:ScreenController by state<ScreenController>{ SplashController() }
    private var currentIndex=0
    @Preview
    @Composable
    fun viewContent() {
        Crossfade(current = current,animation = TweenBuilder()) {
            Surface {
                it.screenContent()
                it.onFocus()
            }
        }
    }

    fun navigateBack(): Boolean {
        current.onBlur()
        stack.remove(current)
        currentIndex--
        updateCurrentScreen()
        return true
    }

    fun navigateTo(controller: PageController): Boolean {
        stack.add(controller)
        currentIndex++
        updateCurrentScreen()
        return true
    }

    fun updateCurrentScreen(){
        current = stack.get(currentIndex)
    }

    fun onBackPressed(): Boolean {
        if (stack.last().onBackPressed(false)){
            return true
        }
        return false
    }
}
