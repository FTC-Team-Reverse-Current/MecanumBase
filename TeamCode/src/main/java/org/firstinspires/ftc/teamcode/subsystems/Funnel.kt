package org.firstinspires.ftc.teamcode.subsystems

import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx
import dev.nextftc.hardware.positionable.SetPosition

object Funnel : Subsystem {
    var leader = ServoEx("leader")
    var follower = ServoEx("follower")

    var open = SetPosition(leader, 1.0).then(SetPosition(follower, (160/180).toDouble()))
    var close = SetPosition(follower, 0.0).then(SetPosition(leader,0.0))
}
