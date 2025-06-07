package com.example.ukuleletuner2.ui.screens.ChordsScreenPackage

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.ukuleletuner2.R
import com.example.ukuleletuner2.audioplayer.AndroidAudioPlayer
import com.example.ukuleletuner2.chords.Chord
import com.example.ukuleletuner2.windowInfo.WindowOrientation
import com.example.ukuleletuner2.windowInfo.rememberWindowInfo

//https://www.youtube.com/watch?v=HmXgVBys7BU (screen rotation)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChordsScreen(
    onNavigateToSettings: () -> Unit,
    onNavigateToTunerScreen: () -> Unit
) {
    val windowInfo = rememberWindowInfo()
    val context = LocalContext.current
    val player = remember { AndroidAudioPlayer(context) }
    var playingChordId by remember { mutableIntStateOf(-1) }

    val chords = remember {
        listOf(
            Chord(1, R.string.ukulele_c, R.drawable.ukulele_c, R.raw.ukulele_c),
            Chord(2, R.string.ukulele_f, R.drawable.ukulele_f, R.raw.ukulele_f),
            Chord(3, R.string.ukulele_g, R.drawable.ukulele_g, R.raw.ukulele_g),
            Chord(4, R.string.ukulele_e, R.drawable.ukulele_e, R.raw.ukulele_e),
            Chord(5, R.string.ukulele_a, R.drawable.ukulele_a, R.raw.ukulele_a),
            Chord(6, R.string.ukulele_b, R.drawable.ukulele_b, R.raw.ukulele_b),
            Chord(7, R.string.ukulele_am, R.drawable.ukulele_am, R.raw.ukulele_am),
            Chord(8, R.string.ukulele_em, R.drawable.ukulele_em, R.raw.ukulele_em),
            Chord(9, R.string.ukulele_bm, R.drawable.ukulele_bm, R.raw.ukulele_bm),
            Chord(10, R.string.ukulele_dm, R.drawable.ukulele_dm, R.raw.ukulele_dm),
            Chord(11, R.string.ukulele_c7, R.drawable.ukulele_c7, R.raw.ukulele_c7),
            Chord(12, R.string.ukulele_e7, R.drawable.ukulele_e7, R.raw.ukulele_e7),
            Chord(13, R.string.ukulele_a7, R.drawable.ukulele_a7, R.raw.ukulele_a7),
            Chord(14, R.string.ukulele_d7, R.drawable.ukulele_d7, R.raw.ukulele_d7)
        )
    }

    DisposableEffect(player) {
        player.setAfterHook {
            playingChordId = -1
        }
        onDispose {
            player.stop()
        }
    }

    when (windowInfo.screenOrientation) {
        is WindowOrientation.Portrait -> {
            ChordsWithTopBar(
                onNavigateToSettings = onNavigateToSettings,
                onNavigateToChords = {},
                onNavigateToHome = onNavigateToTunerScreen
            ) { paddingValues ->
                ChordsGrid(
                    chords = chords,
                    playingChordId = playingChordId,
                    columnCount = 2,
                    paddingValues = paddingValues,
                    onChordPlayed = { chord ->
                        playingChordId = chord.id
                        try {
                            player.playResource(chord.audioFileName)
                        } catch (e: Exception) {
                            playingChordId = -1
                        }
                    }
                )
            }
        }

        is WindowOrientation.Landscape -> {
            ChordsWithTopBar(
                onNavigateToSettings = onNavigateToSettings,
                onNavigateToChords = {},
                onNavigateToHome = onNavigateToTunerScreen
            ) { paddingValues ->
                ChordsGrid(
                    chords = chords,
                    playingChordId = playingChordId,
                    columnCount = 4,
                    paddingValues = paddingValues,
                    onChordPlayed = { chord ->
                        playingChordId = chord.id
                        try {
                            player.playResource(chord.audioFileName)
                        } catch (e: Exception) {
                            playingChordId = -1
                        }
                    }
                )
            }
        }
    }
}

