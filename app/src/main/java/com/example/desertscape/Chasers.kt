package com.example.desertscape

class Chasers(var slow_pace_min: Int, var slow_pace_max: Int, var fast_pace_min: Int, var fast_pace_max: Int, var chasers_total_distance: Int) {
    var textToShow = ""
    var chasersProgress = 0
    // this returns a boolean depending on the chasers vs fugitive total distance comparison
    fun fugitiveCaught(fugitive: Fugitive): Boolean{ return this.chasers_total_distance >= fugitive.total_distance }

    //this function calls the chasers movement
    fun chase(fugitive: Fugitive) {
        if(fugitive.total_distance - this.chasers_total_distance < TOOLONG) {
            val runPot = (this.slow_pace_min..this.slow_pace_max).toList()
            var chasersRunnedDistance =  runPot.random()
            this.chasers_total_distance = this.chasers_total_distance + chasersRunnedDistance
            if(!this.fugitiveCaught(fugitive)) {
                chasersProgress = this.chasers_total_distance
                textToShow = "Your chasers have traveled $chasersRunnedDistance , they are ${fugitive.total_distance - this.chasers_total_distance} km long from you"
            }
            else {
                chasersProgress = this.chasers_total_distance
                textToShow = "You have been caught prisoner, they catched you after ${this.chasers_total_distance} km chasing you."
            }
        }
        else{
            val runPot = (this.fast_pace_min..this.fast_pace_max).toList()
            var chasersRunnedDistance =  runPot.random()
            this.chasers_total_distance = this.chasers_total_distance + chasersRunnedDistance
            if(!this.fugitiveCaught(fugitive)) {
                chasersProgress = this.chasersProgress
                textToShow = "Your chasers have traveled $chasersRunnedDistance , they are ${fugitive.total_distance - this.chasers_total_distance} long from you"
            }
            else {
                chasersProgress = this.chasers_total_distance
                textToShow = "You have been caught prisoner, they catched you after ${this.chasers_total_distance} km chasing you."
            }
        }
    }
}
