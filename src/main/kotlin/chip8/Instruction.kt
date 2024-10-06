package org.example.chip8

interface Instruction {
    fun getByte1(): UByte
    fun getByte2(): UByte
    fun getNibble1(): UByte
    fun getNibble2(): UByte
    fun getNibble3(): UByte
    fun getNibble4(): UByte
    fun getNNN(): UShort
}