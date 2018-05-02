package org.nix.common.HandlerException;

import org.nix.common.exception.AuthorityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by zhangpe0312@qq.com on 2018/5/1.
 */
@ControllerAdvice
public class UserUseExceptionHandler {

    /**
     * 权限不足而产生的异常，状态码返回 403
     * @param e 异常对象
     * @return 需要通知前端的信息
     */
    @ExceptionHandler({AuthorityException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN) //返回状态码 403 权限不足
    public Map<String,Object> AuthorityException(AuthorityException e){
        e.printStackTrace();
        Map<String,Object> map = new HashMap<>();
        map.put("msg",e.getMessage());
        map.put("code","403");
        return null;
    }

    /**
     * 未知异常，状态码返回 500
     * @param e 异常对象
     * @return 需要通知前端的信息
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED) //返回状态码 500 服务器未知异常
    public Map<String,Object> Exception(Exception e){
        e.printStackTrace();
        Map<String,Object> map = new HashMap<>();
        map.put("msg","服务器异常");
        map.put("code","500");
        return null;
    }

}
