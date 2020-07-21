package cn.modificator.demo

import androidx.annotation.CallSuper
import androidx.compose.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

abstract class PageController:CoroutineScope by MainScope() {


    @Composable
    open abstract fun screenContent()

    @CallSuper
    open fun onFocus() {
    }

    @CallSuper
    open fun onBlur(){

    }

    open fun navigateTo(controller: PageController): Boolean {
        return navigationController.navigateTo(controller)
    }

    open fun navigateBack(): Boolean {
        return navigationController.navigateBack()
    }

    /**
     * @param fromTop
     * @return intercept navigate back
     */
    open fun onBackPressed(fromTop: Boolean): Boolean {
        return false
    }
}