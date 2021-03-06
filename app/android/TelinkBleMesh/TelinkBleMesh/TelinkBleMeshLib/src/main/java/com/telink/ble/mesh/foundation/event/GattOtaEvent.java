package com.telink.ble.mesh.foundation.event;


import android.os.Parcel;

import com.telink.ble.mesh.foundation.Event;

/**
 * Created by kee on 2017/8/30.
 */

public class GattOtaEvent extends Event<String> {

    public static final String EVENT_TYPE_OTA_SUCCESS = "com.telink.sig.mesh.OTA_SUCCESS";

    public static final String EVENT_TYPE_OTA_FAIL = "com.telink.sig.mesh.OTA_FAIL";

    public static final String EVENT_TYPE_OTA_PROGRESS = "com.telink.sig.mesh.OTA_PROGRESS";

    private int meshAddress;
    private int progress;
    private String desc;


    public GattOtaEvent(Object sender, String type, int meshAddress, int progress, String desc) {
        super(sender, type);
        this.meshAddress = meshAddress;
        this.progress = progress;
        this.desc = desc;
    }

    protected GattOtaEvent(Parcel in) {
        meshAddress = in.readInt();
        progress = in.readInt();
        desc = in.readString();
    }

    public static final Creator<GattOtaEvent> CREATOR = new Creator<GattOtaEvent>() {
        @Override
        public GattOtaEvent createFromParcel(Parcel in) {
            return new GattOtaEvent(in);
        }

        @Override
        public GattOtaEvent[] newArray(int size) {
            return new GattOtaEvent[size];
        }
    };

    public String getDesc() {
        return desc;
    }

    public int getMeshAddress() {
        return meshAddress;
    }

    public int getProgress() {
        return progress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(meshAddress);
        dest.writeInt(progress);
        dest.writeString(desc);
    }
}
