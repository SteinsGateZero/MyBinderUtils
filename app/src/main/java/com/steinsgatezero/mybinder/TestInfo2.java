package com.steinsgatezero.mybinder;

import java.io.Serializable;

public class TestInfo2 implements Serializable {
    private int id;
    private String msg;

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
    public String toString() {
        return "TestInfo2{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                '}';
    }
}
