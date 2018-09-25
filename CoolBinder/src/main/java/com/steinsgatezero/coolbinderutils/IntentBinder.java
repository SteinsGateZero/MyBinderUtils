package com.steinsgatezero.coolbinderutils;

import android.app.Activity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class IntentBinder {
    public static void inject(Activity activity) {
        Class clazz;
        try {
            clazz = Class.forName("com.steinsgatezero.MyIntentBinderUtils");
            Method method = clazz.getMethod("bind", Activity.class);
            method.invoke(null,activity);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
