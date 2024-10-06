package org.example.chip8Impl

import kotlinx.serialization.json.Json
import org.example.chip8.*
import org.example.utils.extensions.hexToByte
import java.io.FileNotFoundException

class Chip8Impl : Chip8 {
    private val memory: Memory = MemoryImpl()
    private val display: Display<DisplayImpl.Companion.PixelState> = DisplayImpl()
    private val stack: Stack = StackImpl()
    private val delayTimer: DelayTimer = DelayTimerImpl()
    private val soundTimer: SoundTimer = SoundTimerImpl()
    private val cpu: CPU = CPUImpl(memory, stack, display)

    @OptIn(ExperimentalUnsignedTypes::class)
    private fun init() {
        val fontJson = ({}::class.java.getResource("/fonts/default-font.json") ?: throw FileNotFoundException()).readText()
        val font = Json.decodeFromString<Map<String, List<String>>>(fontJson)

        val fontData = font.flatMap {
            entry -> entry.value
                .map(String::hexToByte)
        }.toUByteArray()

        memory.setAtLocation(80.toUShort(), fontData, true)
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun loadROM(data: UByteArray) {
        memory.setAtLocation(data = data)
    }

    override fun start() {
        init()
        display.start()
        cpu.start()
    }
}