package org.nix.utils.log;

import org.apache.commons.logging.impl.Log4JLogger;
public final class LogKit {
    private static Log4JLogger log = new Log4JLogger("nix");
    private final static String getClassName(Class clazz){
        return clazz.getName() + " : ";
    }
    public final static void info(Class clazz,String msg){
        log.info(getClassName(clazz) + msg);
    }
    public final static void debug(Class clazz,String msg){
        log.debug(getClassName(clazz) + msg);
    }
    public final static void error(Class clazz,String msg){
        log.error(getClassName(clazz) + msg);
    }
}