package org.nix.model;

import org.nix.model.base.BaseModel;

/**
 * Create by zhangpe0312@qq.com on 2018/5/1.
 *
 * 角色
 */
public class RoleModel extends BaseModel<RoleModel> {

    private String roleName;
    private String msg;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "RoleModel{" +
                "roleName='" + roleName + '\'' +
                ", msg='" + msg + '\'' +
                ", id=" + id +
                ", createTime=" + createTime +
                '}';
    }
}
