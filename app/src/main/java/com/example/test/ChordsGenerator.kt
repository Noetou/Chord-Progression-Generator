package com.example.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import com.example.test.ui.theme.TestTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var chordList = generateChordList()
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
fun GenerateChords(liste: ArrayList<Accord>) {
    var usedChords = ArrayList<Accord>()

    for (i in 1..4) {

        var chord = liste[(0..liste.size - 1).random()]
        if (usedChords.size != 0) {
            while (usedChords.contains(chord) && !usedChords[0].getGamme()
                    .contains(chord.getNom())
            ) {
                chord = liste[(0..liste.size - 1).random()]
            }
        }

        usedChords.add(chord)

    }
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
    Column {

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
    var chordList = generateChordList()
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

fun generateChordList(): ArrayList<Accord> {

    val chordList = ArrayList<Accord>()

    chordList.add(Accord("A", R.drawable.a_major, arrayListOf<String>("Bm,D,E")))
    chordList.add(Accord("Am", R.drawable.a_minor, arrayListOf<String>("C,Dm,Em,F,G")))
    chordList.add(Accord("B", R.drawable.b_major, arrayListOf<String>("E")))
    chordList.add(Accord("Bm", R.drawable.b_minor, arrayListOf<String>("D,Em,G,A")))
    chordList.add(Accord("C", R.drawable.c_major, arrayListOf<String>("Dm,Em,F,G,Am")))
    chordList.add(Accord("Cm", R.drawable.c_minor, arrayListOf<String>("Cm,Fm,Gm")))
    chordList.add(Accord("D", R.drawable.d_major, arrayListOf<String>("Em,G,A,Bm")))
    chordList.add(Accord("Dm", R.drawable.d_minor, arrayListOf<String>("F,Gm,Am,C")))
    chordList.add(Accord("E", R.drawable.e_major, arrayListOf<String>("A,B")))
    chordList.add(Accord("Em", R.drawable.e_minor, arrayListOf<String>("G,Am,Bm,C,D")))
    chordList.add(Accord("F", R.drawable.f_major, arrayListOf<String>("Gm,Am,Cm,Dm")))
    chordList.add(Accord("Fm", R.drawable.f_minor, arrayListOf<String>("Cm")))
    chordList.add(Accord("G", R.drawable.g_major, arrayListOf<String>("Am,Bm,C,D,Em")))
    chordList.add(Accord("Gm", R.drawable.g_minor, arrayListOf<String>("Cm,Dm,F")))
    for (chord in chordList) {
        for (i in 0..chordList.size - 1) {
            if (chordList[i].getGamme().contains(chord.getNom()) && !chord.getGamme()
                    .contains(chordList[i].getNom())
            ) {
                chord.addGamme(chordList[i].getNom())
            }
        }
    }

    return chordList
}
