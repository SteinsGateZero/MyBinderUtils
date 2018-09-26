package com.steinsgatezero.mybinder;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initFragment();
    }

    private void initFragment() {
        FragmentTransaction fm = getFragmentManager().beginTransaction();
        //添加显示第一个fragment
        BlankFragment fragment = new BlankFragment();
        fm.add(R.id.frame_container, fragment);

        fm.commitAllowingStateLoss();
    }
}
