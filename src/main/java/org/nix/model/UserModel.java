package org.nix.model;

import com.alibaba.fastjson.annotation.JSONField;
import org.nix.common.enums.RoleEnum;
import org.nix.model.base.BaseModel;
import org.nix.model.city.City;

/**
 * @author Kiss
 * @date 2018/04/22 14:58
 */
public class UserModel extends BaseModel<UserModel> {
    private String nickName;
    private String passWord;
    private String userName;
    private String phone;
    private RoleModel roleModel;
    private City address;

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

    @JSONField(serialize = false)
    public RoleModel getRoleModel() {
        return roleModel;
    }

    public void setRoleModel(RoleModel roleModel) {
        this.roleModel = roleModel;
    }

    @JSONField(serialize = false)
    public City getAddress() {
        return address;
    }

    public void setAddress(City address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "nickName='" + nickName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", roleModel=" + roleModel +
                ", address=" + address +
                ", id=" + id +
                ", createTime=" + createTime +
                '}';
    }
}
