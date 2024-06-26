package com.lizhongbin.ch_final.response;

public class Respon {
    private Boolean success;
    private String message;
    private String token;
    private long userId;
    private String type;

    // 构造方法、getters和setters省略...

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Respon(Boolean success, String message, String token, long userId, String type) {
        this.success = success;
        this.message = message;
        this.token = token;
        this.userId = userId;
        this.type = type;
    }

    public Respon(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
