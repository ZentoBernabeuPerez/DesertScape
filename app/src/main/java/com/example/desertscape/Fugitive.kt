package com.example.desertscape

class Fugitive (var water: Int, var energy: Int, var horse: Horse, var total_distance: Int) {
    //this are values that will be modified along the class
    val horse_gallopade_factor = horse.gallopade
    val horse_trot_factor = horse.trot
    var horse_energy = horse.energy
    var horse_alive = horse.alive
    val human_running_factor = 7
    val human_walking_factor = 10

    //this are values that will be returned after a gameplay to return the objects to its initial state
    val saved_water = this.water
    val saved_energy = this.energy
    val saved_horse_energy = this.horse_energy
    val saved_horse = this.horse_alive
    val saved_total_distance = this.total_distance
    var textToShow = ""
    var textToToast = ""

    //this function used the saved values to restore object to its initial state
    fun newGameRestaurator() {
        this.water = saved_water
        this.energy = saved_energy
        this.horse_energy = saved_horse_energy
        this.horse_alive = saved_horse
        this.total_distance = saved_total_distance
    }
   //this returns a boolean depending on if we have reach the end
   fun saved(): Boolean {return this.total_distance >= LASTCITY}

    //this returns a boolean depending on energy of the horse
    fun horseIsAlive():Boolean { return this.horse_energy > 0 }

    //this return a boolean depending on energy of human
    fun isAlive():Boolean { return this.energy >= 0 }

    //Used to make water to 0 when you have drinked more water than you have
    fun noMoreWater() { this.water = 0; textToToast = "No water left, try other thing" }

    //slow pace with horse function
    fun horseTrot(){
        val energyPot = arrayOf(1, 2, 3, 4)
        val wastedEnergy = energyPot.random()
        this.horse_energy = this.horse_energy - wastedEnergy
        val runnedDistance = wastedEnergy * this.horse_trot_factor
        this.total_distance =  this.total_distance + runnedDistance
        if(this.horse_energy <= 0) { this.horse_energy = 0 }
        if(this.total_distance > LASTCITY) { this.total_distance = LASTCITY }
        textToShow = "Your horse have run $runnedDistance km, your horse have $horse_energy of energy units left \nYou are ${LASTCITY - this.total_distance} km long from saving yourself"
    }

    //fast pace with horse option
    fun horseGallopade(){
        val energyPot = arrayOf(6, 7, 8, 9, 10)
        val wastedEnergy = energyPot.random()
        this.horse_energy = this.horse_energy - wastedEnergy
        val runnedDistance = wastedEnergy * this.horse_gallopade_factor
        this.total_distance =  this.total_distance + runnedDistance
        if(this.horse_energy <= 0) { this.horse_energy = 0 }
        if(this.total_distance > LASTCITY) { this.total_distance = LASTCITY }
        textToShow = "Your horse have run $runnedDistance km, your horse have ${this.horse_energy} of energy units left\nYou are ${LASTCITY - this.total_distance} km long from saving yourself"
    }

    //rest with horse
    fun horseRest() {
        if (this.water > 0) {
            val waterPot = arrayOf(1, 2, 3, 4)
            var waterDrinked = waterPot.random()
            if (this.water <= waterDrinked) {
                waterDrinked = this.water; this.noMoreWater()
            } else {
                this.water = this.water - waterDrinked
            }
            this.horse_energy = (this.horse_energy + waterDrinked * WATERHORSEFACTOR).toInt()
            textToShow = "Your horse have drinked $waterDrinked liters of water. \nYour horse have now ${this.horse_energy} of energy units.\nYou have ${this.water} liters of water left"
        } else {
            this.noMoreWater()
        }
    }

        //slow pace no horse
        fun walk() {
            val energyPot = arrayOf(1, 2, 3)
            val wastedEnergy = energyPot.random()
            this.energy = this.energy - wastedEnergy
            val runnedDistance = wastedEnergy * this.human_walking_factor
            this.total_distance = this.total_distance + runnedDistance
            if (this.energy <= 0) {
                this.energy = 0
            }
            if (this.total_distance > LASTCITY) {
                this.total_distance = LASTCITY
            }
            textToShow = "You have run $runnedDistance km, you have $energy of energy units left\nYou are ${LASTCITY - this.total_distance} km long from saving yourself"
        }

        //fast pace no horse
        fun run() {
            val energyPot = arrayOf(5, 6, 7, 8)
            val wastedEnergy = energyPot.random()
            this.energy = this.energy - wastedEnergy
            val runnedDistance = wastedEnergy * this.human_running_factor
            this.total_distance = this.total_distance + runnedDistance
            if (this.energy <= 0) {
                this.energy = 0
            }
            if (this.total_distance > LASTCITY) {
                this.total_distance = LASTCITY
            }
            textToShow = "You have run $runnedDistance km, you have $energy of energy units left\nYou are ${LASTCITY - this.total_distance} km long from saving yourself"
        }

        //rest without horse
        fun rest() {
            if (this.water > 0) {
                val waterPot = arrayOf(1, 2, 3, 4, 5)
                var waterDrinked = waterPot.random()
                if (this.water <= waterDrinked) {
                    waterDrinked = this.water; this.noMoreWater()
                } else {
                    this.water = this.water - waterDrinked
                }
                this.energy = this.energy + waterDrinked * WATERHUMANFACTOR
                textToShow = "You have drinked $waterDrinked liters of water. \nYou have now ${this.energy} of energy units.\nYou have ${this.water} liters of water left"
            } else {
                this.noMoreWater()
            }
        }

        //hide option
    fun hide(chasers: Chasers){
            val potluck = arrayOf(1, 2, 3, 4)
            val hidePotluck = potluck.random()
            if (hidePotluck == RIGHTFACTOR) {
                this.total_distance = this.total_distance + HIDEPLUSDISTANCE
                textToShow = "You have hidden succesfully, you are now: ${this.total_distance} "
            }
            else {
                chasers.chasers_total_distance = this.total_distance
                textToShow = "You were discovered, big F"
            }
    }
}