package practice.util;

import java.lang.reflect.Array;

public class CustomLog {
    static final private Log log = new Log();

    public static void info(Object str){
        log.info(str.toString());
    }

    public static void s(Object str){
        log.s(str.toString());
    }

    public static void e(Object str){
        log.e(str.toString());
    }

    public static <T> void arr(T array){
        int length = Array.getLength(array);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(Array.get(array, i) + ",");
        }
        info(builder.toString());
    }
}

class Log {
    final String prefix = "[kbug] >> ";
    public void info(String str){
        System.out.println(prefix + str);
    }

    public void s(String str){
        System.out.println("###################### START ######################");
        System.out.println(prefix + str);
    }

    public void e(String str){
        System.out.println(prefix + str);
        System.out.println("###################### END ######################");
    }
}
