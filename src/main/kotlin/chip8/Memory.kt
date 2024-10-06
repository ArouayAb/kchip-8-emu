package org.example.chip8

interface Memory {
    fun setAtLocation(location: UShort = getStartIndex(), data: UByteArray, insecure: Boolean = false)
    fun getCell(location: UShort): UByte
    fun getStartIndex(): UShort
}