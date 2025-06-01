package com.example.ukuleletuner2.widgets

////https://www.youtube.com/watch?v=bhrN7yFG0D4
object WidgetManager {
    private var viewModel: TuningWidgetViewModel? = null

    fun setViewModel(vm: TuningWidgetViewModel) {
        viewModel = vm
    }

    fun startTuning() {
        viewModel?.startTuning()
    }

    fun stopTuning() {
        viewModel?.stopTuning()
    }
}