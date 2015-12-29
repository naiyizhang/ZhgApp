package com.zhg.api.samples.design;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhg.api.samples.R;

public class TabLayoutActivity extends AppCompatActivity implements ItemFragment.OnFragmentInteractionListener {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private String[]mTitles=new String[]{"title1","title2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        mTabLayout= (TabLayout) findViewById(R.id.id_tabLayout);
        mViewPager= (ViewPager) findViewById(R.id.id_viewPager);
        FragmentPagerAdapter adapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                ItemFragment fragment=ItemFragment.newInstance(mTitles[position]);
                return fragment;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }
        };

        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
