package com.titmouse.anton.translator.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
	
	private final List<Fragment> mFragmentList = new ArrayList<>();
	private final List<String> mFragmentTitleList = new ArrayList<>();
	
	public ViewPagerAdapter(final FragmentManager manager) {
		super(manager);
	}
	
	@Override
	public Fragment getItem(final int position) {
		return mFragmentList.get(position);
	}
	
	@Override
	public int getCount() {
		return mFragmentList.size();
	}
	
	public void addFragment(final Fragment fragment, final String title) {
		mFragmentList.add(fragment);
		mFragmentTitleList.add(title);
	}
	
	@Override
	public CharSequence getPageTitle(final int position) {
		return mFragmentTitleList.get(position);
	}
}
