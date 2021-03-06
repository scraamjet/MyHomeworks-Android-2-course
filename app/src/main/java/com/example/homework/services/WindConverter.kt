package com.example.homework.services

class WindConverter {

        fun convert(wind: Double): String {
            when (wind) {
                in 337.5..22.5 -> {
                    return "north"
                }
                in 22.5..67.5 -> {
                    return "north east"
                }
                in 67.5..112.5 -> {
                    return "east"
                }
                in 112.5..157.5 -> {
                    return "south east"
                }
                in 157.5..202.5 -> {
                    return "south"
                }
                in 202.5..247.5 -> {
                    return "south west"
                }
                in 247.5..292.5 -> {
                    return "west"
                }
                in 292.5..337.5 -> {
                    return "north west"
                }
                else -> return ""
        }
    }

}