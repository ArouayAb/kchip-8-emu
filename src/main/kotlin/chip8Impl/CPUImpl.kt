package org.example.chip8Impl

import org.example.chip8.*
import org.example.utils.extensions.isBitSet

class CPUImpl(
    private val memory: Memory,
    private val stack: Stack,
    private val display: Display<DisplayImpl.Companion.PixelState>,
    private val frequency: Int = EXECUTION_RATE
) : CPU {
    companion object {
        private const val EXECUTION_RATE = 700
    }

    private val programCounter: ProgramCounter = ProgramCounterImpl()
    private var instruction: Instruction? = null
    private val indexRegister: IndexRegister = IndexRegisterImpl()
    private val gpRegisters: Map<UByte, GPRegister> = mapOf(
        0x0.toUByte() to GPRegisterImpl(),
        0x1.toUByte() to GPRegisterImpl(),
        0x2.toUByte() to GPRegisterImpl(),
        0x3.toUByte() to GPRegisterImpl(),
        0x4.toUByte() to GPRegisterImpl(),
        0x6.toUByte() to GPRegisterImpl(),
        0x7.toUByte() to GPRegisterImpl(),
        0x8.toUByte() to GPRegisterImpl(),
        0x9.toUByte() to GPRegisterImpl(),
        0xA.toUByte() to GPRegisterImpl(),
        0xB.toUByte() to GPRegisterImpl(),
        0xC.toUByte() to GPRegisterImpl(),
        0xD.toUByte() to GPRegisterImpl(),
        0xE.toUByte() to GPRegisterImpl(),
        0xF.toUByte() to GPRegisterImpl(),
    )
    private var action: () -> Unit = {}

    override fun getMemory(): Memory {
        return memory
    }

    override fun getStack(): Stack {
        return stack
    }

    override fun getExecutionRate(): Int {
        return EXECUTION_RATE
    }

    override fun fetch() {
        val byte1 = memory.getCell(programCounter.next())
        val byte2 = memory.getCell(programCounter.next())

        instruction = InstructionImpl(byte1, byte2)
    }

    override fun decode() {
        val currInstruction = (instruction ?: throw NoSuchElementException())
        action = when (currInstruction.getNibble1().toInt()) {
            0x0 -> {
                {
                    display.clear()
                }
            }
            0x1 -> {
                {
                    programCounter.set(currInstruction.getNNN())
                }
            }
            0x6 -> {
                {
                    val vRegister = (gpRegisters[currInstruction.getNibble2()] ?: throw NoSuchElementException())
                    vRegister.setValue(currInstruction.getByte2())
                }
            }
            0x7 -> {
                {
                    val vRegister = (gpRegisters[currInstruction.getNibble2()] ?: throw NoSuchElementException())
                    vRegister.setValue((vRegister.getValue() + currInstruction.getByte2()).toUByte())
                }
            }
            0xA -> {
                {
                    indexRegister.pointAt(currInstruction.getNNN())
                }
            }
            0xD -> {
                {
                    val height = currInstruction.getNibble4()

                    val vxRegister = (gpRegisters[currInstruction.getNibble2()] ?: throw NoSuchElementException())
                    val vyRegister = (gpRegisters[currInstruction.getNibble3()] ?: throw NoSuchElementException())
                    val vfRegister = (gpRegisters[0x0.toUByte()] ?: throw NoSuchElementException())

                    var xPos = (vxRegister.getValue() % display.getHorizontalResolution()).toUByte()
                    var yPos = (vyRegister.getValue() % display.getVerticalResolution()).toUByte()

                    for (i in 0.toUByte() ..< height) {
                        if (yPos >= display.getVerticalResolution()) {
                            break
                        }

                        val spriteByte = memory.getCell((indexRegister.getCurrentLocation() + i).toUShort())
                        for (bit in 7 downTo 0) {
                            if (xPos >= display.getHorizontalResolution()) {
                                break
                            }

                            if (spriteByte.isBitSet(bit)) {
                                val carryFlag = display.flip(xPos, yPos)
                                if (carryFlag.toInt() != 0) {
                                    vfRegister.setValue(carryFlag)
                                }
                            }

                            xPos = ((xPos + 1.toUByte())).toUByte()
                        }

                        xPos = (vxRegister.getValue() % display.getHorizontalResolution()).toUByte()
                        yPos = (yPos + 1.toUByte()).toUByte()
                    }
                }
            }
            else -> {
                throw NotImplementedError()
            }
        }
    }

    override fun execute() {
        action()
    }
}