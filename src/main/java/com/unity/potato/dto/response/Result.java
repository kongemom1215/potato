package com.unity.potato.dto.response;

public class Result {

    private String resultCode;
    private String resultMsg;

    public Result(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Result(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public String getCode() {
        return resultCode;
    }

    public void setCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
