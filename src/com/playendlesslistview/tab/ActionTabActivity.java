package com.playendlesslistview.tab;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.playendlesslistview.R;

public abstract class ActionTabActivity extends ActionbarBaseActivity implements TabListener {
	private int numPages = 0;
	private ViewPager mPager;
	private ArrayList<TabListFragment> fragmentList;
	
	
	protected abstract ArrayList<TabListFragment> getFragmentList();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action_tab);
		fragmentList = getFragmentList();
		if (fragmentList == null ) {
			return;
		}
		
		
		
		numPages = fragmentList.size();
		
		mPager = (ViewPager) findViewById(R.id.pager);
		if (numPages > 0) {
			mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
		}

		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		for (int i = 0; i < numPages; i++) {
			actionBar.addTab(actionBar.newTab().setText( fragmentList.get(i).getTabTitle())
					.setTabListener(this));

		}
		
		mPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				actionBar.setSelectedNavigationItem(position);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});

	}


	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		mPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	
	private  class MyPagerAdapter extends FragmentPagerAdapter {

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			

			return fragmentList.get(position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return numPages;
		}

	}
}
