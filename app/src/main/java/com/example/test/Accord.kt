package com.example.test

class Accord(
    private val nom: String,
    private val tab: Int,
    private var gammeMajeure: ArrayList<Accord>,
    private var gammeMineure: ArrayList<Accord>)
{


    fun getNom(): String {
        return nom
    }

    fun getTab(): Int {
        return tab
    }

    fun getGammeMajeure(): ArrayList<Accord> {
        return gammeMajeure
    }

    fun putGammeMajeure(accord: Accord) {
        gammeMajeure.add(accord)
    }

    fun getGammeMineure(): ArrayList<Accord> {
        return gammeMineure
    }

    fun putGammeMineure(accord: Accord) {
        gammeMineure.add(accord)
    }


}