package com.mylibrary.recyc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by MS on 2017/5/31.
 */
public abstract  class CommomAdapter<T> extends RecyclerView.Adapter<CommomViewHolder> {

    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;

    private  OnItemClickListener  itemClickListener;

    boolean showLoadingMore;

    public CommomAdapter(Context mContext, int mLayoutId, List<T> mDatas) {
        this.mContext = mContext;
        this.mLayoutId = mLayoutId;
        this.mDatas = mDatas;
    }

    @Override
    public CommomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommomViewHolder.getViewHolder(mContext,parent,mLayoutId);
    }

    @Override
    public void onBindViewHolder(CommomViewHolder holder,int position) {
        holder.updatePosition(position);
        convert(holder, mDatas.get(position));
        holder.setOnClickListener(mDatas.get(position),position,itemClickListener);
    }




    protected abstract void convert(CommomViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public  void addItems(List<T> results){
        mDatas.addAll(results);
        notifyDataSetChanged();
    }

    public void clear(){
        mDatas.clear();
        notifyDataSetChanged();
    }


    public int getItemColumnSpan(int position) {
        if (position == 0){
              return 2;
        }
        return 1;
    }

    public void loadMoreStart(){
        if(showLoadingMore)return ;
        showLoadingMore = true;
//        notifyItemInserted(getLoadingMoreItemPosition());
    }

    public void loadMorefinish() {
        if (!showLoadingMore) return;
        final int loadingPos = getLoadingMoreItemPosition();
        showLoadingMore = false;
        notifyItemRemoved(loadingPos);
    }


    public int getLoadingMoreItemPosition() {
        return showLoadingMore? getItemCount() -1 : RecyclerView.NO_POSITION;
    }


    public interface  OnItemClickListener<T>{
         void onclick(View v, T t, int postion);
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
