package kr.blogspot.ovsoce.allsearch.main;

import android.content.Context;
import android.view.View;

import kr.blogspot.ovsoce.allsearch.R;
import kr.blogspot.ovsoce.allsearch.app.MyApplication;
import kr.blogspot.ovsoce.allsearch.common.Log;

/**
 * Created by ovso on 2016. 2. 19..
 */
public class MainModel {
    public void onQueryTextChange(Context context, String query) {
        ((MyApplication)context.getApplicationContext()).setQuery(query);
    }
    public void clearQuery(Context context) {
        ((MyApplication)context.getApplicationContext()).setQuery(null);
    }
    public void setSearchMode(Context context) {
        MyApplication app = ((MyApplication)context.getApplicationContext());
        app.setSearchMode(!app.isSearchMode());
        Log.d("searchMode = " + app.isSearchMode());
    }
    public boolean isSearchMode(Context context) {
        return ((MyApplication)context.getApplicationContext()).isSearchMode();
    }

    public String getToolbarTitle(Context context) {
        boolean searchMode = ((MyApplication)context.getApplicationContext()).isSearchMode();
        if(searchMode) {
            return context.getString(R.string.text_search_multiple);
        } else {
            return context.getString(R.string.text_search_sync);
        }
    }

}
