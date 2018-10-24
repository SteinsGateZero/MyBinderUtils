package com.steinsgatezero.mybinder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.steinsgatezero.coolbinder.IntentKey;
import com.steinsgatezero.coolbinderutils.IntentBinder;

public class Main2Activity extends AppCompatActivity {

    @IntentKey("key1")
    String name;
    @IntentKey("key2")
    int name2;
    @IntentKey("keyinfo")
    TestInfo info;
    @IntentKey("keyinfo2")
    TestInfo2 info2;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //注入
        IntentBinder.inject(this);
        textView = findViewById(R.id.tv_description);
        if (info != null) {
            textView.setText("info:" + info.toString() + "\n" + "info2:" + info2.toString());
        } else {
            textView.setText("传值\n" + "name:" + name + "\n" + "name2:" + name2);
        }

    }

}
