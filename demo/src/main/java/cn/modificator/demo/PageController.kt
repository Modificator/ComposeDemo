package cn.modificator.demo

import androidx.annotation.CallSuper
import androidx.compose.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

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

    @CallSuper
    open fun destory(){
        cancel()
    }

    /**
     * @param fromTop
     * @return intercept navigate back
     */
    open fun onBackPressed(fromTop: Boolean): Boolean {
        return false
    }
}