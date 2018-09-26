package com.steinsgatezero.coolbinder;


import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.steinsgatezero.coolbinder.IntentKey.TYPE_ACTIVITY;
import static com.steinsgatezero.coolbinder.IntentKey.TYPE_FRAGMENT;


@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
@IntDef({TYPE_ACTIVITY, TYPE_FRAGMENT})
@interface IntentType {

}
