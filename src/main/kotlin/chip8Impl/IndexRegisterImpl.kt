package org.example.chip8Impl

import org.example.chip8.IndexRegister

class IndexRegisterImpl : IndexRegister {
    private var location: UShort = 0.toUShort()
    override fun pointAt(location: UShort) {
        this.location = location
    }

    override fun getCurrentLocation(): UShort {
        return this.location
    }
}