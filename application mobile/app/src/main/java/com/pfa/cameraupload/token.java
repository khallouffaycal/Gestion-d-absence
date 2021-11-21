package com.pfa.cameraupload;

public class token {
    String token;
    String type;

    public token(String token, String type) {
        this.token = token;
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "token{" +
                "token='" + token + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
