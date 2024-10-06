package org.example.chip8

interface ProgramCounter {
    fun set(location: UShort)
    fun incrementBy(increment: UShort)
    fun getCurrentLocation(): UShort
    fun next(): UShort {
        val location = getCurrentLocation()
        incrementBy(1.toUShort())
        return location
    }
}