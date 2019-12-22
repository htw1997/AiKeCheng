package com.muker.result;

public enum ResultCode {
    OK(200),ERROR(400),NOTFOUNT(404),NOAUTH(205);
    private ResultCode(int v){
        val=v;
    }
    private int val;

    public int getVal() {
        return val;
    }
}