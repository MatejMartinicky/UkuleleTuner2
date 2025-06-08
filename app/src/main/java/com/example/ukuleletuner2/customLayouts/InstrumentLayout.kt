/**
 * Author: Matej Martinicky
 */

package com.example.ukuleletuner2.customLayouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ukuleletuner2.R
import com.example.ukuleletuner2.ui.components.buttons.NoteButton
import com.example.ukuleletuner2.ui.components.images.InstrumentImage
import com.example.ukuleletuner2.ui.screens.TunerScreenPackage.UkuleleString
import com.example.ukuleletuner2.ui.theme.ukuleleA4
import com.example.ukuleletuner2.ui.theme.ukuleleC4
import com.example.ukuleletuner2.ui.theme.ukuleleE4
import com.example.ukuleletuner2.ui.theme.ukuleleG4

/**
 * Composable that displays ukulele Tuner head with proper buttons that play correct
 * sounding note when clicked
 *
 * @param modifier modifier applied to the layout
 * @param strings lists of strings containing strings
 * @param playingStringId ID of string that is currently playing
 * @param onStringPlayed callback that plays correctly sounding note when clicked
 */
@Composable
fun InstrumentLayout(
    modifier: Modifier = Modifier,
    strings: List<UkuleleString>,
    playingStringId: Int,
    onStringPlayed: (UkuleleString) -> Unit
) {
    var imageSize by remember { mutableStateOf(IntSize.Zero) }

    //organizes components in constrained layout so there is bigger control over positioning
    ConstraintLayout(
        modifier = modifier
    ) {
        //creates references for  constrained layout and on this it can be later referenced
        //example: Modifier.constrainAs(box1) { top.linkTo(box2.bottom) }
        //means: "I am box1" -> "put my top edge at box2's bottom edge"
        val (image, buttonC, buttonG, buttonE, buttonA) = createRefs()

        InstrumentImage(
            painter = painterResource(id = R.drawable.ukulele_head),
            contentDescription = stringResource(R.string.ukulele_image_description),
            modifier = Modifier.constrainAs(image) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            onSizeChanged = { size ->
                imageSize = size
            },
            sizeX = 250.dp,
            sizeY = 400.dp
        )

        //finds specific ukulele strings from list of IDs
        val noteC = strings.find { it.id == 1 }
        val noteG = strings.find { it.id == 2 }
        val noteE = strings.find { it.id == 3 }
        val noteA = strings.find { it.id == 4 }

        val imageSizeDp = with(LocalDensity.current) {
            IntSize(imageSize.width.toDp().value.toInt(), imageSize.height.toDp().value.toInt())
        }

        val MARGIN_FROM_TOP = (imageSizeDp.height * 0.16f).dp
        val MARGIN_FROM_PREVIOUS = (imageSizeDp.height * 0.06f).dp

        NoteButton(
            letter = stringResource(R.string.C_button),
            color = ukuleleC4,
            onClick = {
                //safe call operator only gets executed when noteC is not null
                //onStringPlayed(noteC) this might class if null
                //it is implicit parameter automatically available in lambda expressions
                noteC?.let {
                    onStringPlayed(it)
                }
            },
            modifier = Modifier.constrainAs(buttonC) {
                top.linkTo(image.top, margin = MARGIN_FROM_TOP)
                end.linkTo(image.start)
                start.linkTo(parent.start)
            }
        )

        NoteButton(
            letter = stringResource(R.string.G_button),
            color = ukuleleG4,
            onClick = {
                //doesn't crash when null
                noteG?.let {
                    onStringPlayed(it)
                }
            },
            modifier = Modifier.constrainAs(buttonG) {
                top.linkTo(buttonC.bottom, margin = MARGIN_FROM_PREVIOUS)
                end.linkTo(image.start)
                start.linkTo(parent.start)
            }
        )

        NoteButton(
            letter = stringResource(R.string.E_button),
            color = ukuleleE4,
            onClick = {
                //doesn't crash when null
                noteE?.let {
                    onStringPlayed(it)
                }
            },
            modifier = Modifier.constrainAs(buttonE) {
                top.linkTo(image.top, margin = MARGIN_FROM_TOP)
                start.linkTo(image.end)
                end.linkTo(parent.end)
            }
        )

        NoteButton(
            letter = stringResource(R.string.A_button),
            color = ukuleleA4,
            onClick = {
                //doesn't crash when null
                noteA?.let {
                    onStringPlayed(it)
                }
            },
            modifier = Modifier.constrainAs(buttonA) {
                top.linkTo(buttonE.bottom, margin = MARGIN_FROM_PREVIOUS)
                start.linkTo(image.end)
                end.linkTo(parent.end)
            }
        )
    }
}