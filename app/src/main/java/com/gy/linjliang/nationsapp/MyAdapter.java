package com.gy.linjliang.nationsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lenovo on 2017/11/8.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private LayoutInflater inflater;
    private Context mContext;
    private List<Info> Infos;
    private OnItemClickLitener mOnItemClickLitener;

    //创建构造参数
    public MyAdapter(Context context , List<Info> datas){
        this.mContext = context;
        this.Infos = datas;
        inflater = LayoutInflater.from(context);
    }
    //必须重载的三大函数
    @Override
    public int getItemCount() {
        return Infos.size();
    }
    //创建ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_view, parent , false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }
    //接口类
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        boolean onItemLongClick(View view , int position);
    }
    public void setOnItemClickLitener(OnItemClickLitener mmOnItemClickLitener)
    {
        this.mOnItemClickLitener = mmOnItemClickLitener;
    }
    //绑定ViewHolder
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        //内容赋值
        holder.iv.setImageResource(Infos.get(position).getImageindex());
        holder.tv1.setText(Infos.get(position).getName());
        holder.tv2.setText(Infos.get(position).getNation());
        holder.tv3.setText(Infos.get(position).getSex());
        holder.tv4.setText(Infos.get(position).getLive());
        holder.tv5.setText(Infos.get(position).getPlace());
        //
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }

    /*                -- Viewholder类 --                  */
    //自定义Viewholder类
    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv; //头像
        TextView tv1; //名字
        TextView tv2; //主效势力
        TextView tv3; //性别
        TextView tv4; //生卒年
        TextView tv5; //籍贯
        View v;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.touxiang);
            tv1 = (TextView) itemView.findViewById(R.id.mingzi);
            tv2 = (TextView) itemView.findViewById(R.id.nation);
            tv3 = (TextView) itemView.findViewById(R.id.xingbie);
            tv4 = (TextView) itemView.findViewById(R.id.shengzu);
            tv5 = (TextView) itemView.findViewById(R.id.jiguan);
            v = itemView;
        }
    }
}
