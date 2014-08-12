package com.playendlesslistview.endless;

import java.util.List;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.playendlesslistview.R;
import com.playendlesslistview.R.id;
import com.playendlesslistview.R.layout;
import com.playendlesslistview.endless.parent.GenericAdapter;

public class StreamAdapter extends GenericAdapter<String> {
	

	public StreamAdapter(Activity activity, List<String> list) {
		super(activity, list);
		// TODO Auto-generated constructor stub
	}
	
	
	private static class ViewHolder{
		private TextView tvRow;
		public ViewHolder(View row) {
			tvRow = (TextView) row.findViewById(R.id.tvRow);
		}
	}
	
	@Override
	public boolean isEnabled(int position) {
		 // disable click events on the progress bar item. 
		return getItemViewType(position) == VIEW_TYPE_ACTIVITY;
	}

	@Override
	public View getDataRow(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			row = mActivity.getLayoutInflater().inflate(R.layout.endless_list_row, null, false);
		}
		
		ViewHolder holder = null;
		holder = (ViewHolder) row.getTag();
		if (holder == null) {
			holder = new ViewHolder(row);
			row.setTag(holder);
		}
		
		holder.tvRow.setText(dataList.get(position));
		
		return row;
	}

}
