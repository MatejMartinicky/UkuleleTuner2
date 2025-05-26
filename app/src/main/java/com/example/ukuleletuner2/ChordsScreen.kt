package com.example.ukuleletuner2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChordsScreen() {
    val chords = listOf(
        Chord("C", R.drawable.ukulele_c_chord, "C.mp3")
    )

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
                        ChordCard(
                            painter = painterResource(id = chord.image),
                            contentDescription = "${chord.name} chord",
                            title = chord.name,
                            chordName = chord.audioFileName,
                            OnPlayed = { chordAudioFile ->
                                println("playing like that cord!")
                            }
                        )
                    }
                }
            }
        }
    )
}