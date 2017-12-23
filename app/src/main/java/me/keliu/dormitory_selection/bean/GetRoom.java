package me.keliu.dormitory_selection.bean;

/**
 * Created by 45023 on 2017/12/03.
 */

public class GetRoom {
    private String errCode;
    private String fifthNumber;
    private String thirteenthNumber;
    private String fourteenthNumber;
    private String eighthNumber;
    private String ninethNumber;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getFifthNumber() {
        return fifthNumber;
    }

    public void setFifthNumber(String fifthNumber) {
        this.fifthNumber = fifthNumber;
    }

    public String getThirteenthNumber() {
        return thirteenthNumber;
    }

    public void setThirteenthNumber(String thirteenthNumber) {
        this.thirteenthNumber = thirteenthNumber;
    }

    public String getFourteenthNumber() {
        return fourteenthNumber;
    }

    public void setFourteenthNumber(String fourteenthNumber) {
        this.fourteenthNumber = fourteenthNumber;
    }

    public String getEighthNumber() {
        return eighthNumber;
    }

    public void setEighthNumber(String eighthNumber) {
        this.eighthNumber = eighthNumber;
    }

    public String getNinethNumber() {
        return ninethNumber;
    }

    public void setNinethNumber(String ninethNumber) {
        this.ninethNumber = ninethNumber;
    }

    @Override
    public String toString() {
        return "GetRoom{" +
                "errCode='" + errCode + '\'' +
                ", fifthNumber='" + fifthNumber + '\'' +
                ", thirteenthNumber='" + thirteenthNumber + '\'' +
                ", fourteenthNumber='" + fourteenthNumber + '\'' +
                ", eighthNumber='" + eighthNumber + '\'' +
                ", ninethNumber='" + ninethNumber + '\'' +
                '}';
    }
}

