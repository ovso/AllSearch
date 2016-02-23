package kr.blogspot.ovsoce.allsearch.splash;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;

import kr.blogspot.ovsoce.allsearch.R;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView loading = (ImageView) findViewById(R.id.img_loading);
        final AnimationDrawable drawable = (AnimationDrawable) loading.getDrawable();
        drawable.start();

        loading.postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        drawable.stop();
                        finish();
                    }
                });
            }
        }, 3500);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
