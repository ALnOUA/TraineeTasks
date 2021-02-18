package shop.utils.annotations;

import shop.model.Food;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SetLastUseDate{
       int setExpirationDays() default 1;
}

class ExpirationDaysSetter {

    public void process(Object instance, Food food) {
        Class<?> clazz = instance.getClass();
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.isAnnotationPresent(SetLastUseDate.class)) {
                SetLastUseDate annotation = m.getAnnotation(SetLastUseDate.class);
                food.setExpirationDays(annotation.setExpirationDays());
            }
        }
    }
}