
package com.example.bservice.event;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * BService(background service) Eventクラス
 */
public class BEvent implements Parcelable {
    /** Event kind */
    private String mKind;
    /** Operation code */
    private String mCode;
    /** Data length */
    private int mDataLength;
    /** Data */
    private byte[] mData;

    /**
     * コンストラクタ
     *
     * @param kind Event kind
     * @param code Operation code
     * @param dataLength Data Length
     * @param data Data
     */
    public BEvent(String kind, String code, int dataLength, byte[] data) {
        mKind = kind;
        mCode = code;
        mDataLength = dataLength;
        mData = data;
    }

    public BEvent(Parcel in) {
        mKind = in.readString();
        mCode = in.readString();
        mDataLength = in.readInt();
        if (mDataLength > 0) {
            mData = new byte[mDataLength];
            in.readByteArray(mData);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mKind);
        dest.writeString(mCode);
        dest.writeInt(mDataLength);
        dest.writeByteArray(mData);
    }

    public static final Parcelable.Creator<BEvent> CREATOR = new Parcelable.Creator<BEvent>() {
        public BEvent createFromParcel(Parcel in) {
            return new BEvent(in);
        }

        public BEvent[] newArray(int size) {
            return new BEvent[size];
        }
    };

    public String getKind() {
        return mKind;
    }

    public void setKind(String kind) {
        mKind = kind;
    }

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public int getDataLength() {
        return mDataLength;
    }

    public void setDataLength(int dataLength) {
        mDataLength = dataLength;
    }

    public byte[] getData() {
        return mData;
    }

    public void setData(byte[] data) {
        mData = data;
    }
}
