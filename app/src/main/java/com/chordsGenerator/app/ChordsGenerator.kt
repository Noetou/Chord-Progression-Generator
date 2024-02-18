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


    val chordsList = list
    chordsList.removeAll(usedChords.toSet())

    // "if (generate > 0)" ensure the method doesn't display chords on the starting page
    if (generate > 0) {
        for (i in 0..3) {
            if (usedChords[i] == null) {
                var chord = chordsList.random()
                if (usedChords[0] != null) {

                    /* Ensure that the same chord can't be present twice in the same progression if not chosen by the user,
                    *  and that the chosen chord is in the scale of the firstChord */

                    while (!usedChords[0]?.scale?.contains(chord.name)!!) {
                        val chordName = usedChords[0]?.scale?.random()
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
                                chord = chordsList.random()

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



