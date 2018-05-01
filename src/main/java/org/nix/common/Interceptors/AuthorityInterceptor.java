package org.nix.common.Interceptors;

import org.nix.annotation.Authority;
import org.nix.common.enums.RoleEnum;
import org.nix.common.enums.SessionHelper;
import org.nix.common.exception.AuthorityException;
import org.nix.dao.mapper.UserMapper;
import org.nix.model.RoleModel;
import org.nix.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Create by zhangpe0312@qq.com on 2018/5/1.
 * <p>
 * 权限拦截器
 */
public class AuthorityInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) o;
        Authority authority = handlerMethod.getMethodAnnotation(Authority.class);
        if (authority == null)
            return true;

        HttpSession session = httpServletRequest.getSession();
        UserModel user = (UserModel) session.getAttribute(SessionHelper.SESSION_HELPER_USER.getSessionKey());

        if (user == null ) {
            throw new AuthorityException();
        }

        user = userMapper.select(user.getId());
        if (!pass(authority.roles(), user.getRoleModel()))
            throw new AuthorityException();

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    /**
     * 进行权限校验
     *
     * @param roleEnums 注解上的值
     * @param roleModel 用户角色
     * @return 如果用户有这个权限返回true，否则返回false
     */
    private boolean pass(RoleEnum[] roleEnums, RoleModel roleModel) {
        for (RoleEnum roleEnum : roleEnums) {
            if (roleEnum.getRoleName().endsWith(roleModel.getRoleName()))
                return true;
        }
        return false;
    }
}
