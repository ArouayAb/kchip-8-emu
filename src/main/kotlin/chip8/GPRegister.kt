package org.example.chip8

interface GPRegister {
    fun setValue(value: UByte)
    fun getValue(): UByte
}