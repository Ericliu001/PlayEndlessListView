package com.playendlesslistview;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;



public class MainActivity extends FragmentActivity{
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		
		
		super.onCreate(arg0);
		setContentView(R.layout.activity_container);
		
		if (getSupportFragmentManager().findFragmentByTag("Good") == null) {
			getSupportFragmentManager().beginTransaction().add(R.id.container, new MainFragment(), "Good").commit();
		}
	}


}
