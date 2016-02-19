package kr.blogspot.ovsoce.allsearch.main.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import kr.blogspot.ovsoce.allsearch.R;
import kr.blogspot.ovsoce.allsearch.common.Log;

public class PlaceholderFragment extends Fragment implements PlaceholderPresenter.View {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";

    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
    private PlaceholderPresenter mPresenter;
    public PlaceholderPresenter getPresenter() {
        return mPresenter;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new PlaceholderPresenterImpl(this);
        mPresenter.onActivityCreate(getActivity(), getArguments());
    }

    private WebView mWebview;
    @Override
    public void onInit() {
        mWebview = (WebView) getView().findViewById(R.id.webview);
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.setWebChromeClient(new WebChromeClient());
        mWebview.setWebViewClient(new WebViewClient());
    }

    @Override
    public void replaceUrl(String url) {
        Log.d("url = " + url);
        if(getView() != null) {
            mWebview.loadUrl(url);
        }
    }

}
