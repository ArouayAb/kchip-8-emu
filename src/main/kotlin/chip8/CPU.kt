package org.example.chip8

import org.example.utils.extensions.period
import java.util.*
import kotlin.concurrent.schedule

interface CPU {
    fun getMemory(): Memory
    fun getStack(): Stack
    fun getExecutionRate(): Int
    fun fetch()
    fun decode()
    fun execute()
    fun processInstruction() {
        fetch()
        decode()
        execute()
    }
    fun start() {
        Timer().schedule(0L, getExecutionRate().period().toLong()) {
            processInstruction()
        }
    }
}