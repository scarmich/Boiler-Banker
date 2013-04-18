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
import android.widget.TextView;

public class DisplayTransactionActivities extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_transaction_activities);
		// Show the Up button in the action bar.
		setupActionBar();
		
		// sets up all textviews
		TextView balance = (TextView) findViewById(R.id.current_balance_view);
		TextView trans1 = (TextView) findViewById(R.id.transaction1);
		TextView trans2 = (TextView) findViewById(R.id.transaction2);
		TextView trans3 = (TextView) findViewById(R.id.transaction3);
		TextView trans4 = (TextView) findViewById(R.id.transaction4);
		TextView trans5 = (TextView) findViewById(R.id.transaction5);
		TextView date1 = (TextView) findViewById(R.id.date1);
		TextView date2 = (TextView) findViewById(R.id.date2);
		TextView date3 = (TextView) findViewById(R.id.date3);
		TextView date4 = (TextView) findViewById(R.id.date4);
		TextView date5 = (TextView) findViewById(R.id.date5);
		TextView amount1 = (TextView) findViewById(R.id.amount1);
		TextView amount2 = (TextView) findViewById(R.id.amount2);
		TextView amount3 = (TextView) findViewById(R.id.amount3);
		TextView amount4 = (TextView) findViewById(R.id.amount4);
		TextView amount5 = (TextView) findViewById(R.id.amount5);
		
		// accesses client
		Client client = MainActivity.getClient();
		
		// gets balance and sets to string
		Double balanceVal = client.getUserData().getBalance();
		String balanceText = "Current Balance: " + balanceVal.toString();
		
		String[] dates = new String[5];
		String[] locations = new String[5];
		double[] amounts = new double[5];
		String[] finals = new String[5];
		
		for (int i = 0; i < 5; i++) {
			dates[i] = client.getUserData().getTransactions()[i].getDate();
			locations[i] = client.getUserData().getTransactions()[i].getLocation().substring(0,15) + "...";
			locations[i] = locations[i].replaceAll("_",  " ");
			amounts[i] = client.getUserData().getTransactions()[i].getAmount();
			finals[i] = dates[i] + " " + locations[i] + " \t\t$" + String.format("%1$,.2f", amounts[i]);
		}
		
		// sets textviews
		balance.setText(balanceText);
		trans1.setText(locations[0]);
		trans2.setText(locations[1]);
		trans3.setText(locations[2]);
		trans4.setText(locations[3]);
		trans5.setText(locations[4]);
		date1.setText(dates[0]);
		date2.setText(dates[1]);
		date3.setText(dates[2]);
		date4.setText(dates[3]);
		date5.setText(dates[4]);
		
		String[] costs = {"$", "$", "$", "$", "$"};
		for (int i = 0; i < 5; i++) {
			costs[i] = costs[i] + String.format("%1$,.2f", amounts[i]);
		}
		amount1.setText(costs[0]);
		amount2.setText(costs[1]);
		amount3.setText(costs[2]);
		amount4.setText(costs[3]);
		amount5.setText(costs[4]);
		
		/*
		amount1.setText(String.format("%1$,.2f", amounts[0]));
		amount2.setText(String.format("%1$,.2f", amounts[1]));
		amount3.setText(String.format("%1$,.2f", amounts[2]));
		amount4.setText(String.format("%1$,.2f", amounts[3]));
		amount5.setText(String.format("%1$,.2f", amounts[4]));
		*/
		String[] d = {"d1", "d2", "d3", "d4", "d5"};
		String[] l = {"l1", "l2", "l3", "l4", "l5"};
		String[] a = {"a1", "a2", "a3", "a4", "a5"};
		
		for (int i = 0; i < 5; i++) {
			savePrefs(d[i], dates[i]);
			savePrefs(l[i], locations[i]);
			savePrefs(a[i], costs[i]);
		}
		savePrefs("lastBalance", balanceText);
	}
	
	private void savePrefs(String key, String value) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		Editor edit = sp.edit();
		edit.putString(key, value);
		edit.commit();
	}
	
	public void backReturnWelcome(View view) {
		finish();	
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
		getMenuInflater().inflate(R.menu.display_transaction_activities, menu);
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
