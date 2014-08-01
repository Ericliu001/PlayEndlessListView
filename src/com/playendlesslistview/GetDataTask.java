package com.playendlesslistview;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;

public class GetDataTask extends AsyncTask<Integer, Void, List<String>> {
	public static final int MAX_REQUEST_RESULT_NUMBER = 10;

	private WeakReference<GetDataListener> refListener;
	private Context context;

	public interface GetDataListener {
		void onQueryComplete(List<String> result);
	}

	public GetDataTask(Context context, GetDataListener callback) {
		refListener = new WeakReference<GetDataListener>(callback);
		this.context = context;
	}

	@Override
	protected List<String> doInBackground(Integer... params) {
		
		
		int startPosition = params[0];
		int endPosition = params[1];
		
		if (startPosition < 0 || startPosition > endPosition) {
			try {
				throw new Exception("The indices you provided are all wrong: " + startPosition + "  " + endPosition);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<String> dataList = new ArrayList<String>();
		String[] array = context.getResources().getStringArray(
				R.array.navi_drawer_list);
		for (int i = 0; i < endPosition - startPosition + 1; i++) {
			int j = i + startPosition;
			if (j < array.length) {

				dataList.add(array[j]);
			}
		}

		return dataList;
	}

	@Override
	protected void onPostExecute(List<String> result) {
		refListener.get().onQueryComplete(result);

	}

}