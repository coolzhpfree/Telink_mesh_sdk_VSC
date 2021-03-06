package com.telink.ble.mesh.entity;

/**
 * Created by kee on 2019/2/25.
 */

public class TransitionTime {

    // 6 bits
    byte number;

    /**
     * 2 bits
     * 0b00
     * The Default Transition Step Resolution is 100 milliseconds
     * 0b01
     * The Default Transition Step Resolution is 1 second
     * 0b10
     * The Default Transition Step Resolution is 10 seconds
     * 0b11
     * The Default Transition Step Resolution is 10 minutes
     */
    byte step;
    // 0b111111
    private static final int MAX_STEP_VALUE = 0x3F;

    private static final byte STEP_RESOLUTION_100_MILL = 0b00;
    private static final byte STEP_RESOLUTION_1_SECOND = 0b01;
    private static final byte STEP_RESOLUTION_10_SECOND = 0b10;
    private static final byte STEP_RESOLUTION_10_MINUTE = 0b11;

    private static final int PERIOD_STEP_100_MILL = 100;
    private static final int PERIOD_STEP_1_SECOND = 1000;
    private static final int PERIOD_STEP_10_SECOND = 10 * 1000;
    private static final int PERIOD_STEP_10_MINUTE = 10 * 60 * 1000;

    public TransitionTime(byte number, byte step) {
        this.number = number;
        this.step = step;
    }

    public static TransitionTime fromTime(long millisecond) {
        byte step = 0, number = 0;
        if (millisecond <= 0) {
            step = 0;
            number = 0;
        } else if (millisecond <= MAX_STEP_VALUE * PERIOD_STEP_100_MILL) {
            step = STEP_RESOLUTION_100_MILL;
            number = (byte) (millisecond / PERIOD_STEP_100_MILL);
        } else if (millisecond <= MAX_STEP_VALUE * PERIOD_STEP_1_SECOND) {
            step = STEP_RESOLUTION_1_SECOND;
            number = (byte) (millisecond / PERIOD_STEP_1_SECOND);
        } else if (millisecond <= MAX_STEP_VALUE * PERIOD_STEP_10_SECOND) {
            step = STEP_RESOLUTION_10_SECOND;
            number = (byte) (millisecond / PERIOD_STEP_10_SECOND);
        } else if (millisecond <= MAX_STEP_VALUE * PERIOD_STEP_10_MINUTE) {
            step = STEP_RESOLUTION_10_MINUTE;
            number = (byte) (millisecond / PERIOD_STEP_10_MINUTE);
        }
        return new TransitionTime(number, step);
    }

    public byte getValue() {
        return (byte) ((step << 6) | number);
    }
}
