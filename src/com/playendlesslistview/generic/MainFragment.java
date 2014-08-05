package com.playendlesslistview.generic;

import com.playendlesslistview.StreamAdapter;
import com.playendlesslistview.StreamLoader;

import android.support.v4.content.Loader;
import android.widget.ListView;


public class MainFragment extends EndlessListViewBaseListFragment {


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
