package org.nix.common.enums;

/**
 * Create by zhangpe0312@qq.com on 2018/5/1.
 *
 * 角色类目
 */
public enum  RoleEnum {

    ROLE_USER("普通用户","能够进行普通操作的用户"),
    ROLE_TOURISTS("游客","能够浏览系统基本信息，但是不能操作业务，必要时必须登陆才能访问指定信息"),
    ROLE_FACTORY("厂家","工厂方操作权限");

    private String roleName;
    private String des;

    RoleEnum(String roleName, String des) {
        this.roleName = roleName;
        this.des = des;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
