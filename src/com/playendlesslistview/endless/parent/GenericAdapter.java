package com.playendlesslistview.endless.parent;

import java.util.List;

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.playendlesslistview.R;

public abstract class GenericAdapter<T> extends BaseAdapter {
	
	protected Activity mActivity;
	protected List<T> dataList;
	protected int serverListSize = -1;
	
	public static final int VIEW_TYPE_LOADING = 0;
	public static final int VIEW_TYPE_ACTIVITY = 1;
	
	
	public GenericAdapter(Activity activity, List<T> list) {
		mActivity = activity;
		dataList = list;
		
	}
	
	
	public void setServerListSize(int serverListSize){
		this.serverListSize = serverListSize;
//		Log.d("eric", "serverListSize is: " + this.serverListSize);
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dataList.size() + 1;
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return (position >= dataList.size()) ? VIEW_TYPE_LOADING
				: VIEW_TYPE_ACTIVITY;
	}

	@Override
	public T getItem(int position) {
		// TODO Auto-generated method stub
		return (getItemViewType(position) == VIEW_TYPE_ACTIVITY) ? dataList
				.get(position) : null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return (getItemViewType(position) == VIEW_TYPE_ACTIVITY) ? position
				: -1;
	}

	@Override
	public  View getView(int position, View convertView, ViewGroup parent){
		if (getItemViewType(position) == VIEW_TYPE_LOADING) {
			return getFooterView(position, convertView, parent);
		}
		View dataRow = convertView;
		dataRow = getDataRow(position, convertView, parent);
		
		return dataRow;
	};
	
	
	public abstract View getDataRow(int position, View convertView, ViewGroup parent);

	public View getFooterView(int position, View convertView,
			ViewGroup parent) {
//		Log.d("eric", "multi-selector position: " + position + " ; viewtype: " + getItemViewType(position) + " ; serverListSize " + serverListSize);
		if (position >= serverListSize && serverListSize > 0) {
			
			TextView tvLastRow = new TextView(mActivity);
			tvLastRow.setHint("Reached the last row.");
			tvLastRow.setGravity(Gravity.CENTER);
			return tvLastRow;
		}
		
		View row = convertView;
		if (row == null) {
			row = mActivity.getLayoutInflater().inflate(
					R.layout.progress, parent, false);
		}

		return row;
	}

}
