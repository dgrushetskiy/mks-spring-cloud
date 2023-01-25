package ru.gothmog.ws.auth.api.mapper;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MapperConverter {

  @AliasFor(annotation = Component.class) String value() default "";
}
