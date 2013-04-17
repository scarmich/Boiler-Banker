package com.example.boilerbanker;

import android.app.Activity;
import android.os.Bundle;
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
		trans1.setText(finals[0]);
		trans2.setText(finals[1]);
		trans3.setText(finals[2]);
		trans4.setText(finals[3]);
		trans5.setText(finals[4]);
		
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
