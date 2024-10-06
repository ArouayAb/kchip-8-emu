package org.example.chip8

@OptIn(ExperimentalUnsignedTypes::class)
interface Chip8 {
    fun loadROM(data: UByteArray)
    fun start()
}