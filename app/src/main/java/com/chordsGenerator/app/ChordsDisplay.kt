package com.chordsGenerator.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



/*
*  It displays a grid containing different things depending on on "generate"'s state :
*
*   - If "generate"==0 : it displays the 4 "+" images
*   - If "generate" > 0 : it displays the chord progression instead
*  */


@Composable
fun Display(
    list: ArrayList<Chord>,
    chordsStateList: ArrayList<MutableState<Chord?>>,
    generate: Int,
    openDialog: (Int) -> Unit,
    soundButtonOnClick: (Chord) -> Unit
) {


    val usedChords =
        generateChords(list, chordsStateList, generate)


    // Display the grid based on "generate"'s value
    BoxWithConstraints {
        val availableHeight =
            maxHeight // adapt the height of "+" symbols depending on the screen's size
        val availableWidth =
            maxWidth // adapt the width "+" symbols depending on the screen's size
        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.height(availableHeight - 200.dp)
        ) {

            itemsIndexed(usedChords) { index, chord ->

                if (chord != null) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .height(availableHeight)
                            .width(availableWidth / 2)
                    ) {
                        Image(
                            painterResource(id = chord.tab),
                            chord.name,
                            modifier = Modifier
                                .width((availableWidth) / 4)
                                .height((availableHeight) / 4)
                                .clickable { openDialog(index) }
                        )
                        Text(
                            chord.name,
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                color = Color(0xFF252B48)
                            ),
                        )

                        Button(onClick = { soundButtonOnClick(chord) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(
                                    0xFF5B9A8B
                                )
                            ),
                            content = {
                                Icon(
                                    painterResource(id = R.drawable.volume_up),
                                    contentDescription = chord.name
                                )
                            })

                    }

                } else {
                    Image(
                        painterResource(id = R.drawable.plus),
                        "chord ${index +1}",
                        modifier = Modifier
                            .height((availableHeight -200.dp) / 2)
                            .width((availableWidth - 200.dp)  / 2)
                            .clickable { openDialog(index) }
                    )
                }

            }
        }
    }
}



