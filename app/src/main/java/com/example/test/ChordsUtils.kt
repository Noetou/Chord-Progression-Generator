package com.example.test

class ChordsUtils {


    companion object {
        /*
         Create a list with all the chords and returns it
         The "scale" stores related chords
         */
        fun generateChordList(): ArrayList<Chord> {

            val chordList = ArrayList<Chord>()

            chordList.add(Chord("A", R.drawable.a, arrayListOf("Bm","D","E","C#m","F#m","G#")))
            chordList.add(Chord("Am", R.drawable.a_minor, arrayListOf("C","Dm","Em","F","G")))

            chordList.add(Chord("A#", R.drawable.b_flat, arrayListOf("Cm","Dm","D#","F","Gm")))
            chordList.add(Chord("A#m", R.drawable.b_flat_minor, arrayListOf("C#","D#m","Fm","F#","G#")))

            chordList.add(Chord("B", R.drawable.b, arrayListOf("E","C#m","D#m","F#","G#m","A#")))
            chordList.add(Chord("Bm", R.drawable.b_minor, arrayListOf("D","Em","G","A","C#","F#m")))

            chordList.add(Chord("C", R.drawable.c, arrayListOf("Dm","Em","F","G","Am")))
            chordList.add(Chord("Cm", R.drawable.c_minor, arrayListOf("Cm","Fm","Gm","G#","A#","D#")))

            chordList.add(Chord("C#", R.drawable.d_flat, arrayListOf("D#m","Fm","F#","G#","A#m")))
            chordList.add(Chord("C#m", R.drawable.d_flat_minor, arrayListOf("D#","E","F#m","G#m","A","B")))

            chordList.add(Chord("D", R.drawable.d, arrayListOf("Em","G","A","Bm","F#m","C#")))
            chordList.add(Chord("Dm", R.drawable.d_minor, arrayListOf("F","Gm","Am","C","A#")))

            chordList.add(Chord("D#", R.drawable.e_flat, arrayListOf("Fm","Gm","G#","A#","Cm")))
            chordList.add(Chord("D#m", R.drawable.e_flat_minor, arrayListOf("F#","G#m","A#m","B","C#")))

            chordList.add(Chord("E", R.drawable.e, arrayListOf("A","B","F#m","G#m","C#m","D#")))
            chordList.add(Chord("Em", R.drawable.e_minor, arrayListOf("G","Am","Bm","C","D","F#")))

            chordList.add(Chord("F", R.drawable.f, arrayListOf("Gm","Am","Cm","Dm","A#")))
            chordList.add(Chord("Fm", R.drawable.f_minor, arrayListOf("Cm","G#","A#m","C#","D#")))

            chordList.add(Chord("F#", R.drawable.g_flat, arrayListOf("G#m","A#m","B","C#","D#m")))
            chordList.add(Chord("F#m", R.drawable.g_flat_minor, arrayListOf("G#","A","Bm","C#m","D","E")))

            chordList.add(Chord("G", R.drawable.g, arrayListOf("Am","Bm","C","D","Em","F#")))
            chordList.add(Chord("Gm", R.drawable.g_minor, arrayListOf("Cm","Dm","F","A#","D#")))

            chordList.add(Chord("G#", R.drawable.a_flat, arrayListOf("A#m","Cm","C#","D#","Fm")))
            chordList.add(Chord("G#m", R.drawable.a_flat_minor, arrayListOf("A#","B","C#m","D#m","E","F#")))

            return chordList
        }
    }

}