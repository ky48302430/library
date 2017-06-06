package com.mylibrary.recyc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by MS on 2017/6/1.
 */

public class CommomViewHolder<T> extends RecyclerView.ViewHolder{

    public View mConvertView;
    private int  mPostion;
    public CommomViewHolder(View itemView) {
        super(itemView);
        mConvertView = itemView;
    }

    public static CommomViewHolder getViewHolder(Context context, ViewGroup parent, int layoutId){
        View view  = LayoutInflater.from(context).inflate(layoutId,parent,false);
        return new CommomViewHolder(view);
    }

    public View getView(int resId){
       View view =  mConvertView.findViewById(resId);
       return  view;
    }

    public void  updatePosition(int postion){
        mPostion = postion;
    }

    public void setOnClickListener(final T t,final int postion,final CommomAdapter.OnItemClickListener itemClickListener) {
        mConvertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != itemClickListener){
                    itemClickListener.onclick(v,t,postion);
                }
            }
        });
    }
}
