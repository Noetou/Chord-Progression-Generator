package com.chordsGenerator.app


import AudioPlayerForMediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chordsGenerator.app.ui.theme.TestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // To generate the list of chords
            val chordList = ChordsUtils.generateChordList() // get the list of all the chords
            TestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF445069)
                ) {
                    Column {
                        // Header

                        Text(
                            stringResource(id = R.string.title),
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = FontFamily.Serif,
                                textAlign = TextAlign.Center,
                                color = Color(0xFF252B48)
                            ),
                            modifier = Modifier
                                .padding(start = 30.dp, top = 30.dp, end = 30.dp, bottom = 150.dp)
                                .fillMaxWidth()
                                .background(Color(0xFFF7E987), shape = RoundedCornerShape(20.dp))
                                .padding(15.dp)
                        )


                        // Display the starting page
                        HomePage(chordList)


                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun OnCreatePreview() {

    // To generate the list of chords
    val chordList = ChordsUtils.generateChordList() // get the list of all the chords
    TestTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFF445069)
        ) {
            Column {
                // Header

                Text(
                    stringResource(id = R.string.title),
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = FontFamily.Serif,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF252B48)
                    ),
                    modifier = Modifier
                        .padding(start = 30.dp, top = 30.dp, end = 30.dp, bottom = 150.dp)
                        .fillMaxWidth()
                        .background(Color(0xFFF7E987), shape = RoundedCornerShape(20.dp))
                        .padding(15.dp)
                )


                // Display the starting page
                HomePage(chordList)


            }
        }

    }
}


/*
* Display a pop-up to chose a chord between every chords in the list
*  */

@Composable
fun ChoseOneChord(
    list: ArrayList<Chord>,
    onDismiss: () -> Unit,
    onSelect: (Chord?) -> Unit,
) {
    BoxWithConstraints {
        //Determine the number of columns in the grid based on the device's width
        val col = when {
            maxWidth.value <= 400 -> 2
            maxWidth.value <= 450 -> 3
            maxWidth.value <= 700 -> 4
            else -> 5
        }

        // Search functionality
        var searchQuery by remember { mutableStateOf("") }

        // Display the pop-up

        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(stringResource(id = R.string.chose_chord)) },
            text = {
                Column {
                    // Search field
                    TextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = {
                            Text(
                                stringResource(id = R.string.search) + "\uD83D\uDD0D"
                            )
                        },
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth()
                    )

                    // To display the chords
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(col),
                    ) {

                        // Filter the list with the text of the search field
                        val filteredList = mutableListOf<Chord>()

                        for (accord in list) {
                            if (accord.name.contains(searchQuery, ignoreCase = true)) {
                                filteredList.add(accord)
                            }
                        }

                        // Display the filtered list of chords

                        items(filteredList) { accord ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            ) {
                                Image(
                                    painterResource(id = accord.tab),
                                    contentDescription = accord.name,

                                    modifier = Modifier
                                        .size(80.dp)
                                        .clickable {
                                            onSelect(accord)
                                        }
                                )
                                Text(
                                    text = accord.name,
                                    style = TextStyle(
                                        fontFamily = FontFamily.Serif,
                                        color = Color(0xFF252B48)
                                    )
                                )
                            }
                        }

                        // Add of a "None" option, to allow the user to delete a previous choice

                        item {
                            Text(
                                text = stringResource(id = R.string.none),
                                style = TextStyle(
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Center
                                ),
                                modifier = Modifier.clickable {
                                    onSelect(null)
                                })
                        }
                    }
                }
            },
            // Closing button
            confirmButton = {
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5B9A8B)),


                    ) {
                    Text(stringResource(id = R.string.close))
                }
            },
            modifier = Modifier
                .width(maxWidth)
                .height(maxHeight)
        )
    }

}

/* It ensures that all four chords aren't null, if a chord is null :
*   - If the first chord is set : it chooses a chord among the first chord's scale
*   - If another chord is set but not the first : it chooses a first chord which has all the set chords in its scale
*   - If none of the 4 chords are set  : chooses randomly the first chord among the list
 */
fun generateChords(
    list: ArrayList<Chord>,
    chordsStateList: ArrayList<MutableState<Chord?>>,
    generate: Int,
): ArrayList<Chord?> {
    // Add all four chords to a list, if a chord hasn't been set by the user beforehand, the chord is null
    val usedChords = ArrayList<Chord?>()
    for (elt in chordsStateList) {
        usedChords.add(elt.value)
    }

    // The logic if a chord is null

    // "if (generate > 0)" ensure the method doesn't display chords on the starting page

    if (generate > 0) {
        for (i in 0..3) {
            if (usedChords[i] == null) {
                var chord = list[(0..<list.size).random()]
                if (usedChords[0] != null) {

                    /* Ensure that the same chord can't be present twice in the same progression if not chosen by the user,
                    *  and that the chosen chord is in the scale of the firstChord */

                    while (usedChords.contains(chord) || !usedChords[0]?.scale
                            ?.contains(chord.name)!!
                    ) {
                        val chordName =
                            usedChords[0]?.scale
                                ?.get((0..<(usedChords[0]?.scale?.size!!)).random())
                        for (c in list) {
                            if (c.name == chordName) {
                                chord = c
                            }
                        }
                    }
                }
                // Ensure that the first chord has the chosen chords in its scale
                else if (usedChords[1] != null || usedChords[2] != null || usedChords[3] != null) {
                    for (c in usedChords) {
                        if (c != null) {
                            while (!chord.scale.contains(c.name)) {
                                chord = list[(0..<list.size).random()]
                            }
                        }
                    }
                }
                usedChords[i] = chord //replace null value by the chord
            }
        }
    }

    return usedChords
}

/*
*  It displays a grid containing different things depending on on "generate"'s state :
*
*   - If "generate"==0 : it displays the 4 "+" images
*   - If "generate" > 0 : it displays the chord progression instead
*  */
@Composable
fun DisplayChords(
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
        val availableWidth = maxWidth // adapt the width "+" symbols depending on the screen's size
        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            modifier = Modifier.height(availableHeight - 200.dp)
        ) {

            itemsIndexed(usedChords) { index, chord ->

                if (chord != null) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painterResource(id = chord.tab),
                            chord.name,
                            modifier = Modifier
                                .height((availableHeight - 100.dp) / 4)
                                .width((availableWidth - 100.dp) / 4)
                                .clickable { openDialog(index) }
                        )


                        Text(
                            chord.name,
                            Modifier.padding(horizontal = 65.dp),
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
                        "chord 1",
                        modifier = Modifier
                            .height((availableHeight - 200.dp) / 4)
                            .width((availableWidth - 200.dp) / 4)
                            .clickable { openDialog(index) }
                    )
                }

            }
        }
    }

}

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
            Row {
                Spacer(modifier = Modifier.weight(1f))
                DisplayChords(
                    list,
                    chordsStateList,
                    generate.intValue,
                    openDialog,
                    fun(chord: Chord) {

                        player.play(chord.sound)

                    }
                )
                Spacer(modifier = Modifier.weight(1f))
            }

            BoxWithConstraints {
                Column(verticalArrangement = Arrangement.Center) {
                    Spacer(modifier = Modifier.weight(1f))
                    Row {
                        Spacer(modifier = Modifier.weight(1f))

                        // "Reset" button
                        Button(
                            onClick = {
                                for (elt in chordsStateList) {
                                    elt.value = null
                                }
                                generate.intValue = 0
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5B9A8B))
                        ) {
                            Text(
                                stringResource(id = R.string.reset),
                                style = TextStyle(
                                    fontFamily = FontFamily.Serif,
                                    color = Color(0xFF252B48)
                                ),
                            )
                        }


                        Spacer(modifier = Modifier.weight(1f))
                    }
                    Row {

                        Spacer(modifier = Modifier.weight(1f))

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
                                    color = Color(0xFF252B48)
                                )
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))
                    }

                }
            }


        }
    }
}

