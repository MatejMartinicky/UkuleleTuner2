/**
 * @author Matej Martinicky
 */
package com.example.ukuleletuner2.viewModels.TunerViewModel
/**
 * represents the current tuning status of the ukulele
 *
 * @param status current tuning state (waiting, tuned, not tuned, error)
 * @param note name of the detected note if tuned, null otherwise
 */
data class TuneStatus(
    val status: TuningStatus = TuningStatus.WAITING,
    val note: String? = null
)