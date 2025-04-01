package com.example.ukuleletuner2

import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.withContext

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.app.ActivityCompat
import com.example.ukuleletuner2.fastFurierTransform.FourierTransform
import com.example.ukuleletuner2.recorder.AndroidAudioRecorder
import com.example.ukuleletuner2.recorder.AudioRecorder
import kotlinx.coroutines.Dispatchers
import kotlin.math.abs

class MainActivity : ComponentActivity() {
    private var isRecording by mutableStateOf(false)
    private val sampleRate = 44100
    private val bufferSize = 2048
    private val audioRecorder: AudioRecorder = AndroidAudioRecorder()
    private val fourierTransform = FourierTransform(sampleRate, bufferSize)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Request microphone permission
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.RECORD_AUDIO),
            0
        )

        setContent {
            TunerScreen()
        }
    }

    //https://developer.android.com/develop/ui/compose/touch-input/user-interactions/handling-interactions
    @Composable
    fun NoteButton(
        letter: String,
        color: Color,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
    ) {
        val interactionSource = remember { MutableInteractionSource() }
        val isPressed: Boolean by interactionSource.collectIsPressedAsState()

        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .size(64.dp)
                .background(Color.Transparent, CircleShape)
                .border(4.dp, if (isPressed) Color.Gray else color, CircleShape)
                .fillMaxWidth()
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onClick()
                }
        ) {
            Text(
                text = letter,
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

    @Composable
    fun InstrumentImage(
        painter: Painter,
        contentDescription: String,
        modifier: Modifier = Modifier,
        onSizeChanged: (IntSize) -> Unit,
    ) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .wrapContentWidth()
                    .onGloballyPositioned { layoutCoordinates ->
                        val size = layoutCoordinates.size
                        onSizeChanged(size)
                }
            )
        }
    }

    @Composable
    fun InstrumentLayout() {
        var imageSize by remember { mutableStateOf(IntSize.Zero) }

        ConstraintLayout(
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

    @Preview
    @Composable
    fun TunerScreen() {
        var detectedFrequency by remember { mutableStateOf(0.0) }
        var tuningStatus by remember { mutableStateOf("Waiting...") }

        LaunchedEffect(isRecording) {
            if (isRecording) {
                withContext(Dispatchers.IO) {
                    val buffer = ShortArray(bufferSize)
                    audioRecorder.start()
                    while (isRecording) {
                        val read = audioRecorder.read(buffer)
                        if (read > 0) {
                            detectedFrequency = fourierTransform.processFFT(buffer)
                            tuningStatus = evaluateTuning(detectedFrequency)
                        }
                    }
                    audioRecorder.stop()
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Ukulele Tuner", color = Color.White, style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(20.dp))
            Text("Detected Frequency: ${"%.2f".format(detectedFrequency)} Hz", color = Color.Gray)
            Text("Tuning: $tuningStatus", color = Color.Black, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { isRecording = !isRecording }) {
                Text(if (isRecording) "Stop" else "Start Tuning")
            }

            InstrumentLayout()
        }
    }

    private fun evaluateTuning(frequency: Double): String {
        val ukuleleNotes = mapOf(
            "G4" to 392.00, "C4" to 261.63,
            "E4" to 329.63, "A4" to 440.00
        )
        val closestNote = ukuleleNotes.minByOrNull { (_, noteFreq) ->
            abs(noteFreq - frequency)
        }
        if (closestNote == null) return "Unknown"
        val difference = frequency - closestNote.value

        return when {
            difference < -10 -> "Too Low (${closestNote.key})"
            difference > 10 -> "Too High (${closestNote.key})"
            else -> "In Tune (${closestNote.key})"
        }
    }
}