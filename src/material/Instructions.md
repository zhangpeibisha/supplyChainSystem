###文件夹使用说明


####存放系统开发必要文件
### 用户部分api

<br/>
---
#####注册
/user/registered.do GET or POST<br>
参数：<br>
{<br>
"nickName":用户名,<br>
"passWord":密码（md5）,<br>
"userName":姓名,<br>
"phone":phone<br>
}<br>
返回参数:<br>
{<br>
"nickName":用户名,<br>
"userName":姓名,<br>
"phone":phone<br>
}

<br>
---
#####登录
/user/login.do GET or POST<br>
参数：<br>
{<br>
"nickName":用户名,<br>
"passWord":密码（md5）<br>
}<br>
返回参数:<br>
{<br>
"nickName":用户名,<br>
"userName":姓名,<br>
"phone":phone<br>
}

<br>
---
#####添加
/user/add.do POST<br>
参数：<br>
{<br>
"nickName":用户名,<br>
"passWord":密码（md5）,<br>
"userName":姓名,<br>
"phone":phone<br>
}<br>
返回参数:<br>
{<br>
"nickName":用户名,<br>
"userName":姓名,<br>
"phone":phone<br>
}

<br>
---
#####查看用户信息
/user/{id}.do GET<br>
参数：<br>
{<br>
无
}<br>
返回参数:<br>
{<br>
"nickName":用户名,<br>
"userName":姓名,<br>
"phone":phone<br>
}

<br>
---
#####用户信息修改
/user/add.do POST<br>
参数：<br>
{<br>
"id":用户id,<br>
"nickName":用户名,<br>
"userName":姓名,<br>
"phone":phone<br>
}<br>
返回参数:<br>
{<br>
"nickName":用户名,<br>
"userName":姓名,<br>
"phone":phone<br>
}


#####获取最优厂商
/api/order/best.do POST<br>
参数：<br>
{<br>
"id":订单id,<br>
}<br>
返回参数:<br>
{<br>
"status":1（成功）,<br>
"min":最短路径长度（可以用长度/10算出天数这些）,<br>
"best":厂商的全部信息<br>
}
--- 
#####使用规则
* 对放入的文件进行详细说明使用方法，和为什么使用，使用过程中有什么问题，应该如何解决


订单列表：orderList.do
方法：POST
参数：{
        "limit":一页显示数,
        "currentPage":当前页面
    }
返回：
    失败{"code",0}
    成功
    {
        code：1,
        "": {
             订单编号,
             用户编号,
             用户名,
             货物名,
             货物单价,       
             货物数量,
             合格率,
             到货天数,
             地址,
             原料合格率
        }
    }
------------------------------------------------
删除订单：orderDel.do
方法：POST
参数：{
        "orderid":订单号
    }
返回：
    失败{"code",0}
    成功
    {
        code：1
    }
------------------------------------------------
订单详情：orderDetail.do
方法：POST
参数：{
        "orderid":订单号
    }
返回：
    失败{"code",0}
    成功
       {
           code：1,
           "": {
                订单编号,
                用户编号,
                用户名,
                货物名,
                货物单价,       
                货物数量,
                合格率,
                到货天数,
                地址,
                原料合格率
           }
       }