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
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private static Client client;
	
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
		
		/*
		 * Grabs USERNAME and password credentials and saves them to login 
		 * and password variables
		 */
		EditText usernameEditText = (EditText) this.findViewById(R.id.username_message);
		String login = usernameEditText.getText().toString();
		EditText passwordEditText = (EditText) this.findViewById(R.id.password_message);
		String password = passwordEditText.getText().toString();
		
		/*
		 * Sets up an alert dialog to be used if the user forgets to enter a username,
		 * password, or both. The client will only contact the server if both are filled.
		 */
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle("UH OH!");
		alertDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				return;
			}
		});
		
		
		if (login.equals("") && password.equals("")) {
			alertDialog.setMessage("You need to enter a username and password to login!");
			alertDialog.show();
		} else if (login.equals("")) {
			alertDialog.setMessage("You need to enter a username to login!");
			alertDialog.show();
		} else if (password.equals("")) {
			alertDialog.setMessage("You need to enter a password to login!");
			alertDialog.show();
		} else {
			
			/*
			 * Needed: pass login and password strings to Server for login
			 */
			
			
			
			/*
			 * Once login is successful, open welcome screen
			 */
			Intent welcomeIntent = new Intent(this, DisplayWelcomeActivity.class);
			startActivity(welcomeIntent);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public static Client getClient() {
		if(client == null) {
			client = new Client("data.cs.purdue.edu", 5555);
			Transaction trans[] = new Transaction[50];
			for(int i=0;i < 15; i++){
				trans[i] = new Transaction(i, "4/" + i + "/2013", "Walmart"+i, i*10);
			}

			int current = 200;
			client.setUser("test");
			client.setCurrentBalance(current);
			
			client.setTransactions(trans);
			
			//client.handleMessageFromServer((Object)trans);
		}
		return client;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
