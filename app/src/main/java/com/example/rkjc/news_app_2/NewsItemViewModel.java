package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

public class NewsItemViewModel extends AndroidViewModel {

    private NewsItemRepositoty mRepository;
    private LiveData<List<NewsItem>> mAllNewsItems;

    public NewsItemViewModel (Application application) {
        super(application);
        mRepository = new NewsItemRepositoty(application);
        mAllNewsItems = mRepository.getAllNewsItems();
    }

    public LiveData<List<NewsItem>> getAllNewsItems() {
        return mAllNewsItems;
    }

    public void insert() {
        mRepository.insert();
    }

    public void deleteAll(){
        mRepository.deleteAllNewsItems();
    }
}
