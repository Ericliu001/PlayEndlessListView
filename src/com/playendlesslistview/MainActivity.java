package com.playendlesslistview;

import com.playendlesslistview.generic.MainFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;



public class MainActivity extends FragmentActivity{
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		
		
		super.onCreate(arg0);
		setContentView(R.layout.activity_container);
		
		if (getSupportFragmentManager().findFragmentByTag("Good") == null) {
			getSupportFragmentManager().beginTransaction().add(new MainFragment(), "Good").commit();
		}
	}


}
