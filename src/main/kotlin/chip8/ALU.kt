package org.example.chip8

interface ALU {
    fun set(source: UByte): UByte
    fun bitwiseOR(op1: UByte, op2: UByte): UByte
    fun bitwiseAND(op1: UByte, op2: UByte): UByte
    fun logicalXOR(op1: UByte, op2: UByte): UByte
    fun add(op1: UByte, op2: UByte): UByte
    fun subtract(op1: UByte, op2: UByte): UByte
    fun shift(value: UByte, direction: Boolean): UByte
}