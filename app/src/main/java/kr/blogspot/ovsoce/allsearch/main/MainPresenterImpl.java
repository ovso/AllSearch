package kr.blogspot.ovsoce.allsearch.main;

import android.content.Context;

import kr.blogspot.ovsoce.allsearch.R;
import kr.blogspot.ovsoce.allsearch.app.MyApplication;

public class MainPresenterImpl implements MainPresenter {
    private MainPresenter.View mView;
    private MainModel mModel;

    MainPresenterImpl(MainPresenter.View view) {
        mView = view;
        mModel = new MainModel();
    }

    @Override
    public void onCreate(Context context) {
        mView.showSplash();
        mView.onInit();
        mView.setViewPager();
        mView.setToolbarTitle(mModel.getToolbarTitle(context));
        boolean searchMode = mModel.isSearchMode(context);
        //mView.setToolbarTitle(mModel.getToolbarTitle(view.getContext()));
        if(searchMode) {
            mView.showToolbar();
        } else {
            mView.hideToolbar();
        }

    }

    @Override
    public void onQueryTextChange(Context context, String query) {
        mModel.onQueryTextChange(context, query);
    }

    @Override
    public void onClick(android.view.View view) {
        if(view.getId() == R.id.fab) {
            mModel.setSearchMode(view.getContext());
            boolean searchMode = mModel.isSearchMode(view.getContext());
            //mView.setToolbarTitle(mModel.getToolbarTitle(view.getContext()));
            if(searchMode) {
                mView.showToolbar();
                mView.showToast(view.getContext().getString(R.string.text_search_sync));
            } else {
                mView.hideToolbar();
                mView.showToast(view.getContext().getString(R.string.text_search_sync));
            }
        } else if(view.getId() == R.id.fab_lock) {
            mModel.setSwipe(view.getContext());
            boolean isSwipe = mModel.isSwipe(view.getContext());
            if(isSwipe) {
                mView.showSwipeUnlock(isSwipe);
                mView.showToast(view.getContext().getString(R.string.text_swipe_unlock));

            } else {
                mView.showSwipeLock(isSwipe);
                mView.showToast(view.getContext().getString(R.string.text_swipe_lock));
            }
        }
    }

    @Override
    public void onQueryTextSubmit(Context context) {
        boolean searchMode = mModel.isSearchMode(context);
        if(searchMode) {
            mView.doSyncSearch();
        } else {
            mView.doMultipleSearch();
        }
    }

    @Override
    public void clearQuery(Context context) {
        mModel.clearQuery(context);
    }

    @Override
    public void onDestory(Context context) {
        ((MyApplication)context.getApplicationContext()).remove();
    }
}
