package com.chordsGenerator.app

import AudioPlayer
import android.media.MediaPlayer

data class Chord(
    val name: String,
    val tab: Int,
    var scale: ArrayList<String>,
    val sound: Int,

) {


}