package com.gy.linjliang.nationsapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.OvershootInLeftAnimator;

/**
 * Created by lenovo on 2017/11/9.
 */

public class InfoList extends Activity{
    private RecyclerView mRecycleView;
    private MyAdapter myAdapter;
    private List<Info> Infos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_list);

        Infos = new ArrayList<Info>(){{
            add(new Info(R.mipmap.caocao,getString(R.string.caocao_name),
                    getString(R.string.caocao_sex),getString(R.string.caocao_live),getString(R.string.caocao_place),
                    getString(R.string.caocao_nation),getString(R.string.caocao_information)));
        }};

        /*    --- RecyclerView ---  */
        mRecycleView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this)); //垂直布局
        myAdapter = new MyAdapter(this, Infos);
        /*  网上的库 添加动画  */
        ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(myAdapter);
        animationAdapter.setDuration(1000);
        mRecycleView.setAdapter(animationAdapter);
        mRecycleView.setItemAnimator(new OvershootInLeftAnimator());
    }
}
