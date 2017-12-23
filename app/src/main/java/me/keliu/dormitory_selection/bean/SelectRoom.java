package me.keliu.dormitory_selection.bean;

/**
 * Created by 45023 on 2017/12/03.
 */

public class SelectRoom {
    private String errcode;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    @Override
    public String toString() {
        return "SelectRoom{" +
                "errcode='" + errcode + '\'' +
                '}';
    }
}
