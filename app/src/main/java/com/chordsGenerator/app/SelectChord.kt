package com.chordsGenerator.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



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
                        columns = GridCells.Adaptive(100.dp),
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
