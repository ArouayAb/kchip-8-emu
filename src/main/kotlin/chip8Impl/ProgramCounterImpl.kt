package org.example.chip8Impl

import org.example.chip8.ProgramCounter

class ProgramCounterImpl : ProgramCounter {
    private var location: UShort = 512.toUShort()

    override fun set(location: UShort) {
        this.location = location
    }

    override fun incrementBy(increment: UShort) {
        if (location + increment > UShort.MAX_VALUE) {
            throw OutOfMemoryError()
        }

        location = (location + increment).toUShort()
    }

    override fun getCurrentLocation(): UShort {
        return location
    }
}