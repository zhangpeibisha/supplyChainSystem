package org.nix.common.exception;

/**
 * Create by zhangpe0312@qq.com on 2018/5/1.
 *
 * 权限不足
 */
public class AuthorityException extends RuntimeException{

    private String message;

    public AuthorityException() {
        message = "权限不足";
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
