package com.steinsgatezero.mybinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void sendMsg(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.bt1:
                intent = new Intent(this, Main2Activity.class);
                intent.putExtra("key1", "来了啊1");
                intent.putExtra("key2", 2);
                break;
            case R.id.bt2:
                intent = new Intent(this, Main2Activity.class);
                TestInfo info = new TestInfo();
                info.setId(101);
                info.setMsg("传值");
                TestInfo2 info2 = new TestInfo2();
                info2.setId(102);
                info2.setMsg("传值2");
                intent.putExtra("keyinfo", info);
                intent.putExtra("keyinfo2", info2);
                break;
            case R.id.bt3:
                intent = new Intent(this, Main3Activity.class);
                TestInfo info3 = new TestInfo();
                info3.setId(103);
                info3.setMsg("传值3");
                intent.putExtra("keyinfo3", info3);
                break;
        }
        startActivity(intent);
    }
}
