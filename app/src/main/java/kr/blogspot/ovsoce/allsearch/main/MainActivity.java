package kr.blogspot.ovsoce.allsearch.main;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import kr.blogspot.ovsoce.allsearch.R;
import kr.blogspot.ovsoce.allsearch.common.Log;
import kr.blogspot.ovsoce.allsearch.main.fragment.PlaceholderFragment;

public class MainActivity extends AppCompatActivity implements MainPresenter.View, View.OnClickListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private MainPresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainPresenterImpl(this);
        mPresenter.onCreate(getApplicationContext());

    }

    private MaterialSearchView mSearchView;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        mSearchView.setMenuItem(item);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search_view) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onInit() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSearchView = (MaterialSearchView) findViewById(R.id.search_view);

        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                mPresenter.onQueryTextSubmit(getApplicationContext());

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mSearchView.getWindowToken(), 0);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                //Log.d("newText = " + newText);
                mPresenter.onQueryTextChange(getApplicationContext(), newText);
                return false;
            }
        });

        mSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
                //Log.d("shown");
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
                //Log.d("closed");
                //mPresenter.clearQuery(getApplicationContext());
            }
        });

        findViewById(R.id.fab).setOnClickListener(this);

    }

    @Override
    public void setViewPager() {
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), getApplicationContext());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d("position = " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    }

    @Override
    public void setToolbarTitle(String title) {
        getSupportActionBar().setTitle(title);
        Log.d("toolbar = " + title);
    }

    @Override
    public void doMultipleSearch() {
        PlaceholderFragment fragment = (PlaceholderFragment) mSectionsPagerAdapter.getRegisteredFragment(mViewPager.getCurrentItem());
        fragment.getPresenter().onQueryTextSubmit(getApplicationContext(), fragment.getArguments());
    }

    @Override
    public void doSyncSearch() {
        int size = mSectionsPagerAdapter.getRegisteredFragmentList().size();

        for(int i=0; i<size; i++) {
            PlaceholderFragment fragment = (PlaceholderFragment) mSectionsPagerAdapter.getRegisteredFragment(i);
            fragment.getPresenter().onQueryTextSubmit(getApplicationContext(), fragment.getArguments());
        }
    }

    @Override
    public void hideToolbar() {
        findViewById(R.id.toolbar_container).setVisibility(View.GONE);
    }

    @Override
    public void showToolbar() {
        findViewById(R.id.toolbar_container).setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        if (mSearchView.isSearchOpen()) {
            mSearchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        mPresenter.onClick(v);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestory(getApplicationContext());
    }
}
