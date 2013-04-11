package com.example.boilerbanker;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

public class DisplaySettingsViewActivity extends Activity {
	
	CheckBox offView;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_settings_view);
		offView = (CheckBox) findViewById(R.id.offlineCheckbox);
		// Show the Up button in the action bar.
		setupActionBar();
		loadPrefs();
	}
	
	public void backClicked(View view) {
		saveChanges(view);
		finish();
	}
	
	private void loadPrefs() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		boolean checked = sp.getBoolean("OFFLINE_CHECKBOX", false);
		if (checked) {
			offView.setChecked(true);
		} else {
			offView.setChecked(false);
		}
		
	}
	
	private void savePrefs(String key, boolean value) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		Editor edit = sp.edit();
		edit.putBoolean(key, value);
		edit.commit();
	}
	
	public void saveChanges(View view) {
		savePrefs("OFFLINE_CHECKBOX", offView.isChecked());
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_settings_view, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
