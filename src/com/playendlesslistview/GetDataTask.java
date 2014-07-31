package com.playendlesslistview;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;

public class GetDataTask extends AsyncTask<Void, Void, List<String>>{
	private WeakReference<GetDataListener> refListener;
	private Context context;
	
	public interface GetDataListener{
		void onQueryComplete(List<String> result);
	}
	
	
	public GetDataTask(Context context, GetDataListener callback) {
		refListener = new WeakReference<GetDataListener>(callback);
		this.context = context;
	}

	@Override
	protected List<String> doInBackground(Void... params) {
		ArrayList<String> dataList = new ArrayList<String>();
		String[] array = context.getResources().getStringArray(R.array.navi_drawer_list);
		for (int i = 0; i < array.length; i++) {
			dataList.add(array[i]);
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataList;
	}
	
	@Override
	protected void onPostExecute(List<String> result) {
		refListener.get().onQueryComplete(result);
	
	}
	
}