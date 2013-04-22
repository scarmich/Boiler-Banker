package com.example.boilerbanker;

import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class DisplayWelcomeActivity extends Activity {
	

	public void signOutReturnToWelcome(View view) {
		
		finish();
	}
	
	public void refresh() {
		final ProgressDialog progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("Logging in to PEFCU");
		progressDialog.show();

		Thread thread = new Thread() {
			public void run() {
				MainActivity.getClient().sendUserCredentials(MainActivity.user, MainActivity.pass);
				while (MainActivity.waiting) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						progressDialog.dismiss();
					}
				});
			}
		};
		MainActivity.waiting = true;
		thread.start();
	}

	public void openSettingsActivity(View view) {
		Intent settingsIntent = new Intent(this, DisplaySettingsViewActivity.class);
		startActivity(settingsIntent);
	}
	
	public void openTransactionActivity(View view) {
		Intent TransactionsIntent = new Intent(this, DisplayTransactionActivities.class);
		startActivity(TransactionsIntent);
	}
	
	public void openBudgetActivity(View view) {
		Intent budgetIntent = new Intent(this, DisplayBudgetActivity.class);
		startActivity(budgetIntent);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_welcome);
		// Show the Up button in the action bar.
		setupActionBar();
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
		getMenuInflater().inflate(R.menu.display_welcome, menu);
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
