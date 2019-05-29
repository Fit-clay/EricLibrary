package com.eric.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.eric.base.utils.ToSystemPageUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_to_web).setOnClickListener(v->{
            ToSystemPageUtils.toChream(MainActivity.this,"https://www.cnblogs.com/markgg/p/6635705.html");
        });
    }
}
