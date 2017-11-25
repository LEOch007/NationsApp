package com.gy.linjliang.nationsapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by lenovo on 2017/11/19.
 */

public class InfoDetail extends Activity {
    private boolean tag = false;
    private Info p; //商品

    private EditText dialog_name;
    private EditText dialog_sex;
    private EditText dialog_live;
    private EditText dialog_place;
    private EditText dialog_nation;
    private EditText dialog_information;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_detail);

        p = (Info) getIntent().getSerializableExtra("Info"); // 接收
        //添加各内容
        ImageView ima = (ImageView)findViewById(R.id.ren_touxiang);
        ima.setImageResource(p.getImageindex());    //头像图片
        TextView renname = (TextView)findViewById(R.id.ren_name);
        renname.setText(p.getName());   //名字
        TextView rennation = (TextView)findViewById(R.id.ren_nation);
        rennation.setText(p.getNation());   //国家
        TextView rensex = (TextView)findViewById(R.id.ren_sex);
        rensex.setText(p.getSex());     //性别
        TextView renlive = (TextView)findViewById(R.id.ren_live);
        renlive.setText(p.getLive());   //生卒
        TextView renplace = (TextView)findViewById(R.id.ren_place);
        renplace.setText(p.getPlace());   //籍贯
        TextView reninformation = (TextView)findViewById(R.id.ren_information);
        reninformation.setText(p.getInformation());   //信息

        //返回键的点击事件
        Button back = (Button) findViewById(R.id.back); //返回键
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //加入收藏夹
        final Button star = (Button) findViewById(R.id.star);
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tag) {
                    star.setBackground(getDrawable(R.mipmap.full_star));
                    tag = true;
                    Toast.makeText(InfoDetail.this,"已加入收藏夹",Toast.LENGTH_SHORT).show();
                    /*    ---- 发布消息 ----     */
                    EventBus.getDefault().post(p); //发布者 发布消息

                } else {
                    star.setBackground(getDrawable(R.mipmap.empty_star));
                    tag = false;
                }
            }
        });

        Button xiugai =(Button) findViewById(R.id.xiugaibutton);
        xiugai.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // 自定义对话框的实现——使用 LayoutInflater 类
                LayoutInflater factory = LayoutInflater.from(InfoDetail.this);
                View newView = factory.inflate(R.layout.dialoglayout, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(InfoDetail.this);

                dialog_name = (EditText) findViewById(R.id.dialog_name);
                dialog_sex = (EditText) findViewById(R.id.dialog_sex);
                dialog_live = (EditText) findViewById(R.id.dialog_live);
                dialog_place = (EditText) findViewById(R.id.dialog_place);
                dialog_nation = (EditText) findViewById(R.id.dialog_nation);
                dialog_information = (EditText) findViewById(R.id.dialog_information);

                //设置初始内容
                dialog_name.setText(p.getName());
                dialog_sex.setText(p.getSex());
                dialog_live.setText(p.getLive());
                dialog_place.setText(p.getPlace());
                dialog_nation.setText(p.getNation());
                dialog_information.setText(p.getInformation());

                // 自定义对话框的实现
                builder.setView(newView);
                builder.setTitle("修改人物信息");
                builder.setPositiveButton("保存修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dialog_name.length() != 0) {
                            MyDataBase db = new MyDataBase(getBaseContext());
                            SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
                            sqLiteDatabase.execSQL("update t_table" +
                                    " set name = ? where id = ?", new Object[]{
                                    dialog_name.getText().toString(), p.getId()});
                            sqLiteDatabase.close();
                        }
                        if (dialog_sex.length() != 0) {
                            MyDataBase db = new MyDataBase(getBaseContext());
                            SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
                            sqLiteDatabase.execSQL("update t_table" +
                                    " set sex = ? where id = ?", new Object[]{
                                    dialog_sex.getText().toString(), p.getId()});
                            sqLiteDatabase.close();
                        }
                        if (dialog_live.length() != 0) {
                            MyDataBase db = new MyDataBase(getBaseContext());
                            SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
                            sqLiteDatabase.execSQL("update t_table" +
                                    " set live = ? where id = ?", new Object[]{
                                    dialog_live.getText().toString(), p.getId()});
                            sqLiteDatabase.close();
                        }
                        if (dialog_place.length() != 0) {
                            MyDataBase db = new MyDataBase(getBaseContext());
                            SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
                            sqLiteDatabase.execSQL("update t_table" +
                                    " set place = ? where id = ?", new Object[]{
                                    dialog_place.getText().toString(), p.getId()});
                            sqLiteDatabase.close();
                        }
                        if (dialog_nation.length() != 0) {
                            MyDataBase db = new MyDataBase(getBaseContext());
                            SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
                            sqLiteDatabase.execSQL("update t_table" +
                                    " set nation = ? where id = ?", new Object[]{
                                    dialog_nation.getText().toString(), p.getId()});
                            sqLiteDatabase.close();
                        }
                        if (dialog_information.length() != 0) {
                            MyDataBase db = new MyDataBase(getBaseContext());
                            SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
                            sqLiteDatabase.execSQL("update t_table" +
                                    " set information = ? where id = ?", new Object[]{
                                    dialog_information.getText().toString(), p.getId()});
                            sqLiteDatabase.close();
                        }
//                        dataUpdate();
                    }
                });
                builder.setNegativeButton("放弃修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
            }
        });
    }
}
