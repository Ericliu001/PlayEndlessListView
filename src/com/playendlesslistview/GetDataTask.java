package com.playendlesslistview;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;

public class GetDataTask extends AsyncTask<Integer, Void, List<String>>{
	public static final int MAX_REQUEST_RESULT_NUMBER = 15;
	
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
	protected List<String> doInBackground(Integer... params) {
		int page = params[0];
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<String> dataList = new ArrayList<String>();
		String[] array = context.getResources().getStringArray(R.array.navi_drawer_list);
		for (int i = page; i < page + MAX_REQUEST_RESULT_NUMBER; i++) {
			dataList.add(array[i]);
		}
		
		
		return dataList;
	}
	
	@Override
	protected void onPostExecute(List<String> result) {
		refListener.get().onQueryComplete(result);
	
	}
	
}