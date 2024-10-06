package org.example.chip8Impl

import org.example.chip8.Stack

class StackImpl : Stack {
    private val stack = ArrayDeque<UShort>()
    override fun push(data: UShort) {
        stack.addLast(data)
    }

    override fun pop() {
        stack.removeLast()
    }

}