package org.example.chip8

interface DelayTimer {
    fun setValue(value: UByte)
    fun isActive(): Boolean
}