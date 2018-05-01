package org.nix.annotation;

import org.nix.common.enums.RoleEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD}) //能够在方法和类、接口、枚举上使用
@Retention(RetentionPolicy.RUNTIME)// 运行时有效
public @interface Authority {
    /**
     * 一个类或者方法使用权限集合
     * 默认为游客权限
     */
    RoleEnum[] roles() default RoleEnum.ROLE_TOURISTS;

}
