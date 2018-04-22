package org.nix.common.pojo.entity.base;

import java.util.Date;

/**
 * Create by zhangpe0312@qq.com on 2018/4/22.
 *
 * todo: 用于基础表父类数据项
 */
public class BaseEntity {

    /**
     * 数据id统一编号
     */
    private int id;
    /**
     * 数据创建时间
     */
    private Date createTime = new Date();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
