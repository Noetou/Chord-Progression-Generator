package com.example.test

import android.media.MediaPlayer
import android.provider.MediaStore.Audio.Media

class Chord(
    private val name: String,
    private val tab: Int,
    private var scale: ArrayList<String>,
    private val sound: MediaPlayer?
) {

    /*
    * Returns the name of the chord
    * */
    fun getName(): String {
        return name
    }

    /*
   * Returns the tab of the chord
   * */

    fun getTab(): Int {
        return tab
    }

    /*
   * Returns the list of the related chords
   * */
    fun getScale(): ArrayList<String> {
        return scale
    }

    fun getMediaPlayer(): MediaPlayer? {
        return sound
    }

}