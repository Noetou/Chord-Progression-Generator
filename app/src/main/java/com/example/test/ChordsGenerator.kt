package com.example.test

import ChordsUtils
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            var chordList = ChordsUtils.generateChordList() // get the list of all the chords
            val chosenChords = remember { mutableStateOf(0) }
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


@Composable
fun ChoseOneChord() {

}

@Composable
fun GenerateChords(liste: ArrayList<Accord>) {
    var usedChords = ArrayList<Accord>()

    for (i in 1..4) {
        var chord = liste[(0..<liste.size).random()]
        if (usedChords.size != 0) {

            while (usedChords.contains(chord) || !usedChords[0].getGamme().contains(chord.getNom())
            ) {

                val chordName =
                    usedChords[0].getGamme()[(0..<usedChords[0].getGamme().size).random()]
                for (accord in liste) {
                    if (accord.getNom() == chordName) {
                        chord = accord
                    }
                }
            }
        }
        usedChords.add(chord)
    }
// create components : 2 row of 2 chords with their tabs
    Column {
        Row {
            for (i in 0..1) {
                Column {
                    Image(
                        painterResource(id = usedChords[i].getTab()),
                        usedChords[i].getNom(),
                        modifier = Modifier.size(150.dp)
                    )
                    Text(
                        usedChords[i].getNom(),
                        Modifier.padding(horizontal = 65.dp),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
        Row {
            for (i in 2..3) {
                Column {
                    Image(
                        painterResource(id = usedChords[i].getTab()),
                        usedChords[i].getNom(),
                        modifier = Modifier.size(150.dp)
                    )

                    Text(
                        usedChords[i].getNom(),
                        Modifier.padding(horizontal = 65.dp),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }


}


@Composable
fun HomePage(liste: ArrayList<Accord>) {
    val etat = remember { mutableStateOf(0) }
    val selectedChord = remember { mutableStateOf<Accord?>(null) }
    val showDialog = remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = { showDialog.value = true },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Sélectionner un accord")
        }


        //show chords list and allow the user to chose the first chord of the progression
        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = { Text("Sélectionnez un accord") },
                text = {
                    LazyHorizontalGrid(rows = GridCells.Fixed(6)) {
                        items(liste) { accord ->
                            Column (horizontalAlignment = Alignment.CenterHorizontally){

                                Text(
                                    text = accord.getNom(),
                                    modifier = Modifier
                                        .clickable {
                                            selectedChord.value = accord
                                            showDialog.value = false
                                        }
                                        .padding(horizontal = 20.dp)
                                )
                                Image(
                                    painterResource(id = accord.getTab()),
                                    contentDescription = accord.getNom()
                                )

                            }
                        }
                    }
                },
                confirmButton = {
                    Button(
                        onClick = { showDialog.value = false },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text("Fermer")
                    }
                },

                )
        }

        // Afficher l'accord sélectionné (facultatif)
        selectedChord.value?.let {
            Text("Accord sélectionné: ${it.getNom()}")
        }
        if (etat.value > 0) {
            Spacer(modifier = Modifier.weight(1f))
            Row {
                Spacer(modifier = Modifier.weight(1f))

                GenerateChords(liste)
                Spacer(modifier = Modifier.weight(1f))

            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Row {

            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = {
                if (etat.value == 0) {
                    etat.value = 1
                } else {
                    etat.value++
                }
            }, shape = CutCornerShape(10), modifier = Modifier.width(300.dp)) {
                Text("Générer")
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    var chordList = ChordsUtils.generateChordList()
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

