package com.gy.linjliang.nationsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.OvershootInLeftAnimator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonwei=(Button) findViewById(R.id.buttonwei);
        final Button buttonshu=(Button) findViewById(R.id.buttonshu);
        final Button buttonwu=(Button) findViewById(R.id.buttonwu);

        buttonwei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mes=new Intent(MainActivity.this,InfoList.class);
                mes.putExtra("countryname","魏");
                startActivity(mes);

            }
        });

        buttonshu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mes=new Intent(MainActivity.this,InfoList.class);
                mes.putExtra("countryname","蜀");
                startActivity(mes);
            }
        });

        buttonwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mes=new Intent(MainActivity.this,InfoList.class);
                mes.putExtra("countryname","吴");
                startActivity(mes);
            }
        });
    }
}
