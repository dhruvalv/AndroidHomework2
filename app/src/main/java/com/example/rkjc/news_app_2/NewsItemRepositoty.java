package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import java.io.IOException;
import java.util.List;

public class NewsItemRepositoty {

    private NewsItemDao newsItemDao;
    private LiveData<List<NewsItem>> allNewsItems;

    public NewsItemRepositoty(Application application) {
        NewsItemDatabase database = NewsItemDatabase.getDatabase(application);
        newsItemDao = database.newsItemDao();
        allNewsItems = newsItemDao.getAllNotes();
    }

    public void insert() {
        new InsertNewsItemsAsyncTask(newsItemDao).execute();
    }

    public void deleteAllNewsItems() {
        new DeleteNewsItemsAsyncTask(newsItemDao).execute();
    }

    public LiveData<List<NewsItem>> getAllNewsItems() {
        return allNewsItems;
    }

    private static class InsertNewsItemsAsyncTask extends AsyncTask<Void, Void, Void> {

        private NewsItemDao newsItemDao;

        private InsertNewsItemsAsyncTask(NewsItemDao newsItemDao) {
            this.newsItemDao = newsItemDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String jSONResp = null;
            try {
                jSONResp = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildURL());
                List<NewsItem> newsList = JsonUtils.parseNews(jSONResp);
                newsItemDao.insertNotes(newsList);
            } catch (IOException e) {
                e.printStackTrace();
            }
          return null;
        }
    }

    private static class DeleteNewsItemsAsyncTask extends AsyncTask<Void, Void, Void> {

        private NewsItemDao newsItemDao;

        private DeleteNewsItemsAsyncTask(NewsItemDao newsItemDao) {
            this.newsItemDao = newsItemDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            newsItemDao.deleteAllNotes();
            return null;
        }
    }
}

