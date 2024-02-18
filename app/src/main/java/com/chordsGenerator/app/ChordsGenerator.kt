package com.chordsGenerator.app


import androidx.compose.runtime.MutableState


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



