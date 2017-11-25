package com.gy.linjliang.nationsapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
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
    //详细信息界面
    private ImageView ima;
    private TextView renname;
    private TextView rensex;
    private TextView renlive;
    private TextView renplace;
    private TextView rennation;
    private TextView reninformation;

    //对话框
    private EditText dialogname;
    private EditText dialogsex;
    private EditText dialoglive;
    private EditText dialogplace;
    private EditText dialognation;
    private EditText dialoginformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_detail);

        p = (Info) getIntent().getSerializableExtra("Info"); // 接收
        //添加各内容
        ima = (ImageView)findViewById(R.id.ren_touxiang);
        ima.setImageResource(p.getImageindex());    //头像图片
        renname = (TextView)findViewById(R.id.ren_name);
        renname.setText(p.getName());   //名字
        rensex = (TextView)findViewById(R.id.ren_sex);
        rensex.setText(p.getSex());     //性别
        renlive = (TextView)findViewById(R.id.ren_live);
        renlive.setText(p.getLive());   //生卒
        renplace = (TextView)findViewById(R.id.ren_place);
        renplace.setText(p.getPlace());   //籍贯
        rennation = (TextView)findViewById(R.id.ren_nation);
        rennation.setText(p.getNation());   //国家
        reninformation = (TextView)findViewById(R.id.ren_information);
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
//                Toast.makeText(InfoDetail.this,p.getName(),Toast.LENGTH_LONG).show();
                // 自定义对话框的实现——使用 LayoutInflater 类
                LayoutInflater factory = LayoutInflater.from(InfoDetail.this);
                View newView = factory.inflate(R.layout.dialoglayout, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(InfoDetail.this);

                dialogname = (EditText) newView.findViewById(R.id.dialog_name);
                dialogsex = (EditText) newView.findViewById(R.id.dialog_sex);
                dialoglive = (EditText) newView.findViewById(R.id.dialog_live);
                dialogplace = (EditText) newView.findViewById(R.id.dialog_place);
                dialognation = (EditText) newView.findViewById(R.id.dialog_nation);
                dialoginformation = (EditText) newView.findViewById(R.id.dialog_information);

                //设置初始内容
                dialogname.setText(p.getName());
                dialogsex.setText(p.getSex());
                dialoglive.setText(p.getLive());
                dialogplace.setText(p.getPlace());
                dialognation.setText(p.getNation());
                dialoginformation.setText(p.getInformation());

                // 自定义对话框的实现
                builder.setView(newView);
                builder.setTitle("修改人物信息");
                builder.setPositiveButton("保存修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dialogname.length()==0) {
                            Toast.makeText(InfoDetail.this,"名字不能为空",Toast.LENGTH_SHORT).show();
                        }
                        if (dialogname.length() != 0) {
                            MyDataBase db = new MyDataBase(getBaseContext());
                            SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
                            sqLiteDatabase.execSQL("update t_table" +
                                    " set name = ? where id = ?", new Object[]{
                                    dialogname.getText().toString(), p.getId()});
                            sqLiteDatabase.close();
                        }
                        if (dialogsex.length() != 0) {
                            MyDataBase db = new MyDataBase(getBaseContext());
                            SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
                            sqLiteDatabase.execSQL("update t_table" +
                                    " set sex = ? where id = ?", new Object[]{
                                    dialogsex.getText().toString(), p.getId()});
                            sqLiteDatabase.close();
                        }
                        if (dialoglive.length() != 0) {
                            MyDataBase db = new MyDataBase(getBaseContext());
                            SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
                            sqLiteDatabase.execSQL("update t_table" +
                                    " set live = ? where id = ?", new Object[]{
                                    dialoglive.getText().toString(), p.getId()});
                            sqLiteDatabase.close();
                        }
                        if (dialogplace.length() != 0) {
                            MyDataBase db = new MyDataBase(getBaseContext());
                            SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
                            sqLiteDatabase.execSQL("update t_table" +
                                    " set place = ? where id = ?", new Object[]{
                                    dialogplace.getText().toString(), p.getId()});
                            sqLiteDatabase.close();
                        }
                        if (dialognation.length() != 0) {
                            MyDataBase db = new MyDataBase(getBaseContext());
                            SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
                            sqLiteDatabase.execSQL("update t_table" +
                                    " set nation = ? where id = ?", new Object[]{
                                    dialognation.getText().toString(), p.getId()});
                            sqLiteDatabase.close();
                        }
                        if (dialoginformation.length() != 0) {
                            MyDataBase db = new MyDataBase(getBaseContext());
                            SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
                            sqLiteDatabase.execSQL("update t_table" +
                                    " set information = ? where id = ?", new Object[]{
                                    dialoginformation.getText().toString(), p.getId()});
                            sqLiteDatabase.close();
                        }
                        dataUpdate();//更新到当前UI上
                        Toast.makeText(InfoDetail.this,"人物信息已修改",Toast.LENGTH_SHORT).show();
                        setResult(22,new Intent());
                    }
                });
                builder.setNegativeButton("放弃修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }

    // 将保存在数据库中的数据更新到UI中的函数
    public void dataUpdate(){
        try {
            MyDataBase db = new MyDataBase(getBaseContext());
            SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from t_table where id=" + p.getId(), null); //查询
            for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
//                Toast.makeText(InfoDetail.this, cursor.getString(cursor.getColumnIndex("name")), Toast.LENGTH_LONG).show();
                renname.setText(cursor.getString(cursor.getColumnIndex("name")));
                rensex.setText(cursor.getString(cursor.getColumnIndex("sex")));
                renlive.setText(cursor.getString(cursor.getColumnIndex("live")));
                renplace.setText(cursor.getString(cursor.getColumnIndex("place")));
                rennation.setText(cursor.getString(cursor.getColumnIndex("nation")));
                reninformation.setText(cursor.getString(cursor.getColumnIndex("information")));
            }
        }
        catch (SQLException e) {}
    }

}
