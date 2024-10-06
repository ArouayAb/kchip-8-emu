package org.example.chip8Impl

import org.example.chip8.Instruction
import org.example.utils.extensions.toHex

class InstructionImpl(private val byte1: UByte, private val byte2: UByte) : Instruction {
    override fun getByte1(): UByte {
        return byte1
    }

    override fun getByte2(): UByte {
        return byte2
    }

    override fun getNibble1(): UByte {
        return ((byte1.toInt() and 0xF0) shr 4).toUByte()
    }

    override fun getNibble2(): UByte {
        return ((byte1.toInt() and 0x0F)).toUByte()
    }

    override fun getNibble3(): UByte {
        return ((byte2.toInt() and 0xF0) shr 4).toUByte()
    }

    override fun getNibble4(): UByte {
        return ((byte2.toInt() and 0x0F)).toUByte()
    }

    override fun getNNN(): UShort {
        return ((this.getNibble2().toInt() shl 8) + (this.getNibble3().toInt() shl 4) + (this.getNibble4().toInt())).toUShort()
    }
}