package com.steinsgatezero.coolbinder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.steinsgatezero.coolbinder.IntentKey.Type.ACTIVITY;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface IntentKey {

    enum Type{
        ACTIVITY,
        FRAGMENT
    }

     Type intentType() default ACTIVITY;

    String value();
}
