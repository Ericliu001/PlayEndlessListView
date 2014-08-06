package com.playendlesslistview.generic;

import java.util.List;

import com.playendlesslistview.R;
import com.playendlesslistview.GetDataTask.GetDataListener;

import android.content.Context;
import android.support.v4.content.Loader;

public abstract class GenericLoader<T> extends Loader<List<T>>  implements GetDataListener {
	public static final int LOAD_ITEM_NUMER = 20;
	protected int serverListSize = -1;
	
	public GenericLoader(Context context) {
		super(context);
		
		String[] array = context.getResources().getStringArray(
				R.array.navi_drawer_list);
		serverListSize = array.length;
		// TODO Auto-generated constructor stub
	}
	
	
	public abstract void loadMore(int skip, int top);
	
	
	@Override
	public void onQueryComplete(List<String> result) {
		
		List<T> list  = (List<T>) result;
		deliverResult(list);
	}
	
	public int getServerListSize(){
		
		return serverListSize;
	}
	

	
	

	@Override
	public void deliverResult(List<T> data) {
		// TODO Auto-generated method stub
		super.deliverResult(data);
	}
}
