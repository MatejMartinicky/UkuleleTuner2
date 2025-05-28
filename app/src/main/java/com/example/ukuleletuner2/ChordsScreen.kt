package com.example.ukuleletuner2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.ukuleletuner2.chords.ChordCard
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.ukuleletuner2.audioplayer.AndroidAudioPlayer
import java.io.File


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChordsScreen() {
    val context = LocalContext.current
    val player = remember { AndroidAudioPlayer(context) }
    var playingChordId by remember { mutableIntStateOf(-1) }

    val chords = remember {
        listOf(
            Chord(1, R.string.ukulele_c, R.drawable.ukulele_c, R.raw.guitar_a_major),
            Chord(2, R.string.ukulele_f, R.drawable.ukulele_f, R.raw.guitar_a_major),
            Chord(3, R.string.ukulele_g, R.drawable.ukulele_g, R.raw.guitar_a_major),
            Chord(4, R.string.ukulele_e, R.drawable.ukulele_e, R.raw.guitar_a_major),
            Chord(5, R.string.ukulele_a, R.drawable.ukulele_a, R.raw.guitar_a_major),
            Chord(6, R.string.ukulele_b, R.drawable.ukulele_b, R.raw.guitar_a_major),
            Chord(7, R.string.ukulele_am, R.drawable.ukulele_am, R.raw.guitar_a_major),
            Chord(8, R.string.ukulele_em, R.drawable.ukulele_em, R.raw.guitar_a_major),
            Chord(9, R.string.ukulele_bm, R.drawable.ukulele_bm, R.raw.guitar_a_major),
            Chord(10, R.string.ukulele_dm, R.drawable.ukulele_dm, R.raw.guitar_a_major),
            Chord(11, R.string.ukulele_c7, R.drawable.ukulele_c7, R.raw.guitar_a_major),
            Chord(12, R.string.ukulele_e7, R.drawable.ukulele_e7, R.raw.guitar_a_major),
            Chord(13, R.string.ukulele_a7, R.drawable.ukulele_a7, R.raw.guitar_a_major),
            Chord(14, R.string.ukulele_d7, R.drawable.ukulele_d7, R.raw.guitar_a_major)
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("") //fix
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF66BB6A)
                ),
                navigationIcon = {
                    IconButton(onClick = { /* todo */ }) {
                        Icon(
                            Icons.Default.Menu,
                            contentDescription = stringResource(R.string.menu_content_description),
                            tint = Color.White
                        )
                    }
                },

                actions = {
                    IconButton(onClick = { /*todo*/ }) {
                        Icon(
                            Icons.Filled.Settings,
                            contentDescription = stringResource(R.string.settings_content_description),
                            tint = Color.White
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .padding(paddingValues)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
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
                                contentDescription = "${chord.name} chord",
                                title = chordName,
                                isPlaying = playingChordId == chord.id,
                                onPlayed = {
                                    playingChordId = chord.id
                                    val tempFile = File(context.cacheDir, "chord_${chord.name}.wav")
                                    try {
                                        context.resources.openRawResource(chord.audioFileName)
                                            .use { input ->
                                                tempFile.outputStream().use { output ->
                                                    input.copyTo(output)
                                                }
                                            }

                                        player.playFile(tempFile)
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