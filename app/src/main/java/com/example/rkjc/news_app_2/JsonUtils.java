package com.example.rkjc.news_app_2;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class JsonUtils {
    public static ArrayList<NewsItem> parseNews(String jsonResult){
        ArrayList<NewsItem> newsItems = new ArrayList<>();
        try {
            JSONObject mainJSONObject = new JSONObject(jsonResult);
            JSONArray articles = mainJSONObject.getJSONArray("articles");

            for(int i = 0; i < articles.length(); i++){
                JSONObject article = articles.getJSONObject(i);
                newsItems.add(new NewsItem(article.getString("title"),
                        article.getString("description"),article.getString("url"),article.getString("urlToImage"),article.getString("publishedAt")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsItems;
    }
}


