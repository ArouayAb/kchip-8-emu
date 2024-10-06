package org.example.chip8Impl

import org.example.chip8.DelayTimer
import org.example.utils.extensions.period
import java.util.*
import kotlin.concurrent.schedule

open class DelayTimerImpl : DelayTimer {
    init {
        Timer().schedule(0L, DECREMENT_RATE.period().toLong()) {
            if (value > 0.toUByte()) {
                value--
            }
        }
    }

    companion object {
        private const val DECREMENT_RATE = 60
    }

    private var value: UByte = 0.toUByte()

    override fun setValue(value: UByte) {
        this.value = value
    }

    override fun isActive(): Boolean {
        return value > 0.toUByte()
    }
}