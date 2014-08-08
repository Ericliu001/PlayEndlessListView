package com.playendlesslistview.tab;

import android.R;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public abstract class ActionbarBaseActivity extends FragmentActivity {
	
	
	public abstract String getActionbarTitle();
	public abstract String getActionbarSubTitle();
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		
		getActionBar().setTitle(getActionbarTitle());
		getActionBar().setSubtitle(getActionbarSubTitle());
		
	}
	
	
	
	
	
	
	
	
	
}
