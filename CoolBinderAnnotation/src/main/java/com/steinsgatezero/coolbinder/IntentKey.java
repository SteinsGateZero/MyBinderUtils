package com.steinsgatezero.coolbinder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface IntentKey {

     int TYPE_ACTIVITY = 0;
     int TYPE_FRAGMENT = 1;

    @IntentType int intentType() default TYPE_ACTIVITY;

    String value();
}
