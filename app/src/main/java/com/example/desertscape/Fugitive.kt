package com.example.desertscape

class Fugitive (var water: Int, var energy: Int, var horse: Horse, var total_distance: Int) {

        val horse_gallopade_factor = horse.gallopade
        val horse_trot_factor = horse.trot
        var horse_energy = horse.energy
        var horse_alive = horse.alive
        val human_running_factor = 7
        val human_walking_factor = 10

        val saved_water = this.water
        val saved_energy = this.energy
        val saved_horse_energy = this.horse_energy
        val saved_horse = this.horse_alive
        val saved_total_distance = this.total_distance
    fun status_printer():Any{
        return this.horse_energy
    }
}