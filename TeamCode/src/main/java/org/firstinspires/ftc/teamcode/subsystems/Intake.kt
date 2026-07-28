package org.firstinspires.ftc.teamcode.subsystems

import dev.nextftc.core.commands.Command
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.MotorEx
import dev.nextftc.hardware.powerable.SetPower

object Intake : Subsystem {
    private val intakeMotor = MotorEx("intake").brakeMode()

    var run = SetPower(intakeMotor, 1.0).requires(this)
    var stop = SetPower(intakeMotor, 0.0).requires(this)

    var reverse = SetPower(intakeMotor, -1.0).requires((this))

}