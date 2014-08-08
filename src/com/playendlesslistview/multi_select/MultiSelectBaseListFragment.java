package com.playendlesslistview.multi_select;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.playendlesslistview.R;
import com.playendlesslistview.endless.parent.EndlessListViewBaseListFragment;

public abstract class MultiSelectBaseListFragment<T> extends
		EndlessListViewBaseListFragment<T> {

	private static final String ACTION_MODE_ON = "action_mode_on";
	private static final String STATE_CHOICE_MODE = "state choice mode";
	private ListView mLv;
	private ActionModeHelper actionHelper;
	private boolean isActionModeOn = false;
	private int choiceMode = ListView.CHOICE_MODE_NONE;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		setHasOptionsMenu(true);
		actionHelper = new ActionModeHelper();

	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		mLv = getListView();
		mLv.setChoiceMode(choiceMode);
		// mLv.setOnItemSelectedListener(actionHelper);
		 mLv.setOnItemClickListener(actionHelper);

		if ((savedInstanceState != null && savedInstanceState
				.getBoolean(ACTION_MODE_ON)) || isActionModeOn) {
			getActivity().startActionMode(actionHelper);
		}

		if ((savedInstanceState != null)) {
			mLv.setChoiceMode(savedInstanceState.getInt(STATE_CHOICE_MODE));
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {

		super.onSaveInstanceState(outState);
		outState.putBoolean(ACTION_MODE_ON, isActionModeOn);
		outState.putInt(STATE_CHOICE_MODE, mLv.getChoiceMode());
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

	private void setUpAction() {

		
		getActivity().startActionMode(actionHelper);
	}

	private class ActionModeHelper implements ActionMode.Callback, OnItemClickListener {
		private ActionMode actionMode;
		private int checkedNumber = 0;
		SparseBooleanArray checked = null;
		
		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {

			choiceMode = ListView.CHOICE_MODE_MULTIPLE;
			mLv.setChoiceMode(choiceMode);
			
			actionMode = mode;
			
			getActivity().getMenuInflater().inflate(R.menu.send, menu);
			mode.setTitle("Send Group Message");
			mode.setSubtitle("(" + checkedNumber +  ")");
			isActionModeOn = true;
			return true;
		}

		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

			return false;
		}

		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			String s = "";
			int j = 0;
			for (int i = 0; i < mLv.getAdapter().getCount(); i++) {
				if (checked.get(i)) {
					s = s + " " + i + "; ";
					j++;
				}
			}

			switch (item.getItemId()) {
			case R.id.action_send:
				Toast.makeText(getActivity(), "Checked: " + s,
						Toast.LENGTH_SHORT).show();
				break;

			default:
				break;
			}

			return false;
		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {
			isActionModeOn = false;
			mLv.clearChoices();
			mLv.setChoiceMode(ListView.CHOICE_MODE_NONE);
			choiceMode = ListView.CHOICE_MODE_NONE;
			mLv.setAdapter(getListAdapter());
			if (actionMode != null) {
				actionMode = null;
			}
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			checked = mLv.getCheckedItemPositions();
			int j = 0;
			for (int i = 0; i < mLv.getAdapter().getCount(); i++) {
				if (checked.get(i)) {
					j++;
				}
			}
			checkedNumber = j;
			actionMode.setSubtitle("(" + checkedNumber + ")");
		}

	}

}
