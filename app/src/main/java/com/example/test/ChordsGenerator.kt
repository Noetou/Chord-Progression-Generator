package com.example.test

import ChordsUtils
import android.os.Bundle
import android.util.Log
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
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    onSelect: (Accord) -> Unit
) {
    AlertDialog(
        onDismissRequest =  onDismiss,
        title = { Text("Choisir le premier accord") },
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
            }
        },
        confirmButton = {
            Button(
                onClick =  onDismiss ,
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
fun GenerateChords(liste: ArrayList<Accord>, nbChordsToGenerate: Int, firstChord : Accord?,secondChord : Accord?,thirdChord : Accord?,fourthChord : Accord?) {
    val usedChords = ArrayList<Accord>()
    if(firstChord != null){
        usedChords.add(firstChord)
    }
    if(secondChord != null){
        usedChords.add(secondChord)
    }
    if(thirdChord != null){
        usedChords.add(thirdChord)
    }
    if(fourthChord != null){
        usedChords.add(fourthChord)
    }
    else{
        for (i in 1..nbChordsToGenerate) {
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
    }

// create components : 2 row of 2 chords with their tabs
    LazyHorizontalGrid(rows = GridCells.Fixed(2), modifier = Modifier.height(400.dp)) {

        items(usedChords) {
            Column {
                Image(
                    painterResource(id = it.getTab()),
                    it.getNom(),
                    modifier = Modifier.size(150.dp)
                )
                Text(
                    it.getNom(),
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


@Composable
fun HomePage(liste: ArrayList<Accord>) {
    val etat = remember { mutableIntStateOf(0) }
    val firstChord = remember { mutableStateOf<Accord?>(null) }
    val secondChord = remember { mutableStateOf<Accord?>(null) }
    val thirdChord = remember { mutableStateOf<Accord?>(null) }
    val fourthChord = remember { mutableStateOf<Accord?>(null) }
    val showDialog = remember { mutableStateOf(false) }
    var nbChordsToGenerate = remember { mutableIntStateOf(4) }
    val closeDialog:() -> Unit = { showDialog.value = false}
    val selectChord:(Accord)-> Unit = { accord ->
        firstChord.value = accord
        showDialog.value = false
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = { showDialog.value = true },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Choisir le premier accord")
            if(firstChord.value != null){
                Text(text = "(Actuel : ${firstChord.value!!.getNom()} )")
            }
        }

        //show chords list and allow the user to choose the first chord of the progression
        if (showDialog.value) {
            ChoseOneChord(liste,closeDialog,selectChord)
            if(nbChordsToGenerate.intValue == 4 )
            nbChordsToGenerate.intValue--;
        }

        //if the button has already been pressed, replace the previous chords with new ones
        if (etat.intValue > 0 && !showDialog.value) {
            Spacer(modifier = Modifier.weight(1f))
            Row {
                Spacer(modifier = Modifier.weight(1f))
                GenerateChords(liste, nbChordsToGenerate.intValue,firstChord.value,secondChord.value,thirdChord.value,fourthChord.value)
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Row {

            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = {
                if (etat.intValue == 0) {
                    etat.intValue = 1
                }
                else{
                    etat.intValue++;
                }
            }, shape = CutCornerShape(10), modifier = Modifier.width(300.dp)) {
                Text("Générer")
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

