package com.example.boilerbanker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	
	public void openOfflineView(View view) {
		//Takes the user to the offline view
		Intent offlineIntent = new Intent(this, DisplayOfflineViewActivity.class);
		startActivity(offlineIntent);
	}
	
	public void openWelcome(View view) {
		//Takes the user to the welcome screen
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
