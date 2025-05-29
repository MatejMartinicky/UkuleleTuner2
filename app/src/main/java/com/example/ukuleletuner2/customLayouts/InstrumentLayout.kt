package com.example.ukuleletuner2.customLayouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ukuleletuner2.R
import com.example.ukuleletuner2.ui.components.buttons.NoteButton
import com.example.ukuleletuner2.ui.components.images.InstrumentImage


@Composable
fun InstrumentLayout(
    modifier: Modifier = Modifier
) {
    var imageSize by remember { mutableStateOf(IntSize.Zero) }

    ConstraintLayout(
        modifier = modifier
    ) {
        val (image, buttonC, buttonG, buttonE, buttonA) = createRefs()

        InstrumentImage(
            painter = painterResource(id = R.drawable.ukulele_head),
            contentDescription = "Ukulele head",
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

        val imageSizeDp = with(LocalDensity.current) {
            IntSize(imageSize.width.toDp().value.toInt(), imageSize.height.toDp().value.toInt())
        }

        NoteButton(
            letter = "C",
            color = Color(0xFF67999A),
            onClick = { println("cat") },
            modifier = Modifier.constrainAs(buttonC) {
                top.linkTo(image.top, margin = (imageSizeDp.height * 0.2f).dp)
                end.linkTo(image.start)
                start.linkTo(parent.start)
            }
        )

        NoteButton(
            letter = "G",
            color = Color(0xFF509073),
            onClick = { println("cat") },
            modifier = Modifier.constrainAs(buttonG) {
                top.linkTo(buttonC.bottom, margin = (imageSizeDp.height * 0.06f).dp)
                end.linkTo(image.start)
                start.linkTo(parent.start)
            }
        )

        NoteButton(
            letter = "E",
            color = Color(0xFFE78e22),
            onClick = { println("cat") },
            modifier = Modifier.constrainAs(buttonE) {
                top.linkTo(image.top, margin = (imageSizeDp.height * 0.2f).dp)
                start.linkTo(image.end)
                end.linkTo(parent.end)
            }
        )

        NoteButton(
            letter = "A",
            color = Color(0xFFE52625),
            onClick = { println("cat") },
            modifier = Modifier.constrainAs(buttonA) {
                top.linkTo(buttonE.bottom, margin = (imageSizeDp.height * 0.06f).dp)
                start.linkTo(image.end)
                end.linkTo(parent.end)
            }
        )
    }
}