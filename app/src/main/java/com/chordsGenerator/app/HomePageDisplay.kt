package com.chordsGenerator.app

import AudioPlayerForMediaPlayer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// Recreate the page each time the activity is refreshed
@Composable
fun HomePage(list: ArrayList<Chord>) {
    val player = AudioPlayerForMediaPlayer(LocalContext.current)

    val chordsStateList = arrayListOf<MutableState<Chord?>>(
        remember { mutableStateOf(null) }, // the value of the first chord
        remember { mutableStateOf(null) }, // the value of the second chord
        remember { mutableStateOf(null) }, // the value of the third chord
        remember { mutableStateOf(null) }) // the value of the fourth chord
    val generate = remember { mutableIntStateOf(0) } // handles the reset of the application

    val dialog =
        remember { mutableStateOf(false) } // remembers whether the AlertDialog is shown or not
    val slot =
        remember { mutableIntStateOf(0) } // remembers the chosen slot when the user chose a chord

    val closeDialog: () -> Unit = { dialog.value = false }
    val openDialog: (Int) -> Unit = {
        dialog.value = true
        slot.intValue = it
    }

    // when a chord is selected, put its value into the right chord depending on the chosen slot
    val selectChord: (Chord?) -> Unit = { chord ->
        chordsStateList[slot.intValue].value = chord
        dialog.value = false
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Show chords list and allow the user to choose the first chord of the progression
        if (dialog.value) {
            ChoseOneChord(list, closeDialog, selectChord)

        } else {
            Spacer(modifier = Modifier.weight(1f))


            Display(
                list,
                chordsStateList,
                generate.intValue,
                openDialog,
                fun(chord: Chord) {

                    player.play(chord.sound)

                }
            )

            BoxWithConstraints {
                val availableWidth = maxWidth
                // "Reset" button
                Button(
                    onClick = {
                        for (elt in chordsStateList) {
                            elt.value = null
                        }
                        generate.intValue = 0
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5B9A8B)),
                    modifier = Modifier.width(availableWidth /4)
                ) {
                    Text(
                        stringResource(id = R.string.reset),
                        style = TextStyle(
                            fontFamily = FontFamily.Serif,
                            color = Color(0xFF252B48),
                            fontWeight = FontWeight.Bold
                        ),
                    )
                }
            }




            // "Generate" button
            Button(
                onClick = {
                    generate.intValue++ /*to regenerate the page each time the button is
                                                      clicked with new chords, without generating the "+" images */
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5B9A8B)),
                shape = CutCornerShape(10),
                modifier = Modifier
                    .padding(30.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    stringResource(id = R.string.generate), style = TextStyle(
                        fontFamily = FontFamily.Serif,
                        color = Color(0xFF252B48),
                        fontWeight = FontWeight.Bold
                    )
                )
            }


        }


    }
}

