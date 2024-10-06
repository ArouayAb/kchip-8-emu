package org.example.chip8

interface IndexRegister {
    fun pointAt(location: UShort)
    fun getCurrentLocation(): UShort
}