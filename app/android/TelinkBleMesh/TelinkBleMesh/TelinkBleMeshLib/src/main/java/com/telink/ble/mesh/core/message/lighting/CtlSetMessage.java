package com.telink.ble.mesh.core.message.lighting;

import com.telink.ble.mesh.core.message.Opcode;
import com.telink.ble.mesh.core.message.generic.GenericMessage;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * include CLT set and CTL set no ack
 * by {@link #ack}
 * Created by kee on 2019/8/14.
 */
public class CtlSetMessage extends GenericMessage {

    private int lightness;

    private int temperature;

    private int deltaUV;

    // transaction id
    private byte tid = 0;

    private byte transitionTime = 0;

    private byte delay = 0;

    private boolean ack = false;

    private boolean isComplete = false;

    public static CtlSetMessage getSimple(int address, int appKeyIndex, int lightness, int temperature, int deltaUV, boolean ack, int rspMax) {
        CtlSetMessage message = new CtlSetMessage(address, appKeyIndex);
        message.lightness = lightness;
        message.temperature = temperature;
        message.deltaUV = deltaUV;
        message.ack = ack;
        message.setResponseMax(rspMax);
        return message;
    }

    public CtlSetMessage(int destinationAddress, int appKeyIndex) {
        super(destinationAddress, appKeyIndex);
        setTidPosition(6);
    }

    @Override
    public int getResponseOpcode() {
        return ack ? Opcode.LIGHT_CTL_STATUS.value : super.getResponseOpcode();
    }

    @Override
    public int getOpcode() {
        return ack ? Opcode.LIGHT_CTL_SET.value : Opcode.LIGHT_CTL_SET_NOACK.value;
    }

    @Override
    public byte[] getParams() {
        return
                isComplete ?
                        ByteBuffer.allocate(9).order(ByteOrder.LITTLE_ENDIAN)
                                .putShort((short) lightness)
                                .putShort((short) temperature)
                                .putShort((short) deltaUV)
                                .put(tid)
                                .put(transitionTime)
                                .put(delay).array()
                        :
                        ByteBuffer.allocate(7).order(ByteOrder.LITTLE_ENDIAN)
                                .putShort((short) lightness)
                                .putShort((short) temperature)
                                .putShort((short) deltaUV)
                                .put(tid).array()
                ;
    }

    public void setLightness(int lightness) {
        this.lightness = lightness;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setDeltaUV(int deltaUV) {
        this.deltaUV = deltaUV;
    }

    public void setTid(byte tid) {
        this.tid = tid;
    }

    public void setTransitionTime(byte transitionTime) {
        this.transitionTime = transitionTime;
    }

    public void setDelay(byte delay) {
        this.delay = delay;
    }

    public void setAck(boolean ack) {
        this.ack = ack;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}