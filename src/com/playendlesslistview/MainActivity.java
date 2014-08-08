package com.playendlesslistview;


import java.util.ArrayList;

import android.os.Bundle;

import com.playendlesslistview.fragment.MainFragment;
import com.playendlesslistview.fragment.MultiSelectFragment;
import com.playendlesslistview.tab.ActionTabActivity;
import com.playendlesslistview.tab.TabListFragment;



public class MainActivity extends ActionTabActivity{
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		
		
		super.onCreate(arg0);
//		setContentView(R.layout.activity_container);
//		
//		if (getSupportFragmentManager().findFragmentByTag("Good") == null) {
//			getSupportFragmentManager().beginTransaction().add(R.id.container, new MainFragment(), "Good").commit();
//		}
	}

	@Override
	protected ArrayList<TabListFragment> getFragmentList() {
		ArrayList<TabListFragment> list = new ArrayList<TabListFragment>();
		list.add(new MainFragment());
		list.add(new MultiSelectFragment());
		return list;
	}

	@Override
	public String getActionbarTitle() {
		// TODO Auto-generated method stub
		return "Pretty Cool";
	}

	@Override
	public String getActionbarSubTitle() {
		// TODO Auto-generated method stub
		return null;
	}


}
