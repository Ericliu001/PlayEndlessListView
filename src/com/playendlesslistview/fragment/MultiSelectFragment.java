package com.playendlesslistview.fragment;

import android.os.Bundle;
import android.widget.ListView;

import com.playendlesslistview.endless.StreamAdapter;
import com.playendlesslistview.endless.StreamLoader;
import com.playendlesslistview.endless.parent.GenericAdapter;
import com.playendlesslistview.endless.parent.GenericLoader;
import com.playendlesslistview.multi_select.MultiSelectBaseListFragment;

public class MultiSelectFragment extends MultiSelectBaseListFragment {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setLoadItemCount(15);
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




	@Override
	public String getTabTitle() {
		// TODO Auto-generated method stub
		return "Multi-Select";
	}


	

}
