package com.example.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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


    Row {
        for (i in 1..4) {
            var chord = liste[(0..7).random()]
            while (usedChords.contains(chord)) {
                chord = liste[(0..7).random()]
            }
            usedChords.add(chord)
            Column {
                Image(
                    painterResource(id = chord.getTab()),
                    chord.getNom(),
                    modifier = Modifier.size(150.dp)
                )

                Text(chord.getNom(), Modifier.padding(horizontal = 65.dp), style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center))
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
            }, shape = CutCornerShape(10)) {
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

    val compatibleMap = HashMap<Accord,ArrayList<String>>()
    compatibleMap.put(Accord("A", R.drawable.a_major), arrayListOf(""))
    compatibleMap.put(Accord("Am", R.drawable.a_minor), arrayListOf(""))
    compatibleMap.put(Accord("B", R.drawable.b_major), arrayListOf(""))
    compatibleMap.put(Accord("Bm", R.drawable.b_minor), arrayListOf(""))
    compatibleMap.put(Accord("C", R.drawable.c_major), arrayListOf(""))
    compatibleMap.put(Accord("Cm", R.drawable.c_minor), arrayListOf(""))
    compatibleMap.put(Accord("D", R.drawable.d_major), arrayListOf(""))
    compatibleMap.put(Accord("Dm", R.drawable.d_minor), arrayListOf(""))
    compatibleMap.put(Accord("E", R.drawable.e_major), arrayListOf(""))
    compatibleMap.put(Accord("Em", R.drawable.e_minor), arrayListOf(""))
    compatibleMap.put(Accord("F", R.drawable.f_major), arrayListOf(""))
    compatibleMap.put(Accord("Fm", R.drawable.f_minor), arrayListOf(""))
    compatibleMap.put(Accord("G", R.drawable.g_major), arrayListOf(""))
    compatibleMap.put(Accord("Gm", R.drawable.g_minor), arrayListOf(""))


    return chordList
}
