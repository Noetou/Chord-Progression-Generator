package com.example.test

class Accord(
    private val nom: String,
    private val tab: Int,
    private var gamme: ArrayList<String>,
) {


    fun getNom(): String {
        return nom
    }

    fun getTab(): Int {
        return tab
    }

    fun getGamme(): ArrayList<String> {
        return gamme
    }


}