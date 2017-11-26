package com.gy.linjliang.nationsapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.OvershootInLeftAnimator;

/**
 * Created by lenovo on 2017/11/26.
 */

public class Favorite extends AppCompatActivity {
    private RecyclerView mfavorite; //收藏夹
    private MyAdapter shoucangAdapter;
    private List<Info> shoucangList; //收藏夹的List

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favoite_layout);

        /*    --- RecyclerView ---  */
        mfavorite =(RecyclerView) findViewById(R.id.shoucangjia);
        mfavorite.setLayoutManager(new LinearLayoutManager(this)); //垂直布局
        //recyclerView 添加内容
        updateUI();
    }

    //更新UI函数
    public void updateUI() {
        shoucangList = new ArrayList<Info>();
        MyDataBase db = new MyDataBase(getBaseContext());
        SQLiteDatabase sq1=db.getReadableDatabase();
        Cursor cursor = sq1.rawQuery("select * from t_table where flag=1;",null); //查询标记为1的收藏词条
        for(cursor.moveToFirst();!(cursor.isAfterLast());cursor.moveToNext()){
            Info in = new Info(cursor.getInt(cursor.getColumnIndex("image")), cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("sex")), cursor.getString(cursor.getColumnIndex("live")),
                    cursor.getString(cursor.getColumnIndex("place")), cursor.getString(cursor.getColumnIndex("nation")),
                    cursor.getString(cursor.getColumnIndex("information")),cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getInt(cursor.getColumnIndex("flag")));
            shoucangList.add(in);
        }
        sq1.close();

        shoucangAdapter = new MyAdapter(this, shoucangList);
        /*  网上的库 添加动画  */
        ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(shoucangAdapter);
        animationAdapter.setDuration(1000);
        mfavorite.setAdapter(animationAdapter);
        mfavorite.setItemAnimator(new OvershootInLeftAnimator());
        shoucangAdapter.setOnItemClickLitener(new MyAdapter.OnItemClickLitener() {
            //点击事件
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(Favorite.this, InfoDetail.class); //显式调用
                Info temp = shoucangList.get(position); //第i个Info商品信息
                intent.putExtra("Info", temp);
                startActivityForResult(intent,4);
            }
            //长按事件
            @Override
            public boolean onItemLongClick(View view, final int position) {
                //简单对话框的设计
                AlertDialog.Builder message = new AlertDialog.Builder(Favorite.this);
                message.setTitle("移除收藏词条");
                message.setMessage("从收藏夹中移除该词条？");
                message.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                message.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        shoucangList.remove(position);
                        shoucangAdapter.notifyDataSetChanged();
                    }
                });
                message.create().show();
                return true;
            }

        });
    }

    //接受返回的结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==4 && resultCode==22){
            updateUI(); //更新的当前界面
        }
    }
}

