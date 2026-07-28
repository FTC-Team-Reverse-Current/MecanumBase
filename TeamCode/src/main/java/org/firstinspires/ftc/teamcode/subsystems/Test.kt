package org.firstinspires.ftc.teamcode.subsystems

import dev.nextftc.core.components.BindingsComponent
import dev.nextftc.core.components.SubsystemComponent
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import dev.nextftc.hardware.impl.MotorEx


class Test : NextFTCOpMode() {

    init {
        addComponents(
            SubsystemComponent(Intake),
            BulkReadComponent,
            BindingsComponent
        )
    }

    var motor = MotorEx("intake")

    public override fun onStartButtonPressed() {

    }
}