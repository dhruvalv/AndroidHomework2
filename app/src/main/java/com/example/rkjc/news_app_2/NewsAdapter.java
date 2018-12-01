package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder> {
    Context mContext;
    private final LayoutInflater mInflater;
    List<NewsItem> itemList;
    private NewsItemViewModel viewModel;

    public NewsAdapter(Context context, NewsItemViewModel viewModel) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        this.viewModel = viewModel;
    }

    @Override
    public NewsAdapter.NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        boolean shouldAttachToParentImmediately = false;
        View view = mInflater.inflate(R.layout.news_item, parent, shouldAttachToParentImmediately);
        NewsItemViewHolder viewHolder = new NewsItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsAdapter.NewsItemViewHolder holder, int position) {
        if (itemList != null) {
            holder.bind(position);
        } else {
            holder.title.setText("Hit Refresh");
        }
    }

    void setNewsItem(List<NewsItem> newsItemList){
        itemList = newsItemList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (itemList != null)
            return itemList.size();
        else return 0;
    }


    public class NewsItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView description;
        TextView date;

        public NewsItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            description = (TextView) itemView.findViewById(R.id.tv_description);
            date = (TextView) itemView.findViewById(R.id.tv_date);
        }

        void bind(final int listIndex) {
            title.setText("Title :" + itemList.get(listIndex).getTitle());
            description.setText("Description :" + itemList.get(listIndex).getDescription());
            date.setText("Date :" + itemList.get(listIndex).getPublishedAt());
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String urlString = itemList.get(getAdapterPosition()).getUrl();
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(urlString));
            mContext.startActivity(i);
        }
    }

    public List<NewsItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<NewsItem> itemList) {
        this.itemList = itemList;
    }
}
