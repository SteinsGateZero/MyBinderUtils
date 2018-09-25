package com.steinsgatezero.mybinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("key1","来了啊1");
        intent.putExtra("key2","来了啊2");
        startActivity(intent);
    }
}
