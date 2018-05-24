package com.jnyilin.basiclib.utils.retrofitutils.entity;

/**
 * @author HRR
 * @datetime 2018/1/31
 * @describe
 * @modifyRecord
 */

public class CallEntity {

    /**
     * data :
     * msg : 密码不正确
     * code : 1
     * debugMsg :
     */

    private String data;
    private String msg;
    private int code;
    private String debugMsg;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDebugMsg() {
        return debugMsg;
    }

    public void setDebugMsg(String debugMsg) {
        this.debugMsg = debugMsg;
    }

    @Override
    public String toString() {
        return "CallEntity{" +
                "data='" + data + '\'' +
                ", msg='" + msg + '\'' +
                ", code=" + code +
                ", debugMsg='" + debugMsg + '\'' +
                '}';
    }
}
