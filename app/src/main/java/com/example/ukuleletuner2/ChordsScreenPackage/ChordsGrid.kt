package com.example.ukuleletuner2.ChordsScreenPackage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ukuleletuner2.chords.Chord

@Composable
internal fun ChordsGrid(
    chords: List<Chord>,
    playingChordId: Int,
    columnCount: Int,
    paddingValues: PaddingValues,
    onChordPlayed: (Chord) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(columnCount),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(chords.size) { index ->
                val chord = chords[index]
                ChordGridItem(
                    chord = chord,
                    isPlaying = playingChordId == chord.id,
                    onPlayed = { onChordPlayed(chord) }
                )
            }
        }
    }
}