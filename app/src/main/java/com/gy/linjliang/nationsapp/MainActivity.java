package com.gy.linjliang.nationsapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.OvershootInLeftAnimator;

public class MainActivity extends AppCompatActivity {
    private boolean tag=false; //点击浮动按钮变化
    private ImageView mfab;
    private RecyclerView mshoucangjia; //收藏夹
    private MyAdapter shoucangAdapter;
    private List<Info> shoucangList; //收藏夹的List
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonwei=(Button) findViewById(R.id.buttonwei);
        final Button buttonshu=(Button) findViewById(R.id.buttonshu);
        final Button buttonwu=(Button) findViewById(R.id.buttonwu);
//        final ImageView map = (ImageView)findViewById(R.id.imageView);

        //三国各自的button
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

        /*    --- RecyclerView ---  */
        shoucangList = new ArrayList<Info>();
//        shoucangList.add(new Info(R.mipmap.caocao,"haha","ha","ha","haha","hah","ahah",2,0));
        mshoucangjia =(RecyclerView) findViewById(R.id.shoucangjia);
        mshoucangjia.setLayoutManager(new LinearLayoutManager(this)); //垂直布局
        //recyclerView 添加内容
        udateUI();

        //浮动按钮
        final TextView tubiao = (TextView)findViewById(R.id.tubiao);
        final TextView wenzi = (TextView)findViewById(R.id.wenzi);
        final View selfline = findViewById(R.id.selfline);

//        mfab = (ImageView) findViewById(R.id.favorite);
//        mfab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(!tag) {
//                    mfab.setImageResource(R.mipmap.mainpage); //切换图像
//                    //设置可见
//                    mshoucangjia.setVisibility(View.VISIBLE);
//                    tubiao.setVisibility(View.VISIBLE);
//                    wenzi.setVisibility(View.VISIBLE);
//                    selfline.setVisibility(View.VISIBLE);
//                    //设置不可见
//                    map.setVisibility(View.GONE);
//                    buttonwei.setVisibility(View.GONE);
//                    buttonshu.setVisibility(View.GONE);
//                    buttonwu.setVisibility(View.GONE);
//                    tag = true;
//                }
//                else{
//                    mfab.setImageResource(R.mipmap.shoplist); //切换图像
//                    //设置不可见
//                    mshoucangjia.setVisibility(View.GONE);
//                    tubiao.setVisibility(View.GONE);
//                    wenzi.setVisibility(View.GONE);
//                    selfline.setVisibility(View.GONE);
//                    //设置可见
//                    map.setVisibility(View.VISIBLE);
//                    buttonwei.setVisibility(View.VISIBLE);
//                    buttonshu.setVisibility(View.VISIBLE);
//                    buttonwu.setVisibility(View.VISIBLE);
//                    tag = false;
//                }
//            }
//        });

        /*     ------   注册订阅者  -------   */
        EventBus.getDefault().register(this); //订阅消息

    }
    //更新UI函数
    public void udateUI(){
        shoucangAdapter = new MyAdapter(this, shoucangList);
        /*  网上的库 添加动画  */
        ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(shoucangAdapter);
        animationAdapter.setDuration(1000);
        mshoucangjia.setAdapter(animationAdapter);
        mshoucangjia.setItemAnimator(new OvershootInLeftAnimator());
        shoucangAdapter.setOnItemClickLitener(new MyAdapter.OnItemClickLitener()
        {
            //点击事件
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, InfoDetail.class); //显式调用
                Info temp = shoucangList.get(position); //第i个Info商品信息
                intent.putExtra("Info", temp);
                startActivityForResult(intent,1);
            }
            //长按事件
            @Override
            public boolean onItemLongClick(View view, final int position) {
                //简单对话框的设计
                AlertDialog.Builder message = new AlertDialog.Builder(MainActivity.this);
                message.setTitle("移除收藏词条");
                message.setMessage("从收藏夹中移除" + shoucangList.get(position).getName()+"？");
                message.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {}
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


    //取消订阅
    @Override
    protected void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    //订阅方法
    @Subscribe(threadMode = ThreadMode.MAIN) //选择线程模式
    public void onMessageEvent(Info ren){
//        Toast.makeText(MainActivity.this,ren.getName(),Toast.LENGTH_LONG).show();
//        shoucangList.add(new Info(R.mipmap.caocao,"haha","ha","ha","haha","hah","ahah",2,0));
        shoucangList.add(ren);
        udateUI();
    }
}
