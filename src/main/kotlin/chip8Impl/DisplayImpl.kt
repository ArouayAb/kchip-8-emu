package org.example.chip8Impl

import org.example.chip8.Display
import org.example.utils.extensions.initMatrix
import org.example.utils.extensions.period
import org.example.utils.extensions.toUByte
import java.util.*
import kotlin.concurrent.schedule

class DisplayImpl : Display<DisplayImpl.Companion.PixelState> {
    companion object {
        enum class PixelState(val state: Boolean, val shape: String) {
            ON(true, "■■" ),
            OFF(false, "  ")
        }

        private val HORIZONTAL_SIZE: UByte = 64.toUByte()
        private val VERTICAL_SIZE: UByte = 32.toUByte()
        private const val REFRESH_RATE = 5
    }

    private val pixels = initMatrix<PixelState>(HORIZONTAL_SIZE, VERTICAL_SIZE, PixelState.OFF)

    private fun clearTerminal() {
        println(System.lineSeparator().repeat(2 * VERTICAL_SIZE.toInt()))
    }

    override fun update(x: UByte, y: UByte, state: PixelState): PixelState {
        if (x !in 0.toUByte() ..< HORIZONTAL_SIZE || (y !in 0.toUByte() ..< VERTICAL_SIZE)) {
            throw IllegalAccessException()
        }

        pixels[y.toInt()][x.toInt()] = state
        return state
    }

    override fun flip(x: UByte, y: UByte): UByte {
        if (x !in 0.toUByte() ..< HORIZONTAL_SIZE || (y !in 0.toUByte() ..< VERTICAL_SIZE)) {
            throw IllegalAccessException()
        }

        val newState = update(x, y, if (pixels[y.toInt()][x.toInt()] == PixelState.ON) PixelState.OFF else PixelState.ON)
        return (!newState.state).toUByte()
    }

    override fun refresh() {
        val screenBufferBuilder = StringBuilder()
        for (row in pixels) {
            screenBufferBuilder.append('\n')
            for (pixel in row) {
                screenBufferBuilder.append(if (pixel == PixelState.ON) PixelState.ON.shape else PixelState.OFF.shape)
            }
        }

        print(screenBufferBuilder.toString())
    }

    override fun start() {
        val refreshPeriod = REFRESH_RATE.period().toLong()

        Timer().schedule(0L, refreshPeriod) {
            clearTerminal()
            refresh()
        }
    }

    override fun clear() {
        for (indexRow in pixels.indices) {
            for (indexColumn in 0 ..< pixels[indexRow].size) {
                pixels[indexRow][indexColumn] = PixelState.OFF
            }
        }
    }

    override fun getHorizontalResolution(): UByte {
        return HORIZONTAL_SIZE
    }

    override fun getVerticalResolution(): UByte {
        return VERTICAL_SIZE
    }
}