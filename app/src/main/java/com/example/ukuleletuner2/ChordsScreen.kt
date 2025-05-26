package com.example.ukuleletuner2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.ukuleletuner2.audioplayer.AndroidAudioPlayer
import com.example.ukuleletuner2.audioplayer.AudioPlayer
import java.io.File


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChordsScreen() {
    val context = LocalContext.current
    val player = remember { AndroidAudioPlayer(context) }

    val chords = listOf(
        Chord("C", R.drawable.ukulele_c_chord, R.raw.guitar_a_major) //change when required
    )

    DisposableEffect(Unit) {
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

                LazyColumn {
                    items(items = chords) { chord ->
                        Box(modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(16.dp)
                        ) {
                            ChordCard(
                                painter = painterResource(id = chord.image),
                                contentDescription = "${chord.name} chord",
                                title = chord.name,
                                chordName = chord.name,
                                OnPlayed = { chordAudioFile ->
                                    val tempFile = File(context.cacheDir, "chord_${chord.name}.wav")

                                    try {
                                        context.resources.openRawResource(chord.audioFileName).use { input ->
                                            tempFile.outputStream().use { output ->
                                                input.copyTo(output)
                                            }
                                        }
                                        player.playFile(tempFile)
                                    } catch (e: Exception) {
                                        println("Error while palying: ${e.message}") //log
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