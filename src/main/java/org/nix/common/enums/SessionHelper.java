package org.nix.common.enums;

/**
 * Create by zhangpe0312@qq.com on 2018/5/1.
 */
public enum SessionHelper {

    SESSION_HELPER_USER("user","session存入用户信息的key");

    private String sessionKey;
    private String message;

    SessionHelper(String sessionKey, String message) {
        this.sessionKey = sessionKey;
        this.message = message;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
