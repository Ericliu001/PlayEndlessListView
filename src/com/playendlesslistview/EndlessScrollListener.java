package com.playendlesslistview;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

public abstract class EndlessScrollListener implements OnScrollListener {
	
	private int currentPage = 0;
	private int previousTotalItemCount = 0;
	private int startingPageIndex = 0;
	
	private boolean isLoading = true;

	
	public EndlessScrollListener() {
		// TODO Auto-generated constructor stub
	}
	
	public EndlessScrollListener(int startPage){
		this.startingPageIndex = startPage;
		this.currentPage = startPage;
	}
	
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		
		if (totalItemCount < previousTotalItemCount) {
			this.currentPage = this.startingPageIndex;
			this.previousTotalItemCount = totalItemCount;
			if (totalItemCount == 0) {
				this.isLoading = true;
			}
		}
		
		if (isLoading && (totalItemCount > previousTotalItemCount)) {
			isLoading = false;
			previousTotalItemCount = totalItemCount;
			currentPage ++ ;
		}
		
		if (!isLoading  && ((firstVisibleItem + 1 + visibleItemCount) >= totalItemCount )) {
			loadMoreResults(currentPage +1, totalItemCount);
		}
		
	}

	public abstract void loadMoreResults(int page, int totalItemCount);

}
