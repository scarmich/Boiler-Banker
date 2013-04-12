package com.example.boilerbanker;

import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

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
		// Alert message set up
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle("UH OH!");
		alertDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				return;
			}
		});
		
		
		// Saves username and password to strings
		TextView userName = (TextView) findViewById(R.id.usernameTextView);
		TextView password = (TextView) findViewById(R.id.passwordTextView);
		
		String user = userName.getText().toString();
		String pass = password.getText().toString();
		
		if (user.equals("") && pass.equals("")) {
			
		} else if (user.equals("")) {
			
		} else if (pass.equals("")) {
			
		} else {
			try {
				Client client = new Client("data.cs.purdue.edu", 5002);
				client.sendUserCredentials(user, pass);
				Intent welcomeIntent = new Intent(this, DisplayWelcomeActivity.class);
				startActivity(welcomeIntent);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				alertDialog.setMessage("Failed to connect to server");
				alertDialog.show();
			}
			
			
		}
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
