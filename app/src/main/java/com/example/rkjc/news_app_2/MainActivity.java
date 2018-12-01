package com.example.rkjc.news_app_2;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.lifecycle.Observer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private NewsItemViewModel mNewsItemViewModel;
    private RecyclerView mRecyclerView;
    private NewsAdapter mAdapter;
    private NewsItemRepositoty mRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.news_recyclerview);

        mRepository = new NewsItemRepositoty(this.getApplication());
        mAdapter = new NewsAdapter(this, mNewsItemViewModel);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        mNewsItemViewModel = ViewModelProviders.of(this).get(NewsItemViewModel.class);

        mNewsItemViewModel.getAllNewsItems().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(@Nullable final List<NewsItem> newsItemList) {
                mAdapter.setNewsItem(newsItemList);
            }
        });
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            mNewsItemViewModel.deleteAll();
            mNewsItemViewModel.insert();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}





