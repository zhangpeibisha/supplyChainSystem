package org.nix.model;

import org.nix.model.base.BaseModel;

/**
 * @author Kiss
 * @date 2018/04/22 14:58
 */
public class UserModel extends BaseModel<UserModel> {
    private String nickName;
    private String passWord;
    private String userName;
    private String phone;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "nickName='" + nickName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
