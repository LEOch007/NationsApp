package com.gy.linjliang.nationsapp;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/11/8.
 */

public class Info implements Serializable {
    private int imageindex; //头像
    private String name; //姓名
    private String sex; //性别
    private String live; //生卒时间
    private String place; //籍贯
    private String nation; //主效势力
    private String information; //其他信息

    public Info(int index,String name, String sex, String live, String place,String nation,String information) {
        this.imageindex = index;
        this.name = name;
        this.sex = sex;
        this.live = live;
        this.place = place;
        this.nation = nation;
        this.information = information;
    }

    public int getImageindex(){return imageindex;}  //人物头像
    public String getName(){
        return name;
    }  //人物姓名
    public String getSex(){
        return sex;
    }  //人物性别
    public String getLive(){
        return live;
    }  //人物生卒年
    public String getPlace(){return place;} //人物籍贯
    public String getNation(){return nation;} //主效势力
    public String getInformation(){
        return information;
    }  //其他信息

}
