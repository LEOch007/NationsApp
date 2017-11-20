package com.gy.linjliang.nationsapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
    private MyDataBase dbhelper = new MyDataBase(InfoList.this,"t_db",null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_list);

        //数据库数据初始化
        initdata();
        Infos = new ArrayList<Info>();
        //从数据库中添加数据到List中
        SQLiteDatabase db1=dbhelper.getReadableDatabase();
        Cursor cursor = db1.rawQuery("select * from t_table",null); //查询
        for(cursor.moveToFirst();!(cursor.isAfterLast());cursor.moveToNext()){
            Info in = new Info(cursor.getInt(cursor.getColumnIndex("image")), cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("sex")), cursor.getString(cursor.getColumnIndex("live")),
                    cursor.getString(cursor.getColumnIndex("place")), cursor.getString(cursor.getColumnIndex("nation")),
                    cursor.getString(cursor.getColumnIndex("information")));
            Infos.add(in);
        }

        /*    --- RecyclerView ---  */
        mRecycleView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this)); //垂直布局
        myAdapter = new MyAdapter(this, Infos);
        /*  网上的库 添加动画  */
        ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(myAdapter);
        animationAdapter.setDuration(1000);
        mRecycleView.setAdapter(animationAdapter);
        mRecycleView.setItemAnimator(new OvershootInLeftAnimator());

        myAdapter.setOnItemClickLitener(new MyAdapter.OnItemClickLitener()
        {
            //点击事件
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(InfoList.this, InfoDetail.class); //显式调用
                Info temp = Infos.get(position); //第i个Info商品信息
                intent.putExtra("Info", temp);
                startActivityForResult(intent,1);
            }
            //长按事件
            @Override
            public boolean onItemLongClick(View view, final int position) {
                Toast.makeText(InfoList.this, "词条已被删除",Toast.LENGTH_SHORT).show();
                Infos.remove(position);
                myAdapter.notifyDataSetChanged();
                return true;
            }

        });

        //返回键的点击事件
        Button back = (Button) findViewById(R.id.backbutton); //返回键
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initdata(){

        SQLiteDatabase db = dbhelper.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put("id",0);cv.put("name","关羽");cv.put("sex","男");cv.put("live","？ - 219");
        cv.put("place","司隶河东郡解");cv.put("nation","蜀");cv.put("information","66");cv.put("image",R.mipmap.guanyu);
        db.insert("t_table",null,cv);

        ContentValues cv1=new ContentValues();
        cv1.put("id",1);cv1.put("name","刘备");cv1.put("sex","男");cv1.put("live","161 - 223");
        cv1.put("place","幽州涿郡涿");cv1.put("nation","蜀");cv1.put("information","66");cv1.put("image",R.mipmap.liubei);
        db.insert("t_table",null,cv1);

        ContentValues cv2=new ContentValues();
        cv2.put("id",2);cv2.put("name","张飞");cv2.put("sex","男");cv2.put("live","？ - 221");
        cv2.put("place","幽州涿郡");cv2.put("nation","蜀");cv2.put("information","66");cv2.put("image",R.mipmap.zhangfei);
        db.insert("t_table",null,cv2);

        ContentValues cv3=new ContentValues();
        cv3.put("id",3);cv3.put("name","诸葛亮");cv3.put("sex","男");cv3.put("live","181 - 234");
        cv3.put("place","徐州琅邪国阳都");cv3.put("nation","蜀");cv3.put("information","66");cv3.put("image",R.mipmap.zhugeliang);
        db.insert("t_table",null,cv3);

        ContentValues cv4=new ContentValues();
        cv4.put("id",4);cv4.put("name","曹操");cv4.put("sex","男");cv4.put("live","155 - 220");
        cv4.put("place","豫州沛国谯");cv4.put("nation","魏");cv4.put("information","66");cv4.put("image",R.mipmap.caocao);
        db.insert("t_table",null,cv4);

        ContentValues cv5=new ContentValues();
        cv5.put("id",5);cv5.put("name","曹丕");cv5.put("sex","男");cv5.put("live","187 - 226");
        cv5.put("place","豫州沛国谯");cv5.put("nation","魏");cv5.put("information","66");cv5.put("image",R.mipmap.caopi);
        db.insert("t_table",null,cv5);

        ContentValues cv6=new ContentValues();
        cv6.put("id",6);cv6.put("name","司马懿");cv6.put("sex","男");cv6.put("live","179 - 251");
        cv6.put("place","司隶河内郡温");cv6.put("nation","魏");cv6.put("information","66");cv6.put("image",R.mipmap.simayi);
        db.insert("t_table",null,cv6);


        ContentValues cv7=new ContentValues();
        cv7.put("id",7);cv7.put("name","孙权");cv7.put("sex","男");cv7.put("live","182 - 252");
        cv7.put("place","扬州吴郡富春");cv7.put("nation","吴");cv7.put("information","66");cv7.put("image",R.mipmap.sunquan);
        db.insert("t_table",null,cv7);

        ContentValues cv8=new ContentValues();
        cv8.put("id",8);cv8.put("name","周瑜");cv8.put("sex"," 男");cv8.put("live","175 - 210");
        cv8.put("place","扬州庐江郡舒");cv8.put("nation","吴");cv8.put("information","66");cv8.put("image",R.mipmap.zhouyu);
        db.insert("t_table",null,cv8);

        ContentValues cv9=new ContentValues();
        cv9.put("id",9);cv9.put("name","鲁肃");cv9.put("sex","男");cv9.put("live","172 - 217");
        cv9.put("place","徐州下邳国东城");cv9.put("nation","吴");cv9.put("information","66");cv9.put("image",R.mipmap.lusu);
        db.insert("t_table",null,cv9);

        db.close();
    }

}