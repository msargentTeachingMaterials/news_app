package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rkjc.news_app_2.data.NewsItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class NewsRecyclerViewAdapter  extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {
    private static final String TAG = "### NewsRecyclerViewAdapter ### ";
    private List<NewsItem> newsItemList;
    private Context mContext;


    public NewsRecyclerViewAdapter(Context context) {
        this.mContext = context;
    }

    public void SetItemArrayList(ArrayList<NewsItem> newsItemList){
        this.newsItemList = newsItemList;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.news_item, viewGroup, false);
        NewsViewHolder viewHolder = new NewsViewHolder(view);
        view.setOnClickListener(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder aViewHolder, int index) {
        NewsItem newsItem = newsItemList.get(index);
        aViewHolder.bind(newsItem);
    }

    public List<NewsItem> getNewsItemList() {
        return newsItemList;
    }

    public void setNewsItemList(List<NewsItem> newsItemList) {
        this.newsItemList = newsItemList;
    }


    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView imageView;
        TextView abstr;

        public NewsViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            abstr = (TextView) view.findViewById(R.id.abstr);
            imageView = (ImageView) view.findViewById(R.id.img);

        }

        public void bind(NewsItem newsItem) {
            title.setText(newsItem.getTitle());
            abstr.setText(newsItem.getTime());
            abstr.append(" . ");
            abstr.append(newsItem.getDescription());

            if(newsItem.getThumbURL() != null && !newsItem.getThumbURL().isEmpty())
            Picasso.get()
                    .load(newsItem.getThumbURL())
                    .into(imageView);

        }

        @Override
        public void onClick(View view) {
            NewsItem item = newsItemList.get(getAdapterPosition());
            view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrl())));
        }
    }

    @Override
    public int getItemCount() {
        return (null != newsItemList ? newsItemList.size() : 0);
    }


}
