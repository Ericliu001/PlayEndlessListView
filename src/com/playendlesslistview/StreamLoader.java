package com.playendlesslistview;

import java.lang.ref.WeakReference;
import java.util.List;

import com.playendlesslistview.GetDataTask.GetDataListener;

import android.content.Context;
import android.content.Loader;
import android.os.AsyncTask;
import android.text.GetChars;


public class StreamLoader extends Loader<List<String>> implements GetDataListener{

	public StreamLoader(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onStartLoading() {
		// TODO Auto-generated method stub
		super.onStartLoading();
		
		new GetDataTask( getContext() ,this).execute();
		
	}
	
	@Override
	public void onQueryComplete(List<String> result) {
		deliverResult(result);
	}

	@Override
	public void deliverResult(List<String> data) {
		// TODO Auto-generated method stub
		super.deliverResult(data);
	}

	@Override
	protected void onStopLoading() {
		// TODO Auto-generated method stub
		super.onStopLoading();
	}
	
	@Override
	protected void onForceLoad() {
		// TODO Auto-generated method stub
		super.onForceLoad();
	}
	
	@Override
	protected void onReset() {
		// TODO Auto-generated method stub
		super.onReset();
	}
	
	
}
