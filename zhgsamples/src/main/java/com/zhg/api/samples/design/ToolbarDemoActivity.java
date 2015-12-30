package com.zhg.api.samples.design;

import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;

import com.zhg.api.samples.R;

import java.util.List;

public class ToolbarDemoActivity extends AppCompatActivity {

    private ActionBarDrawerToggle mActionBarDrawerToggle;

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ContentFragment mCurrentFragment;
    private LeftMenuFragment mLeftMenuFragment;
    private String mTitle;
    private static final String KEY_TITLE="key_title";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_demo);
        init();

//        onRestoreInstanceState(savedInstanceState);
        FragmentManager fm=getSupportFragmentManager();
        mCurrentFragment= (ContentFragment) fm.findFragmentByTag(mTitle);
        if(mCurrentFragment==null){
            mCurrentFragment=ContentFragment.getInstance(mTitle);
            fm.beginTransaction().add(R.id.id_content_container,mCurrentFragment,mTitle).commit();
        }
        mLeftMenuFragment= (LeftMenuFragment) fm.findFragmentById(R.id.id_left_menu_container);
        if(mLeftMenuFragment==null){
            mLeftMenuFragment=new LeftMenuFragment();
//            mLeftMenuFragment.setMenuItemSelected(mLeftMenuFragment.getListView().getChildAt(0),0);
            fm.beginTransaction().add(R.id.id_left_menu_container,mLeftMenuFragment).commit();
        }
        List<Fragment> fragments=fm.getFragments();
        if(fragments!=null) {
            for (Fragment f : fragments) {
                if (f == mCurrentFragment || f == mLeftMenuFragment) continue;
                fm.beginTransaction().hide(f).commit();
            }
        }
        mLeftMenuFragment.setOnMenuItemSelectedListener(new LeftMenuFragment.OnMenuItemSelectedListener() {
            @Override
            public void onMenuItemSelect(String title) {
                FragmentManager fm = getSupportFragmentManager();
                ContentFragment contentFragment = (ContentFragment) fm.findFragmentByTag(title);
                if (mCurrentFragment == contentFragment) {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                    return;
                }
                FragmentTransaction ft = fm.beginTransaction();
                ft.hide(mCurrentFragment);
                if (contentFragment == null) {
                    contentFragment = ContentFragment.getInstance(title);
                    ft.add(R.id.id_content_container, contentFragment, title);
                } else {
                    ft.show(contentFragment);
                }
                ft.commit();
                mCurrentFragment = contentFragment;
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                mTitle = title;
                mToolbar.setTitle(title);
            }
        });

    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_TITLE, mTitle);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mTitle=savedInstanceState.getString(KEY_TITLE);
        if(TextUtils.isEmpty(mTitle)){
            mTitle=getResources().getStringArray(R.array.menu_array)[0];
        }else {
            mToolbar.setTitle(mTitle);
        }
    }

    private void init() {
        mToolbar= (Toolbar) findViewById(R.id.id_toolBar);
        mToolbar.setTitle(mTitle=getResources().getStringArray(R.array.menu_array)[0]);
        mToolbar.setNavigationIcon(R.mipmap.ic_menu);
        setSupportActionBar(mToolbar);

        mDrawerLayout= (DrawerLayout) findViewById(R.id.id_drawerLayout);
        mActionBarDrawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.open,R.string.close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
    }
}
