package com.example.desertscape

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var horseObject = Horse(20, 15, 20, true)
        var fugitiveObject = Fugitive(16, 20, horseObject, 100)
        var chaserObject = Chasers(25, 70, 70, 140, 0)
        testingClasses(fugitiveObject, chaserObject, horseObject)
        val levels = resources.getStringArray(R.array.levels_to_choose)
        val arrayAdapterLevels = ArrayAdapter(this, R.layout.dropdown_menu, levels)
        val levelsAutoCompleteTV = findViewById<AutoCompleteTextView>(R.id.levelAutocompleteTV)
        levelsAutoCompleteTV.setAdapter(arrayAdapterLevels)

        val themes = resources.getStringArray(R.array.themes_to_choose)
        val arrayAdapterThemes = ArrayAdapter(this, R.layout.dropdown_menu, themes)
        val themesAutoCompleteTV = findViewById<AutoCompleteTextView>(R.id.skinThemeAutocompleteTV)
        themesAutoCompleteTV.setAdapter(arrayAdapterThemes)


        val howToPlayButton = findViewById<Button>(R.id.thirdPlayOptionButton)
        howToPlayButton.setOnClickListener {
            makeHowToLLVisible()
        }
        val closeHowtoButton = findViewById<Button>(R.id.firstHowToOptionButton)
        closeHowtoButton.setOnClickListener {
            makeHowToLLInvisible()
        }
        val optionsButtonInPlayScreen = findViewById<Button>(R.id.secondPlayOptionButton)
        optionsButtonInPlayScreen.setOnClickListener {
            makeOptionsVisible()
        }
        val closeOptions = findViewById<Button>(R.id.optionsCloseButton)
        closeOptions.setOnClickListener {
            makeOptionsInvisible()
        }
        val openLogPlus = findViewById<Button>(R.id.fourthPlayOptionButton)
        openLogPlus.setOnClickListener {
            makeLogPlusVisible()
        }
        val closeLogPlus = findViewById<Button>(R.id.closeLogPlusButton)
        closeLogPlus.setOnClickListener {
            makeLogPlusInvisible()
        }
    }
    private fun makeOptionsVisible() {
        val optionsMainLayout = findViewById<LinearLayout>(R.id.optionsAndButtonsMainLL)
        optionsMainLayout.visibility = View.VISIBLE
    }
    private fun makeOptionsInvisible() {
        val optionsMainLayout = findViewById<LinearLayout>(R.id.optionsAndButtonsMainLL)
        optionsMainLayout.visibility = View.INVISIBLE
    }
    private fun makeHowToLLVisible() {
        val howToPlayAndButtonsLayout = findViewById<LinearLayout>(R.id.howToPlayAndButtonsLL)
        howToPlayAndButtonsLayout.visibility = View.VISIBLE
    }
    private fun makeHowToLLInvisible() {
        val howToPlayAndButtonsLayout = findViewById<LinearLayout>(R.id.howToPlayAndButtonsLL)
        howToPlayAndButtonsLayout.visibility = View.INVISIBLE
    }
    private fun makeLogPlusVisible() {
        var logPlus = findViewById<LinearLayout>(R.id.logPlusLL)
        logPlus.visibility = View.VISIBLE
    }
    private fun makeLogPlusInvisible() {
        var logPlus = findViewById<LinearLayout>(R.id.logPlusLL)
        logPlus.visibility = View.INVISIBLE
    }

}