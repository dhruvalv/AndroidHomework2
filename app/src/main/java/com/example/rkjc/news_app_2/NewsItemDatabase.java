package com.example.rkjc.news_app_2;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.rkjc.news_app_2.NetworkUtils.*;

@Database(entities = {NewsItem.class}, version = 1)
public abstract class NewsItemDatabase extends RoomDatabase {
    public abstract NewsItemDao newsItemDao();

    private static volatile NewsItemDatabase INSTANCE;

    public static synchronized NewsItemDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    NewsItemDatabase.class, "newsItem_database")
                    .addCallback(roomCallback)
                    .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    private static class populateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private NewsItemDao newsItemDao;

        private populateDBAsyncTask(NewsItemDatabase db){
            this.newsItemDao = db.newsItemDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String jSONResp = null;
            try {
                jSONResp = getResponseFromHttpUrl(buildURL());
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<NewsItem> newsList = JsonUtils.parseNews(jSONResp);
            newsItemDao.insertNotes(newsList);
            return null;
        }
    }
}
