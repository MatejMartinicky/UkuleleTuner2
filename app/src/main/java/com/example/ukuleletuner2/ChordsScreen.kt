package com.example.ukuleletuner2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.ui.res.stringResource
import com.example.ukuleletuner2.chords.Chord
import com.example.ukuleletuner2.ui.components.cards.ChordCard
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.ukuleletuner2.audioplayer.AndroidAudioPlayer
import com.example.ukuleletuner2.windowInfo.WindowOrientation
import com.example.ukuleletuner2.windowInfo.rememberWindowInfo
import java.io.File


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChordsScreen(
    onNavigateToSettings: () -> Unit
) {
    val windowInfo = rememberWindowInfo()

    val context = LocalContext.current
    val player = remember { AndroidAudioPlayer(context) }
    var playingChordId by remember { mutableIntStateOf(-1) }

    val PORTRAT_CHORD_COUNT = 2
    val LANDSCAPE_CHORD_COUNT = 4


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

    when(windowInfo.screenOrientation) {
       is WindowOrientation.Portrait -> {
           Scaffold(
               topBar = {
                   TopAppBar(
                       title = {
                           Text(stringResource(R.string.chords_screen_title))
                       },
                       colors = TopAppBarDefaults.topAppBarColors(
                           containerColor = MaterialTheme.colorScheme.primaryContainer,
                           titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                           navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                           actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                       ),
                       navigationIcon = {
                           IconButton(onClick = { /* todo */ }) {
                               Icon(
                                   Icons.Default.Menu,
                                   contentDescription = stringResource(R.string.menu_content_description)
                               )
                           }
                       },

                       actions = {
                           IconButton(onClick = onNavigateToSettings) {
                               Icon(
                                   Icons.Filled.Settings,
                                   contentDescription = stringResource(R.string.settings_content_description)
                               )
                           }
                       }
                   )
               },
               content = { paddingValues ->
                   ConstraintLayout(
                       modifier = Modifier
                           .fillMaxSize()
                           .padding(paddingValues)
                   ) {
                       LazyVerticalGrid(
                           columns = GridCells.Fixed(PORTRAT_CHORD_COUNT),
                           contentPadding = PaddingValues(8.dp),
                           verticalArrangement = Arrangement.spacedBy(8.dp),
                           horizontalArrangement = Arrangement.spacedBy(8.dp)
                       ) {
                           items(chords.size) { index ->
                               val chord = chords[index]

                               Box(
                                   modifier = Modifier
                                       .fillMaxWidth()
                                       .padding(8.dp)
                               ) {
                                   val chordName = stringResource(id = chord.name)
                                   ChordCard(
                                       painter = painterResource(id = chord.image),
                                       contentDescription = stringResource(R.string.chord_card_content_description),
                                       title = chordName,
                                       isPlaying = playingChordId == chord.id,
                                       onPlayed = {
                                           playingChordId = chord.id
                                           try {
                                               player.playResource(chord.audioFileName)
                                           } catch (e: Exception) {
                                               println("Error while playing: ${e.message}")
                                               playingChordId = -1
                                           }
                                       }
                                   )
                               }
                           }
                       }
                   }
               }
           )
       }
        is WindowOrientation.Landscape -> {

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(stringResource(R.string.chords_screen_title))
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        navigationIcon = {
                            IconButton(onClick = { /* todo */ }) {
                                Icon(
                                    Icons.Default.Menu,
                                    contentDescription = stringResource(R.string.menu_content_description)
                                )
                            }
                        },

                        actions = {
                            IconButton(onClick = onNavigateToSettings) {
                                Icon(
                                    Icons.Filled.Settings,
                                    contentDescription = stringResource(R.string.settings_content_description)
                                )
                            }
                        }
                    )
                },
                content = { paddingValues ->
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(LANDSCAPE_CHORD_COUNT),
                            contentPadding = PaddingValues(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(chords.size) { index ->
                                val chord = chords[index]

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                ) {
                                    val chordName = stringResource(id = chord.name)
                                    ChordCard(
                                        painter = painterResource(id = chord.image),
                                        contentDescription = stringResource(R.string.chord_card_content_description),
                                        title = chordName,
                                        isPlaying = playingChordId == chord.id,
                                        onPlayed = {
                                            playingChordId = chord.id
                                            try {
                                                player.playResource(chord.audioFileName)
                                            } catch (e: Exception) {
                                                println("Error while playing: ${e.message}")
                                                playingChordId = -1
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            )
        }
    }
}