package com.steinsgatezero.mybinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this, Main2Activity.class);
    }

    public void sendMsg(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                intent.putExtra("key1", "来了啊1");
                intent.putExtra("key2", 2);
                break;
            case R.id.bt2:
                TestInfo info = new TestInfo();
                info.setId(101);
                info.setMsg("传值");
                TestInfo2 info2 = new TestInfo2();
                info2.setId(102);
                info2.setMsg("传值2");
                intent.putExtra("keyinfo", info);
                intent.putExtra("keyinfo2", info2);
                break;
        }
        startActivity(intent);
    }
}
