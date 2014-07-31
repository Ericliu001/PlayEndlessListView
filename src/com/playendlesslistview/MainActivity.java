package com.playendlesslistview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity implements LoaderCallbacks<List<String>>{

	private static final int LOAD_DATA = 0;
	private TextView tvState;
	private ListView lvData;
	private List<String> dataList = new ArrayList<String>();
	private ArrayAdapter<String> adapter ;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvState = (TextView) findViewById(R.id.tvState);
        lvData = (ListView) findViewById(R.id.lvData);
        
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList);
        lvData.setAdapter(adapter);
        
        getLoaderManager().initLoader(LOAD_DATA, null, this);
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
		adapter.addAll(data);
		adapter.notifyDataSetChanged();
		
	}


	@Override
	public void onLoaderReset(Loader<List<String>> loader) {
		// TODO Auto-generated method stub
		
	}
}
