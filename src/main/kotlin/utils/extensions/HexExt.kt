package org.example.utils.extensions

fun UByte.toHex(): String {
    return "%02x".format(this)
}

fun String.hexToByte(): UByte {
    return this.toInt(16).toUByte()
}

inline fun <reified T> initMatrix(xSize: UByte, ySize: UByte, initValue: T): Array<Array<T>> {
    return Array(ySize.toInt()) { Array(xSize.toInt()) { _ -> initValue } }
}

fun Int.period(): Double {
    return ((1.toDouble() / this) * 1000)
}

fun Boolean.toUByte(): UByte {
    return if (this) 1.toUByte() else 0.toUByte()
}

fun UByte.isBitSet(bit: Int): Boolean {
    return ((this.toInt() shr bit) and 0x1) == 1
}