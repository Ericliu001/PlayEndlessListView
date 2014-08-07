package com.playendlesslistview;

import com.playendlesslistview.generic.EndlessListViewBaseListFragment;
import com.playendlesslistview.generic.GenericAdapter;
import com.playendlesslistview.generic.GenericLoader;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.widget.ListView;


public class MainFragment extends EndlessListViewBaseListFragment {

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setLoadItemCount(25);
	}

	
	@Override
	public ListView getDataListView() {
		// TODO Auto-generated method stub
		return getListView();
	}

	@Override
	public GenericAdapter getDataListAdapter() {
		// TODO Auto-generated method stub
		return new StreamAdapter(getActivity(), dataList);
	}

	@Override
	public GenericLoader getGenericLoader() {
		// TODO Auto-generated method stub
		return new StreamLoader(getActivity());
	}
	
	
	

}
