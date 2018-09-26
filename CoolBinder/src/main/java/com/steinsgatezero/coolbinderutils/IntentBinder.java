package com.steinsgatezero.coolbinderutils;

import android.app.Activity;
import android.app.Fragment;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class IntentBinder {

    public static void inject(Activity obj) {
        doInject(obj);
    }

    public static void inject(Fragment obj) {
        doInject(obj);
    }

    private static void doInject(Object obj) {
        Class clazz;
        try {
            clazz = Class.forName("com.steinsgatezero.MyIntentBinderUtils");
            Method method = clazz.getMethod("bind", Object.class);
            method.invoke(null, obj);

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
