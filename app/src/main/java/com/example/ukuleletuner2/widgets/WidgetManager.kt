/**
 * @author Matej Martinicky
 *
 * References:
 * @see source: Philipp Lackner (YouTube) -
 * "How to Create a Navigation Drawer With Jetpack Compose - Android Studio Tutorial"
 *      https://www.youtube.com/watch?v=bhrN7yFG0D4
 */
package com.example.ukuleletuner2.widgets

/**
 * manages widget viewmodel instance for action callbacks
 *
 * provides bridge between widget action callbacks and viewmodel
 * since callbacks cannot directly access composable viewmodel
 */
object WidgetManager {
    private var viewModel: TuningWidgetViewModel? = null
    /**
     * sets the active widget viewmodel instance
     *
     * @param vm viewmodel to manage
     */
    fun setViewModel(vm: TuningWidgetViewModel) {
        viewModel = vm
    }
    /**
     * starts tuning through managed viewmodel
     */
    fun startTuning() {
        viewModel?.startTuning()
    }
    /**
     * stops tuning through managed viewmodel
     */
    fun stopTuning() {
        viewModel?.stopTuning()
    }
}