package freemaker.util;

import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TypeUtil {
    private TypeUtil() {}

    private static Map< Class<?>,JdbcType> jdbcTypes = new HashMap<Class<?>,JdbcType>();
    static {
        jdbcTypes.put(String.class,JdbcType.VARCHAR);

        jdbcTypes.put(Boolean.class,JdbcType.BIT );
        jdbcTypes.put(boolean.class,JdbcType.BIT);

        jdbcTypes.put(Integer.class,JdbcType.INTEGER);
        jdbcTypes.put(int.class,JdbcType.INTEGER);

        jdbcTypes.put(Long.class,JdbcType.BIGINT);
        jdbcTypes.put(long.class,JdbcType.BIGINT);


        jdbcTypes.put(Float.class,JdbcType.REAL);
        jdbcTypes.put(float.class,JdbcType.REAL);

        jdbcTypes.put(Double.class,JdbcType.DOUBLE );
        jdbcTypes.put(double.class,JdbcType.DOUBLE );

        jdbcTypes.put(Date.class,JdbcType.TIMESTAMP);
    }
    public static JdbcType getJdbcType(Class<?> clazz){
        return jdbcTypes.get(clazz) == null ? null : jdbcTypes.get(clazz);
    }
}
