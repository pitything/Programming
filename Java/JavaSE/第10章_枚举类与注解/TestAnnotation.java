import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.lang.annotation.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestAnnotation {
    @Check(min = 10)
    public int num;

    TestAnnotation(){}
    TestAnnotation(int num){
        this.num = num;
    }

    public static void main (String[] args){
        final Logger logger = LoggerFactory.getLogger(TestAnnotation.class);

        /** Check是否存在 */
        boolean annotationPresent = TestAnnotation.class.isAnnotationPresent(Check.class);
        /** 获取Check注解 */
        Check check = TestAnnotation.class.getAnnotation(Check.class);
        System.out.println(null != check ? check.min() : null);

        try {
            check(new TestAnnotation(-1));
        }catch (Exception exception){
            exception.printStackTrace();
            logger.error(exception.getMessage());
        }
    }

    static void check(TestAnnotation annotation) throws Exception {
        Constructor constructor = TestAnnotation.class.getConstructors()[0];
        Method method = TestAnnotation.class.getMethods()[0];
        Field field = TestAnnotation.class.getFields()[0];
        int min = field.getAnnotation(Check.class).min();
        if((int)field.get(annotation) < min){
            throw new Exception(field.getName() + "不应小于" + min + "请检查！");
        }
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PACKAGE, ElementType.CONSTRUCTOR,
        ElementType.PARAMETER, ElementType.TYPE_USE})
@Documented
@interface Check{
    int min() default 1;
    int max() default 100;
}