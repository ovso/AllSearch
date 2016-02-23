package kr.blogspot.ovsoce.allsearch.main;

import android.content.Context;

/**
 * Created by ovso on 2016. 2. 19..
 */
public interface MainPresenter {
    void onCreate(Context context);
    void onQueryTextChange(Context context, String query);
    void onClick(android.view.View view);
    void onQueryTextSubmit(Context context);
    void clearQuery(Context context);
    void onDestory(Context context);
    interface View {
        void onInit();
        void setViewPager();
        void setToolbarTitle(String title);
        void doMultipleSearch();
        void doSyncSearch();
        void hideToolbar();
        void showToolbar();
        void showSnackbar(String msg);
        void showToast(String msg);
        void showSplash();
    }
}
