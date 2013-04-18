package com.example.boilerbanker;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class DisplayOfflineViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_offline_view);
		// Show the Up button in the action bar.
		setupActionBar();
		loadPrefs();
	}
	
	public void returnLogin(View view) {
		finish();
	}
	
	private void loadPrefs() {
		// load last balance textview
		TextView lastBal = (TextView) findViewById(R.id.off_current_balance_view);
		
		// load date textviews
		TextView date1 = (TextView) findViewById(R.id.off_date1);
		TextView date2 = (TextView) findViewById(R.id.off_date2);
		TextView date3 = (TextView) findViewById(R.id.off_date3);
		TextView date4 = (TextView) findViewById(R.id.off_date4);
		TextView date5 = (TextView) findViewById(R.id.off_date5);
		
		// load location textviews
		TextView loc1 = (TextView) findViewById(R.id.off_transaction1);
		TextView loc2 = (TextView) findViewById(R.id.off_transaction2);
		TextView loc3 = (TextView) findViewById(R.id.off_transaction3);
		TextView loc4 = (TextView) findViewById(R.id.off_transaction4);
		TextView loc5 = (TextView) findViewById(R.id.off_transaction5);
		
		// load amount textviews
		TextView amount1 = (TextView) findViewById(R.id.off_amount1);
		TextView amount2 = (TextView) findViewById(R.id.off_amount2);
		TextView amount3 = (TextView) findViewById(R.id.off_amount3);
		TextView amount4 = (TextView) findViewById(R.id.off_amount4);
		TextView amount5 = (TextView) findViewById(R.id.off_amount5);
		
		// Prepare to load transactions for offline mode
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		// Sets the keys for finding dates, locations, and amounts
		String[] d = {"d1", "d2", "d3", "d4", "d5"};
		String[] l = {"l1", "l2", "l3", "l4", "l5"};
		String[] a = {"a1", "a2", "a3", "a4", "a5"};
		
		// Strings to store last balance, dates, locations, and amounts
		String bal;
		String[] dates = new String[5];
		String[] locations = new String[5];
		String[] amounts = new String[5];
		
		// Grab last balance string
		bal = sp.getString("lastBalance", "$0.00");
		
		// Grabs strings from savedpreferences
		for (int i = 0; i < 5; i++) {
			dates[i] = sp.getString(d[i], "00/00/0000");
			locations[i] = sp.getString(l[i], "Location");
			amounts[i] = sp.getString(a[i], "$0.00");
		}
		
		// Set last balance
		String balance = "Last " + bal;
		lastBal.setText(balance);
		
		//Sets the dates
		date1.setText(dates[0]);
		date2.setText(dates[1]);
		date3.setText(dates[2]);
		date4.setText(dates[3]);
		date5.setText(dates[4]);
		
		//Sets the locations
		loc1.setText(locations[0]);
		loc2.setText(locations[1]);
		loc3.setText(locations[2]);
		loc4.setText(locations[3]);
		loc5.setText(locations[4]);
		
		//Sets the amounts
		amount1.setText(amounts[0]);
		amount2.setText(amounts[1]);
		amount3.setText(amounts[2]);
		amount4.setText(amounts[3]);
		amount5.setText(amounts[4]);
		
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
		getMenuInflater().inflate(R.menu.display_offline_view, menu);
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
