package com.example.songwei.androidpractice.MyOkHttpTest;

/**
 * Author: SongWei
 * Date:   2019/10/20
 * Usage:
 */
public class NeBean {

    private int resultcode;
    private  String reason;

    public int getResultcode(){
        return resultcode;
    }

    public void setResultcode(int resultcode){
        this.resultcode = resultcode;
    }

    public String getReason(){
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "NeBean{" +
                "resultcode=" + resultcode +
                ", reason='" + reason + "'" +
                "}";
    }
}
