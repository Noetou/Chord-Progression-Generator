package com.example.test

class Accord(
    private val nom: String,
    private val tab: Int,
    private var gammeMajeure: ArrayList<String>,
    private var gammeMineure: ArrayList<String>)
{


    fun getNom(): String {
        return nom
    }

    fun getTab(): Int {
        return tab
    }

    fun getGammeMajeure(): ArrayList<String> {
        return gammeMajeure
    }

    fun getGammeMineure(): ArrayList<String> {
        return gammeMineure
    }

}