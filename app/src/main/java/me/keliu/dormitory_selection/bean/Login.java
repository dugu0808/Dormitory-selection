package me.keliu.dormitory_selection.bean;

/**
 * Created by 45023 on 2017/12/03.
 */

public class Login {
    private String errCode;
    private String errMsg;

    public Login(String errCode, String errMsg){
        this.setErrCode(errCode);
        this.setErrMsg(errMsg);
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }


    @Override
    public String toString() {
        return "Login{" +
                "errCode='" + errCode + '\'' +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}
