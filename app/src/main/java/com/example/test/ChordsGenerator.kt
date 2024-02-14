package com.example.test

import ChordsUtils
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Row {
                            Spacer(Modifier.weight(1f))
                            Text(
                                "Générateur d'accord",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,

                                    )
                            )
                            Spacer(Modifier.weight(1f))
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        HomePage(chordList)
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
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                Row {
                    Spacer(Modifier.weight(1f))
                    Text(
                        "Générateur d'accord",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,

                            )
                    )
                    Spacer(Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.weight(1f))
                HomePage(chordList)
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
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Choisir un accord") },
        text = {
            LazyHorizontalGrid(
                rows = GridCells.Fixed(5),
            ) {
                items(liste) { accord ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    ) {
                        Image(
                            painterResource(id = accord.getTab()),
                            contentDescription = accord.getNom()
                        )
                        Text(
                            text = accord.getNom(),
                            modifier = Modifier
                                .clickable {
                                    onSelect(accord)
                                }
                        )
                    }
                }
                item {
                    Text(
                        text = "Aucun",
                        style = TextStyle(fontWeight = FontWeight.ExtraBold),
                        modifier = Modifier.clickable {
                            onSelect(null)
                        })
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Fermer")
            }
        },
        modifier = Modifier
            .width(350.dp)
            .height(600.dp)
    )
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
                        if(accord != null){
                            while(!chord.getGamme().contains(accord.getNom())){
                               chord = liste[(0..<liste.size).random()]
                            }
                        }
                    }
                }
                usedChords[i] = chord
            }
        }
    }

// create components : 2 row of 2 chords with their tabs
    LazyHorizontalGrid(rows = GridCells.Fixed(2), modifier = Modifier.height(400.dp)) {

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
                            textAlign = TextAlign.Center
                        )
                    )
                } else {
                    Image(
                        painterResource(id = R.drawable.baseline_add_24),
                        "chord 1",
                        modifier = Modifier
                            .size(150.dp)
                            .clickable { openDialog(index) }
                    )
                }
            }
        }
    }
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
    val coroutineScope = rememberCoroutineScope()
    val closeDialog: () -> Unit = { dialog.value = false }
    val openDialog: (Int) -> Unit = {
        dialog.value = true
        emplacement.intValue = it
    }

    val selectChord: (Accord?) -> Unit = { accord ->
        if (emplacement.intValue == 0) {
            firstChord.value = accord
        } else if (emplacement.intValue == 1) {
            secondChord.value = accord
        } else if (emplacement.intValue == 2) {
            thirdChord.value = accord
        } else {
            fourthChord.value = accord
        }
        dialog.value = false
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        //show chords list and allow the user to choose the first chord of the progression
        if (dialog.value) {
            ChoseOneChord(liste, closeDialog, selectChord)
        }

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
                generate.value,
                openDialog
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.weight(1f))
        Row {

            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = {
                generate.intValue++ //to regenerate the page each time the button is clicked with new chords
            }, shape = CutCornerShape(10), modifier = Modifier.width(300.dp)) {
                Text("Générer")
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

