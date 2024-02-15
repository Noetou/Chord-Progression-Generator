package com.example.test

import ChordsUtils
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
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.ui.theme.TestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val chordList = ChordsUtils.generateChordList() // get the list of all the chords
            TestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF445069)
                ) {
                    Column {
                        Row(
                            Modifier
                                .padding(start = 30.dp, top = 30.dp, end = 30.dp, bottom = 150.dp)
                                .fillMaxWidth()
                                .background(Color(0xFFF7E987), shape = RoundedCornerShape(20.dp))
                                .padding(15.dp)
                        ) {
                            Spacer(Modifier.weight(1f))
                            Text(
                                stringResource(id = R.string.title),
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontFamily = FontFamily.Serif,
                                    textAlign = TextAlign.Center,
                                    color = Color(0xFF252B48)
                                )
                            )
                            Spacer(Modifier.weight(1f))
                        }
                        Spacer(Modifier.weight(1f))

                        HomePage(chordList)
                        Spacer(Modifier.weight(1f))


                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val chordList = ChordsUtils.generateChordList() // get the list of all the chords
    TestTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFF445069)
        ) {
            Column {
                Row(
                    Modifier
                        .padding(start = 30.dp, top = 30.dp, end = 30.dp, bottom = 150.dp)
                        .fillMaxWidth()
                        .background(Color(0xFFF7E987), shape = RoundedCornerShape(20.dp))
                        .padding(15.dp)
                ) {
                    Spacer(Modifier.weight(1f))
                    Text(
                        stringResource(id = R.string.title),
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = FontFamily.Serif,
                            textAlign = TextAlign.Center,
                            color = Color(0xFF252B48)
                        )
                    )
                    Spacer(Modifier.weight(1f))
                }
                Spacer(Modifier.weight(1f))
                HomePage(chordList)
                Spacer(Modifier.weight(1f))

            }
        }

    }
}


@Composable
fun ChoseOneChord(
    liste: ArrayList<Accord>,
    onDismiss: () -> Unit,
    onSelect: (Accord?) -> Unit,
) {
    BoxWithConstraints {
        val col = when {
            maxWidth.value <= 400 -> 2
            maxWidth.value <= 450 -> 3
            maxWidth.value <= 700 -> 4
            else -> 5
        }


        var searchQuery by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(stringResource(id = R.string.chose_chord)) },
            text = {
                Column{
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text(
                        stringResource(id = R.string.search) + "\uD83D\uDD0D") },
                    modifier = Modifier.padding(20.dp).fillMaxWidth()
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(col),
                ) {
                    val filteredList = mutableListOf<Accord>()

                    for (accord in liste) {
                        if (accord.getNom().contains(searchQuery, ignoreCase = true)) {
                            filteredList.add(accord)
                        }
                    }
                    items(filteredList) { accord ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        ) {
                            Image(
                                painterResource(id = accord.getTab()),
                                contentDescription = accord.getNom(),

                                modifier = Modifier
                                    .size(80.dp)
                                    .clickable {
                                        onSelect(accord)
                                    }
                            )
                            Text(
                                text = accord.getNom(),
                                style = TextStyle(
                                    fontFamily = FontFamily.Serif,
                                    color = Color(0xFF252B48)
                                )
                            )
                        }
                    }
                    item {
                        Text(
                            text = stringResource(id = R.string.none),
                            style = TextStyle(fontWeight = FontWeight.ExtraBold, fontSize = 18.sp, textAlign = TextAlign.Center),
                            modifier = Modifier.clickable {
                                onSelect(null)
                            })
                    }
                }}
            },
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


@Composable
fun GenerateChords(
    liste: ArrayList<Accord>,
    firstChord: Accord?,
    secondChord: Accord?,
    thirdChord: Accord?,
    fourthChord: Accord?,
    generate: Int,
    openDialog: (Int) -> Unit
) {
    val usedChords = ArrayList<Accord?>()
    usedChords.add(firstChord)
    usedChords.add(secondChord)
    usedChords.add(thirdChord)
    usedChords.add(fourthChord)

    if (generate > 0) {
        for (i in 0..3) {
            if (usedChords[i] == null) {
                var chord = liste[(0..<liste.size).random()]
                if (usedChords[0] != null) {
                    while (usedChords.contains(chord) || !usedChords[0]?.getGamme()
                            ?.contains(chord.getNom())!!
                    ) {
                        val chordName =
                            usedChords[0]?.getGamme()
                                ?.get((0..<(usedChords[0]?.getGamme()?.size!!)).random())
                        for (accord in liste) {
                            if (accord.getNom() == chordName) {
                                chord = accord
                            }
                        }
                    }
                } else if (usedChords[1] != null || usedChords[2] != null || usedChords[3] != null) {
                    for (accord in usedChords) {
                        if (accord != null) {
                            while (!chord.getGamme().contains(accord.getNom())) {
                                chord = liste[(0..<liste.size).random()]
                            }
                        }
                    }
                }
                usedChords[i] = chord
            }
        }
    }

    BoxWithConstraints {
        val availableHeight = maxHeight
        val availableWidth = maxWidth
        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            modifier = Modifier.height(availableHeight - 200.dp)
        ) {

            itemsIndexed(usedChords) { index, chord ->
                Column {
                    if (chord != null) {
                        Image(
                            painterResource(id = chord.getTab()),
                            chord.getNom(),
                            modifier = Modifier
                                .size(150.dp)
                                .clickable { openDialog(index) }
                        )

                        Text(
                            chord.getNom(),
                            Modifier.padding(horizontal = 65.dp),
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                color = Color(0xFF252B48)
                            ),
                        )

                    } else {
                        Image(
                            painterResource(id = R.drawable.plus),
                            "chord 1",
                            modifier = Modifier
                                .height((availableHeight - 200.dp) / 4)
                                .width((availableWidth - 200.dp) /4)
                                .clickable { openDialog(index) }
                        )
                    }
                }
            }
        }
    }
// create components : 2 row of 2 chords with their tabs

}

// create the page each time the activity is refreshed
@Composable
fun HomePage(liste: ArrayList<Accord>) {
    val generate = remember { mutableIntStateOf(0) }
    val firstChord = remember { mutableStateOf<Accord?>(null) }
    val secondChord = remember { mutableStateOf<Accord?>(null) }
    val thirdChord = remember { mutableStateOf<Accord?>(null) }
    val fourthChord = remember { mutableStateOf<Accord?>(null) }
    val dialog = remember { mutableStateOf(false) }
    val emplacement = remember { mutableIntStateOf(0) }
    val closeDialog: () -> Unit = { dialog.value = false }
    val openDialog: (Int) -> Unit = {
        dialog.value = true
        emplacement.intValue = it
    }

    val selectChord: (Accord?) -> Unit = { accord ->
        when (emplacement.intValue) {
            0 -> firstChord.value = accord
            1 -> secondChord.value = accord
            2 -> thirdChord.value = accord
            3 -> fourthChord.value = accord
        }
        dialog.value = false
    }



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        //show chords list and allow the user to choose the first chord of the progression
        if (dialog.value) {
            ChoseOneChord(liste, closeDialog, selectChord)

        } else {


            //if the button has already been pressed, replace the previous chords with new ones

            Spacer(modifier = Modifier.weight(1f))
            Row {
                Spacer(modifier = Modifier.weight(1f))
                GenerateChords(
                    liste,
                    firstChord.value,
                    secondChord.value,
                    thirdChord.value,
                    fourthChord.value,
                    generate.intValue,
                    openDialog
                )
                Spacer(modifier = Modifier.weight(1f))
            }

            BoxWithConstraints {
                val availableWidth = maxWidth
                Column(verticalArrangement = Arrangement.Center) {
                    Spacer(modifier = Modifier.weight(1f))
                    Row {
                        Spacer(modifier = Modifier.weight(1f))
                        Button(
                            onClick = {
                                firstChord.value = null
                                secondChord.value = null
                                thirdChord.value = null
                                fourthChord.value = null
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

                        Button(
                            onClick = {
                                generate.intValue++ //to regenerate the page each time the button is clicked with new chords
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

