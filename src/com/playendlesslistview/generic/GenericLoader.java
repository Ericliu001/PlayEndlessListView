package com.playendlesslistview.generic;

import java.util.List;

import android.content.Context;
import android.support.v4.content.Loader;

import com.playendlesslistview.GetDataTask.GetDataListener;

public abstract class GenericLoader<T> extends Loader<List<T>>  implements GetDataListener {
	protected int serverListSize = -1;
	
	public GenericLoader(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	
	public abstract void loadMore(int skip, int top);
	
	
	@Override
	public void onQueryComplete(List<String> result) {
		List<T> list = (List<T>) result;
		deliverResult(list);
	}
	
	public int getServerListSize(){
		return serverListSize;
	}
	

	
	
//	public abstract List<T> deSerialize(String result);

	@Override
	public void deliverResult(List<T> data) {
		// TODO Auto-generated method stub
		super.deliverResult(data);
	}
}
