package kr.blogspot.ovsoce.allsearch.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import kr.blogspot.ovsoce.allsearch.R;
import kr.blogspot.ovsoce.allsearch.app.MyApplication;
import kr.blogspot.ovsoce.allsearch.common.Log;

/**
 * Created by ovso on 2016. 2. 19..
 */
public class PlaceholderModel {
    public String getUrl(Context context, int sectionNumber) {
        //getFullUrl(context, sectionNumber);
        String query = ((MyApplication)context.getApplicationContext()).getQuery();

        boolean searchMode = ((MyApplication)context.getApplicationContext()).isSearchMode();

        Log.d("searchMode = " + searchMode);
        Log.d("query = " + query);
        if(searchMode) {
            if(TextUtils.isEmpty(query)) {
                return context.getResources().getStringArray(R.array.section_url)[sectionNumber];
            } else {
                return context.getResources().getStringArray(R.array.section_search_url)[sectionNumber]+query;
            }
        } else {
            if(TextUtils.isEmpty(query)) {
                return context.getResources().getStringArray(R.array.section_url)[sectionNumber];
            } else {
                return context.getResources().getStringArray(R.array.section_search_url)[sectionNumber]+query;
            }
        }
    }
}
