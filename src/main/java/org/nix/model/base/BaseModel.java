package org.nix.model.base;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by 11723 on 2017/5/4.
 */
public class BaseModel<M extends BaseModel<M>> {

    protected Integer id;

    //创建时间
    protected Date createTime = new Date();

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    private Object givePrivateValue(Object o,Field field) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = o.getClass();
        Method method = clazz.getMethod((field.getType().getSimpleName().matches(".*oolean.*")?"is":"get") + chanIndexZero(field.getName()));
        return method.invoke(o);
    }

    private Object settingPrivateValue(Object o,Field field,Object value) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = o.getClass();
        Method method = clazz.getMethod("set" + chanIndexZero(field.getName()),field.getType());
        return method.invoke(o,value);
    }
    private String chanIndexZero(String str) {
        char[] c = str.toCharArray();
        c[0] -= 32;
        return String.valueOf(c);
    }

    /**
     * 自动将model对象转为dto对象 <br />
     * 在懒加载中  如果返回model给前端懒加载会不起作用 只有将model转为dto才行
     * */
    public Object toDto(){
        Class clazz;
        Object dto;
        try {
            String clazzName = this.getClass().getSimpleName().replaceFirst("Model","Dto");
            clazz = Class.forName("com.nix.dto." + clazzName.substring(0,clazzName.indexOf("_")));
            dto = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field:fields){
            try {
                settingPrivateValue(dto,field,givePrivateValue(this,field));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dto;
    }
}
