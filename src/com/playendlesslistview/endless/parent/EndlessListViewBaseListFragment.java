package com.playendlesslistview.endless.parent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.playendlesslistview.multi_select.MultiSelectBaseListFragment;
import com.playendlesslistview.tab.TabListFragment;

public abstract class EndlessListViewBaseListFragment<T> extends MultiSelectBaseListFragment implements 
		LoaderCallbacks<List<T>> {
	protected static final int LOAD_DATA = 0;
	protected List<T> dataList = new ArrayList<T>();
	protected ListView lvData;
	protected GenericAdapter<T> adapter;
	
	private int loadItemCount = 20;
	private OnScrollListener scrollListener;

	public abstract ListView getDataListView();
	public abstract GenericAdapter<T> getDataListAdapter();
	public abstract GenericLoader<T> getGenericLoader();
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		scrollListener = new EndlessScrollListener() {
			
			@Override
			public void loadMoreResults(int page, int totalItemCount) {
				// TODO Auto-generated method stub
				loadMoreResultsFromApi(page, totalItemCount);
				Log.d("eric", "page: " + page + " ; totalItemCount: " + totalItemCount );
			}
		};
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = super.onCreateView(inflater, container, savedInstanceState);
		
		return rootView;
	}
	
	
	

	@Override
	public void onActivityCreated( Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		lvData = getDataListView();
		adapter =  getDataListAdapter();
		
		
		
//		lvData.setAdapter(adapter); no use when you are using ListFragment
		
		setListAdapter(adapter);
		
		getLoaderManager().initLoader(LOAD_DATA, null, this);
		lvData.setOnScrollListener(scrollListener);
		
	}
	
	protected void setLoadItemCount(int count){
		this.loadItemCount = count;
	}

	protected void loadMoreResultsFromApi(int page, int totalItemCount) {
			if (isAdded()) {
				Loader loader = getLoaderManager().getLoader(LOAD_DATA);
				if (loader != null) {
					((GenericLoader) loader).loadMore((page -1)*loadItemCount,
							loadItemCount);
				}
			}
	}
	
	
	@Override
	public Loader<List<T>> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		return getGenericLoader();
	}

	@Override
	public void onLoadFinished(Loader<List<T>> loader,
			List<T> resultList) {
		// TODO Auto-generated method stub
		if (resultList != null) {
			dataList.addAll((Collection<? extends T>) resultList);
			adapter.setServerListSize(((GenericLoader)loader).getServerListSize());
			adapter.notifyDataSetChanged();
			
		}
		
	}

	@Override
	public void onLoaderReset(Loader<List<T>> arg0) {
	}

 @Override
public void onDestroyView() {
//	 dataList.clear();
	 super.onDestroyView();
}
	
}
