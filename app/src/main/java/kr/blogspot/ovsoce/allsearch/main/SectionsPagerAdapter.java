package kr.blogspot.ovsoce.allsearch.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import kr.blogspot.ovsoce.allsearch.R;
import kr.blogspot.ovsoce.allsearch.main.fragment.PlaceholderFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    SparseArray<Fragment> fragmentSparseArray = new SparseArray<>();
    private Context mContext;
    public SectionsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return mContext.getResources().getStringArray(R.array.section_url).length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getStringArray(R.array.section_name)[position];
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fragmentSparseArray.put(position, fragment);
        //return super.instantiateItem(container, position);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //fragmentSparseArray.remove(position);
        //super.destroyItem(container, position, object);
    }
    public Fragment getRegisteredFragment(int position) {
        return fragmentSparseArray.get(position);
    }
    public SparseArray<Fragment> getRegisteredFragmentList() {
        return fragmentSparseArray;
    }
}
