package com.example.ukuleletuner2.viewModels.TunerViewModel

data class TuneStatus(
    val status: TuningStatus = TuningStatus.Waiting,
    val note: String? = null
)