package kr.blogspot.ovsoce.allsearch.main;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by ovso on 2016. 2. 23..
 */
public class ViewPagerCustom extends ViewPager {
    public ViewPagerCustom(Context context) {
        super(context);
    }

    public ViewPagerCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return this.isPagingEnabled && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.isPagingEnabled && super.onInterceptTouchEvent(ev);
    }
    private boolean isPagingEnabled = true;
    public void setPagingEnabled(boolean b) {
        this.isPagingEnabled = b;
    }
}
