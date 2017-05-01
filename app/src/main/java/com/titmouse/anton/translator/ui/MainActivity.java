package com.titmouse.anton.translator.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.titmouse.anton.translator.R;
import com.titmouse.anton.translator.adapters.ViewPagerAdapter;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private static final int MAX_ALIVE_FRAGMENTS = 3;
    private static final int TRANSLATE_PAGE = 1;
    private static final int HISTORY_PAGE = 2;
    private  ViewPagerAdapter viewPagerAdapter;
    private FragmentLifecycle fragmentToShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(getApplicationContext());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(MAX_ALIVE_FRAGMENTS);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new FavoriteFragment(), getString(R.string.favorite_name));
        viewPagerAdapter.addFragment(new TranslateFragment(), getString(R.string.translate_name));
        viewPagerAdapter.addFragment(new HistoryFragment(), getString(R.string.history_name));

        viewPager.setAdapter(viewPagerAdapter);

        viewPager.setCurrentItem(TRANSLATE_PAGE);
        viewPager.onSaveInstanceState();
        viewPager.addOnPageChangeListener(this);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("main", "onPageSelected "+String.valueOf(position));

        if (position!=TRANSLATE_PAGE) {
            fragmentToShow = (FragmentLifecycle) viewPagerAdapter.getItem(position);
            fragmentToShow.onOpenFragment();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
