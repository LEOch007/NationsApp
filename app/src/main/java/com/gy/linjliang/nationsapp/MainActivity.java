package com.gy.linjliang.nationsapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.OvershootInLeftAnimator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView music_on;
    private ImageView music_off;
    private MediaPlayer mediaPlayer=new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonwei=(Button) findViewById(R.id.buttonwei);
        final Button buttonshu=(Button) findViewById(R.id.buttonshu);
        final Button buttonwu=(Button) findViewById(R.id.buttonwu);

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

        //跳转收藏夹
        ImageView btnfavorite = (ImageView) findViewById(R.id.favorite);
        btnfavorite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent newintent = new Intent(MainActivity.this,Favorite.class);
                startActivity(newintent);
            }
        });

        // 打开用户帮助
        final ImageView user_help = (ImageView) findViewById(R.id.user_help);

        ImageView getHelp = (ImageView) findViewById(R.id.getHelp);
        getHelp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                user_help.setVisibility(View.VISIBLE);
            }
        });
        user_help.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                user_help.setVisibility(View.GONE);
            }
        });


        //音乐播放器
        music_on=(ImageView) findViewById(R.id.music_on);
        music_off=(ImageView) findViewById(R.id.music_off);
        music_on.setOnClickListener(this);
        music_off.setOnClickListener(this);
        if(ContextCompat.checkSelfPermission(MainActivity.this,"android.permission.WRITE_EXTERNAL_STORAGE")!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"},1);
        }else{
            initMediaPlayer();
        }

    }

    //初始化音乐播放器
    private void initMediaPlayer(){
        try{
            File file=new File(Environment.getExternalStorageDirectory(),"bgm_for_test.mp3");
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //动态申请权限
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    initMediaPlayer();
                } else {
                    Toast.makeText(this, "拒绝权限将无法使用音乐播放功能", Toast.LENGTH_SHORT).show();
                    System.exit(0);

                }
                break;

            default:
                break;
        }

    }
    //按钮
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.music_off:
                if(!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                    music_on.setVisibility(View.VISIBLE);
                    music_off.setVisibility(View.GONE);
                    mediaPlayer.setLooping(true);
                }
                break;
            case R.id.music_on:
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.reset();
                    initMediaPlayer();
                    music_off.setVisibility(View.VISIBLE);
                    music_on.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }
}
