package com.example.boilerbanker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	
	public void openOfflineView(View view) {
		// Checks to see if OfflineView is enabled
				SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
				boolean checked = sp.getBoolean("OFFLINE_CHECKBOX", false);
				
				/*
				 * If checked is true, begins the new intent and opens the offline
				 * view. If false, informs the user
				 */
				if (checked) {
					Intent offlineIntent = new Intent(this, DisplayOfflineViewActivity.class);
					startActivity(offlineIntent);
				} else {
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
					alertDialog.setTitle("UH OH!");
					alertDialog.setMessage("Offline View is not set!");
					alertDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							return;
						}
					});
					alertDialog.show();
				}
	}
	
	public void openWelcome(View view) {
		//Takes the user to the welcome screen
		
		// Saves username and password to strings
		
		
		Intent welcomeIntent = new Intent(this, DisplayWelcomeActivity.class);
		startActivity(welcomeIntent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
