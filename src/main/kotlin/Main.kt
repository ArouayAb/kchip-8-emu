package org.example

import org.example.chip8.Chip8
import org.example.chip8Impl.Chip8Impl
import java.io.FileNotFoundException
import kotlin.experimental.and

@OptIn(ExperimentalUnsignedTypes::class)
fun main() {
    val chip8: Chip8 = Chip8Impl()
    val ibmLogoRomPath = "/roms/IBM Logo.ch8"
    val romData = ({}::class.java.getResource(ibmLogoRomPath) ?: throw FileNotFoundException())
        .readBytes()
        .toUByteArray()

    chip8.loadROM(romData)
    chip8.start()
}