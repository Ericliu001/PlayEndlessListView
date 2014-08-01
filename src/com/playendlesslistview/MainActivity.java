package com.playendlesslistview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends Activity implements LoaderCallbacks<List<String>>{

	private static final int LOAD_DATA = 0;
	private TextView tvState;
	private ListView lvData;
	private List<String> dataList = new ArrayList<String>();
	private StreamAdapter adapter ;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvState = (TextView) findViewById(R.id.tvState);
        lvData = (ListView) findViewById(R.id.lvData);
        
        adapter = new StreamAdapter();
        lvData.setAdapter(adapter);
        
        getLoaderManager().initLoader(LOAD_DATA, null, this);
        lvData.setOnScrollListener(new EndlessScrollListener() {
			
			@Override
			public void loadMoreResults(int page, int totalItemCount) {
				// TODO Auto-generated method stub
				loadMoreDataFromApi(totalItemCount - 1, page*GetDataTask.MAX_REQUEST_RESULT_NUMBER -1);
			}
		});
        
    }


    protected void loadMoreDataFromApi(int startPosition, int endPosition) {
    	Loader loader  = getLoaderManager().getLoader(LOAD_DATA);
    	tvState.setText("Creating");
    	((StreamLoader)loader).loadMore(startPosition,  endPosition);
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


	@Override
	public Loader<List<String>> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		tvState.setText("Creating");
		
		return new StreamLoader(this);
	}


	@Override
	public void onLoadFinished(Loader<List<String>> loader, List<String> data) {
		// TODO Auto-generated method stub
		tvState.setText("Load finished");
		dataList.addAll(data);
		adapter.notifyDataSetChanged();
		
	}


	@Override
	public void onLoaderReset(Loader<List<String>> loader) {
		// TODO Auto-generated method stub
		
	}
	
	
	private static class ViewHolder{
		 private ImageView ivRow;
		 private TextView tvRow;
		
		 public ViewHolder(View row) {
			 ivRow = (ImageView) row.findViewById(R.id.ivRow);
			 tvRow = (TextView) row.findViewById(R.id.tvRow);
		 }
		
		
	}
	
	
	private class StreamAdapter extends BaseAdapter{


		private static final int VIEW_TYPE_LOADING = 0;
		private static final int VIEW_TYPE_ACTIVITY = 1;

		
		
		
		
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
			return (position >= dataList.size())? 
					VIEW_TYPE_LOADING :
						VIEW_TYPE_ACTIVITY
					;
		}
		
		
		@Override
		public String getItem(int position) {
			// TODO Auto-generated method stub
			return (getItemViewType(position) == VIEW_TYPE_ACTIVITY)
					? dataList.get(position)
					: null;
		}
		
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return (getItemViewType(position) == VIEW_TYPE_ACTIVITY)
                    ? position
                    : -1;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (getItemViewType(position) == VIEW_TYPE_LOADING) {
				return getFooterView(position, convertView, parent);
			}
			
			
			View row = convertView;
			if (row == null) {
				row = getLayoutInflater().inflate(R.layout.endless_list_row	, parent, false);
				
			}
			ViewHolder holder = (ViewHolder) row.getTag();
			if (holder == null) {
				holder = new ViewHolder(row);
				row.setTag(holder);
			}
			
			holder.ivRow.setImageResource(R.drawable.ic_launcher);
			holder.tvRow.setText(getItem(position));
			
			return row;
		}

		private View getFooterView(int position, View convertView,
				ViewGroup parent) {
			View row = convertView;
			if (row == null) {
				row = getLayoutInflater().inflate(R.layout.progress	, parent, false);
			}
			
			
			
			return row;
		}
		
	}
}
