package com.example.rkjc.news_app_2;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NewsItemDao {

    @Insert
    void insertNotes(List<NewsItem> items);

    @Query("DELETE FROM news_item")
    void deleteAllNotes ();

    @Query("SELECT * FROM news_item ORDER BY newsId ASC")
    LiveData<List<NewsItem>> getAllNotes();
}
