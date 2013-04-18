package com.example.boilerbanker;

import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class DisplayBudgetActivity extends Activity implements
		SurfaceHolder.Callback {

	private SurfaceView sv;
	private SurfaceHolder sh;
	private EditText dateField;
	private Button goButton;

	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

	private static final String TAG = "BUDGET_ACTIVITY";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_budget);
		// Show the Up button in the action bar.
		setupActionBar();

		sv = (SurfaceView) findViewById(R.id.surfaceView1);
		sh = sv.getHolder();
		sh.addCallback(this);
		sv.setWillNotDraw(false);

		dateField = (EditText) findViewById(R.id.dateField);
		goButton = (Button) findViewById(R.id.budgetGoButton);
		dateField.setHint("Enter date range here");

		Transaction[] trans = MainActivity.getClient().getUserData()
				.getTransactions();
		for (int i = 0; i < trans.length; i++) {
			transactions.add(trans[i]);
		}
		if (transactions != null) {
			Log.i(TAG, "Transactions size: " + transactions.size());
		} else {
			Log.i(TAG, "Transactions was null");
		}
		for (int i = 0; i < transactions.size(); i++) {
			if (transactions.get(i) == null) {
				transactions.remove(i);
				i--;
			}
		}
		displayData(transactions);
	}

	public void backReturnWelcome(View view) {
		finish();
	}

	public void refreshData(View view) {
		EditText et = (EditText) findViewById(R.id.dateField);
		String text = et.getText().toString();
		if (!text.contains("-"))
			return;
		StringTokenizer st = new StringTokenizer(text, "-");
		String start = st.nextToken();
		String end = st.nextToken();
		Date sDate = new Date(start);
		Date eDate = new Date(end);
		ArrayList<Transaction> temp = new ArrayList<Transaction>();
		for (int i = 0; i < transactions.size(); i++) {
			Date tDate = new Date(transactions.get(i).getDate());
			if (tDate.compareTo(sDate) >= 0 && tDate.compareTo(eDate) <= 0) {
				temp.add(transactions.get(i));
			}
		}
		displayData(temp);
	}

	public void displayData(ArrayList<Transaction> transactions) {
		ArrayList<Category> cats = new ArrayList<Category>();
		cats.add(new Category("Food"));
		cats.add(new Category("Groceries"));
		cats.add(new Category("Pharmacy"));
		cats.add(new Category("Entertainment"));
		cats.add(new Category("Clothing"));
		cats.add(new Category("Auto"));
		cats.add(new Category("Other"));
		int fIndex = 0;
		int gIndex = 1;
		int pIndex = 2;
		int eIndex = 3;
		int cIndex = 4;
		int aIndex = 5;
		int oIndex = 6;
		for (int i = 0; i < transactions.size(); i++) {
			if (transactions.get(i).getLocation().contains("CHIPOTLE")
					|| transactions.get(i).getLocation().contains("MCDONALDS")
					|| transactions.get(i).getLocation().contains("COCA_COLA")
					|| transactions.get(i).getLocation().contains("GREEN_LEAF")
					|| transactions.get(i).getLocation().contains("LINS_WOK")
					|| transactions.get(i).getLocation().contains("MCDONALD'S")
					|| transactions.get(i).getLocation().contains("TACO_BELL")
					|| transactions.get(i).getLocation()
							.contains("MC_FIESTA_MEXICAN")
					|| transactions.get(i).getLocation()
							.contains("PANDA_EXPRESS")
					|| transactions.get(i).getLocation()
							.contains("HOT_WOKS_COOL_SUSHI")) {
				cats.get(fIndex).addTransaction(transactions.get(i));
			} else if (transactions.get(i).getLocation().contains("WAL-MART")) {
				cats.get(gIndex).addTransaction(transactions.get(i));
			} else if (transactions.get(i).getLocation().contains("WALGREENS")
					|| transactions.get(i).getLocation()
							.contains("CVS_PHARMACY")) {
				cats.get(pIndex).addTransaction(transactions.get(i));
			} else if (transactions.get(i).getLocation()
					.contains("WABASH_LANDING_9")) {
				cats.get(eIndex).addTransaction(transactions.get(i));
			} else if (transactions.get(i).getLocation().contains("MACY*S")) {
				cats.get(cIndex).addTransaction(transactions.get(i));
			} else if (transactions.get(i).getLocation()
					.contains("BOB_ROHRMAN")
					|| transactions.get(i).getLocation().contains("AUTOZONE")) {
				cats.get(aIndex).addTransaction(transactions.get(i));
			} else {
				cats.get(oIndex).addTransaction(transactions.get(i));
			}
		}

		WebView webView = (WebView) findViewById(R.id.webView1);
		String url = "https://chart.googleapis.com/chart?cht=p3&chs=300x275&chd=t:";
		double totalSpent = 0;
		for (int i = 0; i < cats.size(); i++) {
			totalSpent += cats.get(i).getAmount();
		}
		for (int i = 0; i < cats.size(); i++) {
			if (cats.get(i).getAmount() > 0) {
				url += (int) (100 * cats.get(i).getAmount() / totalSpent);
				if (i < cats.size() - 1) {
					url += ",";
				}
			} else { // if category amount is 0
				if (i == cats.size() - 1) { // if this is last category
					url = url.substring(0, url.length() - 1); // remove last
																// comma
				}
			}
		}

		url += "&chdlp=bv&chdl="; // set legend below graph and vertical
		for (int i = 0; i < cats.size(); i++) {
			if (cats.get(i).getAmount() > 0) {
				String name = cats.get(i).getName();
				int percent = (int)(100* cats.get(i).getAmount() / totalSpent);
				name += " " + percent + "%";
				while (name.contains(" ")) {
					name = name.replace(" ", "%20");
				}
				url += name;
				if (i < cats.size() - 1)
					url += "|";
			} else { // if category amount is 0
				if (i == cats.size() - 1) { // if this is last category
					url = url.substring(0, url.length() - 1); // remove last
																// |
				}
			}
		}
		url += "&chco=00F0FF,FF0F00"; // set color gradient
		System.out.println("URL: " + url);
		webView.loadUrl(url);
		for(int i = 0; i < cats.size(); i++) {
			Log.i(TAG, "Category: " + cats.get(i).getName() + " Amount: " + cats.get(i).getAmount());
			for(int j = 0; j < cats.get(i).getSize(); j++) {
				Log.i(TAG, "Transaction: " + cats.get(i).getTrans().get(j).getLocation() + " Amount: " + cats.get(i).getTrans().get(j).getAmount());
			}
		}
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
		getMenuInflater().inflate(R.menu.display_budget, menu);
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

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
	}

	public void surfaceCreated(SurfaceHolder arg0) {
	}

	public void surfaceDestroyed(SurfaceHolder arg0) {
	}

}