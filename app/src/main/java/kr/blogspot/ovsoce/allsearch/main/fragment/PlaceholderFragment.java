package kr.blogspot.ovsoce.allsearch.main.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

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
    //private ProgressBar mProgressBar;
    private ImageView mLoadingImg;
    @Override
    public void onInit() {
        //mProgressBar = (ProgressBar) getView().findViewById(R.id.webview_progressbar);
        mLoadingImg = (ImageView) getView().findViewById(R.id.img_loading);
        mWebview = (WebView) getView().findViewById(R.id.webview);
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.setWebChromeClient(new WebChromeClientCustom());
        mWebview.setWebViewClient(new WebViewClientCustom());
    }

    @Override
    public void replaceUrl(String url) {
        Log.d("url = " + url);
        if(getView() != null) {
            mWebview.loadUrl(url);
        }
    }

    private class WebChromeClientCustom extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            //mProgressBar.setProgress(newProgress);
            super.onProgressChanged(view, newProgress);

        }
    }
    private class WebViewClientCustom extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //mProgressBar.setVisibility(View.VISIBLE);
            mLoadingImg.setVisibility(View.VISIBLE);
            ((AnimationDrawable)mLoadingImg.getBackground()).start();
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            //mProgressBar.setVisibility(View.GONE);
            //mProgressBar.setProgress(0);
            mLoadingImg.setVisibility(View.GONE);
            ((AnimationDrawable)mLoadingImg.getBackground()).stop();

            super.onPageFinished(view, url);
        }
    }
}
