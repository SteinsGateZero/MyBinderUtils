package com.steinsgatezero.mybinder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.steinsgatezero.coolbinder.IntentKey;
import com.steinsgatezero.coolbinderutils.IntentBinder;

public class Main2Activity extends AppCompatActivity {

    @IntentKey("key1")
    String name;

    @IntentKey("key2")
    String name2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        IntentBinder.inject(this);
        Log.e("来了哦", name + " " + name2);
    }
}
