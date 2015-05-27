package com.playendlesslistview.swipe;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.playendlesslistview.R;
import com.playendlesslistview.tab.TabListFragment;

public abstract class SwipeListFragment extends TabListFragment {
	protected SwipeRefreshLayout swipeContainer;
	protected OnRefreshListener refreshListener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_list, container,
				false);
		swipeContainer = (SwipeRefreshLayout) rootView
				.findViewById(R.id.swipeContainer);

		refreshListener = new OnRefreshListener() {

			@Override
			public void onRefresh() {
				fetchData();
				// swipeContainer.setRefreshing(false);
			}
		};
		swipeContainer.setOnRefreshListener(refreshListener);
		swipeContainer.setColorSchemeResources(android.R.color.holo_green_dark,
				android.R.color.holo_green_light,
				android.R.color.holo_blue_dark,
				android.R.color.holo_blue_bright);
		return rootView;
	}

	public abstract void fetchData();
}
