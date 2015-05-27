package com.playendlesslistview.fragment;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.playendlesslistview.R;
import com.playendlesslistview.endless.StreamAdapter;
import com.playendlesslistview.endless.StreamLoader;
import com.playendlesslistview.endless.parent.EndlessListViewBaseListFragment;
import com.playendlesslistview.endless.parent.GenericAdapter;
import com.playendlesslistview.endless.parent.GenericLoader;

public class MultiSelectFragment extends EndlessListViewBaseListFragment {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setLoadItemCount(15);
		setHasOptionsMenu(true);
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
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.message, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_compose_message:
			setUpAction();
			break;

		default:
			break;
		}
		return true;
	}


	@Override
	public String getTabTitle() {
		// TODO Auto-generated method stub
		return "Multi-Select";
	}


	

}
