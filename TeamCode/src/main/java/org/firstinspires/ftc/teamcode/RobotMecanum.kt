/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import dev.nextftc.core.commands.Command
import dev.nextftc.core.components.BindingsComponent
import dev.nextftc.core.components.SubsystemComponent
import dev.nextftc.ftc.Gamepads
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import dev.nextftc.hardware.driving.MecanumDriverControlled
import dev.nextftc.hardware.impl.MotorEx
import org.firstinspires.ftc.teamcode.subsystems.Intake

@TeleOp(name = "Robot: Teleop Mecanum", group = "Robot")
class RobotMecanum : NextFTCOpMode() {
    init {
        addComponents(
            SubsystemComponent(Intake),
            BulkReadComponent,
            BindingsComponent
        )
    }

    private val frontLeftDrive = MotorEx("front_left").brakeMode().reversed()
    private val frontRightDrive = MotorEx("front_right").brakeMode().reversed()
    private val backLeftDrive = MotorEx("back_left").brakeMode()
    private val backRightDrive = MotorEx("back_right").brakeMode()

    override fun onStartButtonPressed() {
        val driveTrain = MecanumDriverControlled(
            frontLeftDrive,
            frontRightDrive,
            backLeftDrive,
            backRightDrive,
            Gamepads.gamepad1.leftStickY.negate(),
            Gamepads.gamepad1.leftStickX,
            Gamepads.gamepad1.rightStickX
        )
        driveTrain()

        (Gamepads.gamepad1.leftBumper) whenBecomesTrue Intake.run
        (Gamepads.gamepad1.leftBumper) whenBecomesFalse Intake.stop

        (Gamepads.gamepad1.rightBumper) whenBecomesTrue Intake.reverse
        (Gamepads.gamepad1.rightBumper) whenBecomesFalse Intake.stop

        // Send telemetry message to signify robot waiting;
        telemetry.addData(">", "Robot Ready. Press START.")
    }
}