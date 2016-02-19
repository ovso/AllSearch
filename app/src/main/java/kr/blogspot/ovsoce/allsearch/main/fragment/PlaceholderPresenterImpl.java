package kr.blogspot.ovsoce.allsearch.main.fragment;

import android.content.Context;
import android.os.Bundle;

/**
 * Created by ovso on 2016. 2. 19..
 */
public class PlaceholderPresenterImpl implements PlaceholderPresenter {
    PlaceholderPresenter.View mView;
    PlaceholderModel mModel;
    PlaceholderPresenterImpl(PlaceholderPresenter.View view) {
        mView = view;
        mModel = new PlaceholderModel();
    }
    @Override
    public void onActivityCreate(Context context, Bundle bundle) {
        mView.onInit();
        mView.replaceUrl(mModel.getUrl(context, bundle.getInt(PlaceholderFragment.ARG_SECTION_NUMBER)));

    }

    @Override
    public void onQueryTextSubmit(Context context, Bundle bundle) {
        mView.replaceUrl(mModel.getUrl(context, bundle.getInt(PlaceholderFragment.ARG_SECTION_NUMBER)));
    }

}
