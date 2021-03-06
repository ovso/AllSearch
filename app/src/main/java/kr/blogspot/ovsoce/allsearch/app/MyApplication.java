package kr.blogspot.ovsoce.allsearch.app;

import android.app.Application;

import kr.blogspot.ovsoce.allsearch.common.Log;

public class MyApplication extends Application {
    private String mQuery;

    public void setQuery(String query) {
        Log.d("query = " + query);
        mQuery = query;
    }
    public String getQuery() {
        return mQuery;
    }

    private boolean searchMode = false; // multiple false, sync true
    public void setSearchMode(boolean mode) {
        searchMode = mode;
    }
    public boolean isSearchMode() {
        return searchMode;
    }
    public void remove(){
        mQuery = null;
        searchMode = true;
    }
    private boolean mSwipe = true;
    public void setSwipe(boolean swipe) {
        mSwipe = swipe;
    }
    public boolean isSwipe() {
        return mSwipe;
    }
}