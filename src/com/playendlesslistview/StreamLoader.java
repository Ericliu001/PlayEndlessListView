package com.playendlesslistview;

import java.lang.ref.WeakReference;
import java.util.List;

import com.playendlesslistview.GetDataTask.GetDataListener;
import com.playendlesslistview.generic.GenericLoader;

import android.content.Context;
import android.content.Loader;
import android.os.AsyncTask;
import android.text.GetChars;
import android.util.Log;


public class StreamLoader extends GenericLoader<List<String>> implements GetDataListener{

	public StreamLoader(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void loadMore(int skip, int top) {
		// TODO Auto-generated method stub
		new GetDataTask(getContext(), this).execute(skip, top);
		Log.d("eric", "skip: " + skip + " ; top: " + top );
	}
	
}
