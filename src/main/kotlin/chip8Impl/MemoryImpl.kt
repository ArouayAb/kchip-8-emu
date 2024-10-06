package org.example.chip8Impl

import org.example.chip8.Memory

@OptIn(ExperimentalUnsignedTypes::class)
class MemoryImpl : Memory {
    companion object {
        private val START_INDEX: UShort = 512.toUShort()
        private val MAX_SIZE: UShort = 4096.toUShort()
    }

    private val cells: UByteArray = UByteArray(MAX_SIZE.toInt())

    override fun setAtLocation(location: UShort, data: UByteArray, insecure: Boolean) {
        if (!insecure && location < START_INDEX) {
            throw IllegalAccessException()
        }

        data.copyInto(cells, location.toInt())
    }

    override fun getCell(location: UShort): UByte {
        return cells[location.toInt()]
    }

    override fun getStartIndex(): UShort {
        return START_INDEX
    }
}