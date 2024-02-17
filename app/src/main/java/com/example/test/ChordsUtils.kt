package com.example.test

import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

class ChordsUtils {


    companion object {
        /*
         Create a list with all the chords and returns it
         The "scale" stores related chords
         */
        @Composable
        fun generateChordList(): ArrayList<Chord> {

            val chordList = ArrayList<Chord>()

            chordList.add(Chord("A", R.drawable.a, arrayListOf("Bm","D","E","C#m","F#m","G#"),
                MediaPlayer.create(LocalContext.current,R.raw.a)))
            chordList.add(Chord("Am", R.drawable.a_minor, arrayListOf("C","Dm","Em","F","G"), MediaPlayer.create(LocalContext.current,R.raw.a_minor)))

            chordList.add(Chord("A#", R.drawable.b_flat, arrayListOf("Cm","Dm","D#","F","Gm"),MediaPlayer.create(LocalContext.current,R.raw.b_flat)))
            chordList.add(Chord("A#m", R.drawable.b_flat_minor, arrayListOf("C#","D#m","Fm","F#","G#"),null))

            chordList.add(Chord("B", R.drawable.b, arrayListOf("E","C#m","D#m","F#","G#m","A#"),null))
            chordList.add(Chord("Bm", R.drawable.b_minor, arrayListOf("D","Em","G","A","C#","F#m"),MediaPlayer.create(LocalContext.current,R.raw.b_minor)))

            chordList.add(Chord("C", R.drawable.c, arrayListOf("Dm","Em","F","G","Am"),MediaPlayer.create(LocalContext.current,R.raw.c)))
            chordList.add(Chord("Cm", R.drawable.c_minor, arrayListOf("Cm","Fm","Gm","G#","A#","D#"),null))

            chordList.add(Chord("C#", R.drawable.d_flat, arrayListOf("D#m","Fm","F#","G#","A#m"),null))
            chordList.add(Chord("C#m", R.drawable.d_flat_minor, arrayListOf("D#","E","F#m","G#m","A","B"),MediaPlayer.create(LocalContext.current,R.raw.d_flat_minor)))

            chordList.add(Chord("D", R.drawable.d, arrayListOf("Em","G","A","Bm","F#m","C#"),MediaPlayer.create(LocalContext.current,R.raw.d)))
            chordList.add(Chord("Dm", R.drawable.d_minor, arrayListOf("F","Gm","Am","C","A#"),MediaPlayer.create(LocalContext.current,R.raw.d_minor)))

            chordList.add(Chord("D#", R.drawable.e_flat, arrayListOf("Fm","Gm","G#","A#","Cm"),null))
            chordList.add(Chord("D#m", R.drawable.e_flat_minor, arrayListOf("F#","G#m","A#m","B","C#"),null))

            chordList.add(Chord("E", R.drawable.e, arrayListOf("A","B","F#m","G#m","C#m","D#"),MediaPlayer.create(LocalContext.current,R.raw.e)))
            chordList.add(Chord("Em", R.drawable.e_minor, arrayListOf("G","Am","Bm","C","D","F#"),MediaPlayer.create(LocalContext.current,R.raw.e_minor)))

            chordList.add(Chord("F", R.drawable.f, arrayListOf("Gm","Am","Cm","Dm","A#"),MediaPlayer.create(LocalContext.current,R.raw.f)))
            chordList.add(Chord("Fm", R.drawable.f_minor, arrayListOf("Cm","G#","A#m","C#","D#"),null))

            chordList.add(Chord("F#", R.drawable.g_flat, arrayListOf("G#m","A#m","B","C#","D#m"),null))
            chordList.add(Chord("F#m", R.drawable.g_flat_minor, arrayListOf("G#","A","Bm","C#m","D","E"),MediaPlayer.create(LocalContext.current,R.raw.g_flat_minor)))

            chordList.add(Chord("G", R.drawable.g, arrayListOf("Am","Bm","C","D","Em","F#"),MediaPlayer.create(LocalContext.current,R.raw.g)))
            chordList.add(Chord("Gm", R.drawable.g_minor, arrayListOf("Cm","Dm","F","A#","D#"),null))

            chordList.add(Chord("G#", R.drawable.a_flat, arrayListOf("A#m","Cm","C#","D#","Fm"),null))
            chordList.add(Chord("G#m", R.drawable.a_flat_minor, arrayListOf("A#","B","C#m","D#m","E","F#"),MediaPlayer.create(LocalContext.current,R.raw.a_flat_minor)))

            return chordList
        }
    }

}