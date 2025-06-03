package com.example.ukuleletuner2.viewModels.TunerViewModel

data class TuneStatus(
    val status: TuningStatus = TuningStatus.WAITING,
    val note: String? = null
)