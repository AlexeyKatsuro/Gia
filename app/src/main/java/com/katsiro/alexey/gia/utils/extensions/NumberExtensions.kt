package com.katsiro.alexey.gia.utils.extensions


fun Int.applyDispersion(persent: Double): Int {
    return IntRange(this - (this * persent).toInt(), this + (this * persent).toInt()).random()
}


