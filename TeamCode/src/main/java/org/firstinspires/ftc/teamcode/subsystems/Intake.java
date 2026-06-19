package org.firstinspires.ftc.teamcode.subsystems;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.utility.LambdaCommand;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.powerable.SetPower;

public class Intake implements Subsystem {
    public static Intake INSTANCE = new Intake();
    private MotorEx intakeMotor = new MotorEx("intake").brakeMode();

    public Command run = new SetPower(intakeMotor, 1).requires(this);
    public Command stop = new SetPower(intakeMotor, 0).requires(this);
}
