package com.gy.linjliang.nationsapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    }
}
