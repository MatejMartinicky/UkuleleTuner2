package com.example.ukuleletuner2.TunerScreenPackage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ukuleletuner2.customLayouts.InstrumentLayout
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ukuleletuner2.R
import com.example.ukuleletuner2.navigationDrawer.DrawerBody
import com.example.ukuleletuner2.navigationDrawer.DrawerHeader
import com.example.ukuleletuner2.navigationDrawer.MenuItem
import com.example.ukuleletuner2.navigationDrawer.MenuItems
import com.example.ukuleletuner2.viewModels.TunerViewModel.TunerViewModel
import com.example.ukuleletuner2.viewModels.TunerViewModel.TuningStatus
import com.example.ukuleletuner2.windowInfo.WindowOrientation
import com.example.ukuleletuner2.windowInfo.rememberWindowInfo
import kotlinx.coroutines.launch

//https://www.youtube.com/watch?v=HmXgVBys7BU (screen rotation)
//https://www.youtube.com/watch?v=JLICaBEiJS0 (navigation drawer)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TunerScreen(
    onNavigateToSettings: () -> Unit,
    onNavigateToChords: () -> Unit
) {
    val windowInfo = rememberWindowInfo()
    val context = LocalContext.current
    val viewModel: TunerViewModel = viewModel { TunerViewModel(context) }
    val isRecording by viewModel.isRecording.collectAsState()
    val frequency by viewModel.frequency.collectAsState()
    val tuneStatus by viewModel.tuneStatus.collectAsState()

    val displayStatus = when (tuneStatus.status) {
        TuningStatus.ERROR -> context.getString(R.string.tuning_error)
        TuningStatus.WAITING -> context.getString(R.string.tuning_waiting)
        TuningStatus.NOT_TUNED -> context.getString(R.string.tuning_waiting)
        TuningStatus.TUNED -> context.getString(R.string.tuning_in_tune, tuneStatus.note ?: "")
        else -> {}
    }

    when(windowInfo.screenOrientation) {
        is WindowOrientation.Portrait -> {
            PortraitTunerLayout(
                onNavigateToSettings = onNavigateToSettings,
                onNavigateToChords = onNavigateToChords,
                viewModel = viewModel,
                isRecording = isRecording,
                frequency = frequency,
                displayStatus = displayStatus as String
            )
        }
        is WindowOrientation.Landscape -> {
            LandscapeTunerLayout(
                onNavigateToSettings = onNavigateToSettings,
                onNavigateToChords = onNavigateToChords,
                viewModel = viewModel,
                isRecording = isRecording,
                frequency = frequency,
                displayStatus = displayStatus as String
            )
        }
    }
}
