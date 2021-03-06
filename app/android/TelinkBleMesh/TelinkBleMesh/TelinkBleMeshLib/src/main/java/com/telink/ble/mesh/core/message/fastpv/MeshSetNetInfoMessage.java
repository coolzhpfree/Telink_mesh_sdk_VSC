package com.telink.ble.mesh.core.message.fastpv;

import com.telink.ble.mesh.core.message.Opcode;
import com.telink.ble.mesh.core.message.firmwareupdate.UpdatingMessage;
import com.telink.ble.mesh.core.message.generic.GenericMessage;


public class MeshSetNetInfoMessage extends GenericMessage {

    private byte[] netInfoData;

    public static MeshSetNetInfoMessage getSimple(int destinationAddress, int appKeyIndex, byte[] netInfoData) {
        MeshSetNetInfoMessage message = new MeshSetNetInfoMessage(destinationAddress, appKeyIndex);
        message.netInfoData = netInfoData;
        return message;
    }

    public MeshSetNetInfoMessage(int destinationAddress, int appKeyIndex) {
        super(destinationAddress, appKeyIndex);
    }

    @Override
    public int getOpcode() {
        return Opcode.VD_MESH_PROV_DATA_SET.value;
    }

    @Override
    public int getResponseOpcode() {
        return OPCODE_INVALID;
    }

    @Override
    public byte[] getParams() {
        return netInfoData;
    }

    public void setNetInfoData(byte[] netInfoData) {
        this.netInfoData = netInfoData;
    }
}
