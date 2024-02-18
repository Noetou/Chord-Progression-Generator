package com.chordsGenerator.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
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
                                .padding(start = 30.dp, top = 30.dp, end = 30.dp)
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
                        .padding(start = 30.dp, top = 30.dp, end = 30.dp)
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
