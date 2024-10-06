package org.example.chip8

import org.example.chip8Impl.DisplayImpl

interface Display<T> {
    fun update(x: UByte, y: UByte, state: T): DisplayImpl.Companion.PixelState
    fun flip(x: UByte, y: UByte): UByte
    fun refresh()
    fun start()
    fun clear()
    fun getHorizontalResolution(): UByte
    fun getVerticalResolution(): UByte
}