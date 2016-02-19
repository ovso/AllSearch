package kr.blogspot.ovsoce.allsearch.main.fragment;

import android.content.Context;
import android.os.Bundle;

/**
 * Created by ovso on 2016. 2. 19..
 */
public interface PlaceholderPresenter{
    void onActivityCreate(Context context, Bundle bundle);
    void onQueryTextSubmit(Context context, Bundle bundle);

    interface View {
        void onInit();
        void replaceUrl(String url);
    }
}
