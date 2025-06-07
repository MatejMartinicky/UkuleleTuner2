package com.example.ukuleletuner2.ChordsScreenPackage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ukuleletuner2.R
import com.example.ukuleletuner2.chords.Chord
import com.example.ukuleletuner2.ui.components.cards.ChordCard


@Composable
internal fun ChordGridItem(
    chord: Chord,
    isPlaying: Boolean,
    onPlayed: () -> Unit
) {
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
            isPlaying = isPlaying,
            onPlayed = onPlayed
        )
    }
}