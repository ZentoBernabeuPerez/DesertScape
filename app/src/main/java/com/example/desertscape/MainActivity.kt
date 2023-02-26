package com.example.desertscape

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.*
import androidx.annotation.StringRes

const val LASTCITY = 1_000
const val WATERHUMANFACTOR = 4
const val WATERHORSEFACTOR = 2.5
const val HIDEPLUSDISTANCE = 100
const val RIGHTFACTOR = 1
const val  TOOLONG = 150
var totalText = ""
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var horseObject = Horse(20, 15, 20, true)
        var fugitiveObject = Fugitive(16, 20, horseObject, 100)
        var chasersObject = Chasers(25, 70, 70, 140, 0)


        val levels = resources.getStringArray(R.array.levels_to_choose)
        val arrayAdapterLevels = ArrayAdapter(this, R.layout.dropdown_menu, levels)
        val levelsAutoCompleteTV = findViewById<AutoCompleteTextView>(R.id.levelAutocompleteTV)
        levelsAutoCompleteTV.setAdapter(arrayAdapterLevels)

        val themes = resources.getStringArray(R.array.themes_to_choose)
        val arrayAdapterThemes = ArrayAdapter(this, R.layout.dropdown_menu, themes)
        val themesAutoCompleteTV = findViewById<AutoCompleteTextView>(R.id.skinThemeAutocompleteTV)
        themesAutoCompleteTV.setAdapter(arrayAdapterThemes)

        ///These are the main playing buttons plus closing log+ button
        val closeLogPlus = findViewById<Button>(R.id.closeLogPlusButton)

        //These will be buttons from how to play
        val playButtonOnHowTo = findViewById<Button>(R.id.thirdHowToOptionButton)
        val closeHowtoButton = findViewById<Button>(R.id.firstHowToOptionButton)

        //These are the options buttons on playing screen
        val howToPlayButton = findViewById<Button>(R.id.thirdPlayOptionButton)
        val optionsButtonInPlayScreen = findViewById<Button>(R.id.secondPlayOptionButton)
        val openLogPlus = findViewById<Button>(R.id.fourthPlayOptionButton)

        //These are the options buttons
        val closeOptions = findViewById<Button>(R.id.optionsCloseButton)

        //These are the gameover screen buttons
        var closeGameOverButton = findViewById<Button>(R.id.endGameCloseButton)
        var restartGameButton = findViewById<Button>(R.id.restartLevelButton)



        //Here goes all the onclicklisteners options from main playing
        openLogPlus.setOnClickListener {
            makeLogPlusVisible()
        }
        closeLogPlus.setOnClickListener {
            makeLogPlusInvisible()
        }
        howToPlayButton.setOnClickListener {
            makeHowToLLVisible()
        }
        optionsButtonInPlayScreen.setOnClickListener {
            makeOptionsVisible()
        }
        //Here goes the setonclicks from how to
        closeHowtoButton.setOnClickListener {
            makeHowToLLInvisible()
        }
        playButtonOnHowTo.setOnClickListener {
            gameInit(fugitiveObject, chasersObject)
        }
        //Here goes the Options set on clicklisteners
        closeOptions.setOnClickListener {
            makeOptionsInvisible()
        }
        //buttons onclicklistener from gameover screen
        closeGameOverButton.setOnClickListener {
            var gameOverScreen = findViewById<LinearLayout>(R.id.endGameLL)
            gameOverScreen.visibility = INVISIBLE
        }
        restartGameButton.setOnClickListener {
            playAgain(fugitiveObject, chasersObject)
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
    private fun gameInit(fugitive: Fugitive, chasers: Chasers) {

        makeHowToLLInvisible()
        checkIfHorseIsAlive(fugitive, chasers)
    }

    private fun playAgain(fugitive: Fugitive, chasers: Chasers) {
        fugitive.newGameRestaurator()
        chasers.chasers_total_distance = 0
        var fugitiveProgressBar = findViewById<ProgressBar>(R.id.playerProgressSB)
        var chasersProgressBar = findViewById<ProgressBar>(R.id.huntersProgressSB)
        fugitiveProgressBar.isEnabled = false
        chasersProgressBar.isEnabled = false
        fugitiveProgressBar.progress = fugitive.total_distance
        chasersProgressBar.progress = chasers.chasers_total_distance
        var trotButton = findViewById<Button>(R.id.firstActionButton)
        var gallopadeButton = findViewById<Button>(R.id.secondActionButton)
        var hideButton = findViewById<Button>(R.id.fourthActionButton)
        trotButton.text = "Trot"
        gallopadeButton.text = "Gallope"
        hideButton.setBackgroundResource(R.drawable.desert_button_inactive)
        hideButton.text = ""
        var logTV = findViewById<TextView>(R.id.storyTellingTV)
        logTV.text = "Chose your first action by clicking above buttons"
        totalText = ""
        val gameOverScreen = findViewById<LinearLayout>(R.id.endGameLL)
        gameOverScreen.visibility = INVISIBLE
        gameInit(fugitive, chasers)

    }
    private fun checkIfHorseIsAlive(fugitive: Fugitive, chasers: Chasers) {
        if (fugitive.horseIsAlive()) {
            gameWithHorse(fugitive, chasers)
        }
        else {
            gameWithoutHorse(fugitive, chasers)
        }

    }
    private fun gameWithHorse(fugitive: Fugitive, chasers: Chasers) {
        var fugitiveProgressBar = findViewById<ProgressBar>(R.id.playerProgressSB)
        var chasersProgressBar = findViewById<ProgressBar>(R.id.huntersProgressSB)
        fugitiveProgressBar.isEnabled = false
        chasersProgressBar.isEnabled = false
        var logPlusTextTV = findViewById<TextView>(R.id.logPlusTV)
        var logTextTV = findViewById<TextView>(R.id.storyTellingTV)
        val trotButton = findViewById<Button>(R.id.firstActionButton)
        val gallopadeButton = findViewById<Button>(R.id.secondActionButton)
        val horseRestButton = findViewById<Button>(R.id.thirdActionButton)
        val hideButton = findViewById<Button>(R.id.fourthActionButton)
        trotButton.setOnClickListener {
            fugitive.horseTrot()
            if(fugitive.saved()) { gameOver(fugitive, chasers) }
            chasers.chase(fugitive)
            if(chasers.fugitiveCaught(fugitive)) { gameOver(fugitive, chasers) }
            logTextTV.text = "${fugitive.textToShow} \n ${chasers.textToShow}"
            totalText = "\n ${fugitive.textToShow} \n ${chasers.textToShow}" + totalText
            logPlusTextTV.text = totalText
            fugitiveProgressBar.progress = fugitive.total_distance
            chasersProgressBar.progress = chasers.chasers_total_distance
            checkIfHorseIsAlive(fugitive, chasers)
        }
        gallopadeButton.setOnClickListener {
            fugitive.horseGallopade()
            if(fugitive.saved()) { gameOver(fugitive, chasers) }
            chasers.chase(fugitive)
            if(chasers.fugitiveCaught(fugitive)) { gameOver(fugitive, chasers) }
            logTextTV.text = "${fugitive.textToShow} \n ${chasers.textToShow}"
            totalText = "\n ${fugitive.textToShow} \n ${chasers.textToShow}" + totalText
            logPlusTextTV.text = totalText
            fugitiveProgressBar.progress = fugitive.total_distance
            chasersProgressBar.progress = chasers.chasers_total_distance
            checkIfHorseIsAlive(fugitive,chasers)
        }
        horseRestButton.setOnClickListener {
            fugitive.horseRest()
            chasers.chase(fugitive)
            if(chasers.fugitiveCaught(fugitive)) { gameOver(fugitive, chasers) }
            logTextTV.text = "${fugitive.textToShow} \n ${chasers.textToShow}"
            totalText = "\n ${fugitive.textToShow} \n ${chasers.textToShow}" + totalText
            logPlusTextTV.text = totalText
            chasersProgressBar.progress = chasers.chasers_total_distance
        }
        hideButton.setOnClickListener {
            val toast = Toast.makeText(this, "This option is not possible with horse", Toast.LENGTH_LONG)
            toast.show()
        }
    }
    private fun gameWithoutHorse(fugitive: Fugitive, chasers: Chasers) {
        var fugitiveProgressBar = findViewById<ProgressBar>(R.id.playerProgressSB)
        var chasersProgressBar = findViewById<ProgressBar>(R.id.huntersProgressSB)
        fugitiveProgressBar.isEnabled = false
        chasersProgressBar.isEnabled = false
        var logPlusTextTV = findViewById<TextView>(R.id.logPlusTV)
        var logTextTV = findViewById<TextView>(R.id.storyTellingTV)
        val walkButton = findViewById<Button>(R.id.firstActionButton)
        val runButton = findViewById<Button>(R.id.secondActionButton)
        val restButton = findViewById<Button>(R.id.thirdActionButton)
        val hideButton = findViewById<Button>(R.id.fourthActionButton)
        walkButton.text = "walk"
        runButton.text = "run"
        hideButton.setBackgroundResource(R.drawable.desert_button)
        hideButton.text =  "hide"
        walkButton.setOnClickListener {
            fugitive.walk()
            if(!fugitive.isAlive() || fugitive.saved()) { gameOver(fugitive, chasers) }
            chasers.chase(fugitive)
            if(chasers.fugitiveCaught(fugitive)) { gameOver(fugitive, chasers) }
            chasers.chase(fugitive)
            logTextTV.text = "${fugitive.textToShow} \n ${chasers.textToShow}"
            totalText = "\n ${fugitive.textToShow} \n ${chasers.textToShow}" + totalText
            logPlusTextTV.text = totalText
            fugitiveProgressBar.progress = fugitive.total_distance
            chasersProgressBar.progress = chasers.chasers_total_distance
        }
        runButton.setOnClickListener {
            fugitive.run()
            if(!fugitive.isAlive() || fugitive.saved()) { gameOver(fugitive, chasers) }
            chasers.chase(fugitive)
            if(chasers.fugitiveCaught(fugitive)) { gameOver(fugitive, chasers) }
            logTextTV.text = "${fugitive.textToShow} \n ${chasers.textToShow}"
            totalText = "\n ${fugitive.textToShow} \n ${chasers.textToShow}" + totalText
            logPlusTextTV.text = totalText
            fugitiveProgressBar.progress = fugitive.total_distance
            chasersProgressBar.progress = chasers.chasers_total_distance
        }
        restButton.setOnClickListener {
            fugitive.rest()
            chasers.chase(fugitive)
            if(chasers.fugitiveCaught(fugitive)) { gameOver(fugitive, chasers) }
            logTextTV.text = "${fugitive.textToShow} \n ${chasers.textToShow}"
            totalText = "\n ${fugitive.textToShow} \n ${chasers.textToShow}" + totalText
            logPlusTextTV.text = totalText
            chasersProgressBar.progress = chasers.chasers_total_distance
        }
        hideButton.setOnClickListener {
            fugitive.hide(chasers)
            if(!fugitive.isAlive() || fugitive.saved()) { gameOver(fugitive, chasers) }
            chasers.chase(fugitive)
            if(chasers.fugitiveCaught(fugitive)) { gameOver(fugitive, chasers) }
            logTextTV.text = "${fugitive.textToShow} \n ${chasers.textToShow}"
            totalText = "\n ${fugitive.textToShow} \n ${chasers.textToShow}" + totalText
            logPlusTextTV.text = totalText
            fugitiveProgressBar.progress = fugitive.total_distance
            chasersProgressBar.progress = chasers.chasers_total_distance
        }
    }
    private fun gameOver(fugitive: Fugitive, chasers: Chasers) {
        var winLoseTextTV = findViewById<TextView>(R.id.winLoseTV)
        val gameOverScreen = findViewById<LinearLayout>(R.id.endGameLL)
        gameOverScreen.visibility = VISIBLE
        if (!fugitive.isAlive()) {
            winLoseTextTV.text = "You are dead from fatigue!"
        } else if(fugitive.saved() && fugitive.horseIsAlive()) {
            winLoseTextTV.text = "You and your horse are safe now!"
        } else if(fugitive.saved()) {
            winLoseTextTV.text = "You won, congrats!"
        } else {
            winLoseTextTV.text = "You have been caught, and deviscerated"
        }
    }

}