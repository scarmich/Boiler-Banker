package com.example.boilerbanker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	public void openOfflineView(View view) {
		//Takes the user to the offline view
		Intent offlineIntent = new Intent(this, DisplayOfflineViewActivity.class);
		startActivity(offlineIntent);
	}
	
	public void openWelcome(View view) {
		
		/*
		 * Grabs username and password credentials and saves them to login 
		 * and password variables
		 */
		EditText usernameEditText = (EditText) this.findViewById(R.id.username_message);
		String login = usernameEditText.getText().toString();
		EditText passwordEditText = (EditText) this.findViewById(R.id.password_message);
		String password = passwordEditText.getText().toString();
		
		/*
		 * Needed: pass login and password strings to Server for login
		 */
		
		
		
		/*
		 * Once login is successful, open welcome screen
		 */
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
