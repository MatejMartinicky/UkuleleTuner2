/**
 * @author Matej Martinicky
 */

package com.example.ukuleletuner2.viewModels.TunerViewModel

/**
 * represents different states of the tuning process
 */
enum class TuningStatus {
    WAITING,
    ERROR,
    TUNED,
    NOT_TUNED
}