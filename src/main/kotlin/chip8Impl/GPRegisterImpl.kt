package org.example.chip8Impl

import org.example.chip8.GPRegister

class GPRegisterImpl : GPRegister {
    private var value: UByte = 0.toUByte()

    override fun setValue(value: UByte) {
        this.value = value
    }

    override fun getValue(): UByte {
        return this.value
    }
}