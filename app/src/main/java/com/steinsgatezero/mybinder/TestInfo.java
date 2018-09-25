package com.steinsgatezero.mybinder;

import android.os.Parcel;
import android.os.Parcelable;

public class TestInfo implements Parcelable {
    private int id;
    private String msg;

    public TestInfo() {

    }

    private TestInfo(Parcel in) {
        id = in.readInt();
        msg = in.readString();
    }

    public static final Creator<TestInfo> CREATOR = new Creator<TestInfo>() {
        @Override
        public TestInfo createFromParcel(Parcel in) {
            return new TestInfo(in);
        }

        @Override
        public TestInfo[] newArray(int size) {
            return new TestInfo[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(msg);
    }

    @Override
    public String toString() {
        return "TestInfo{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                '}';
    }
}
