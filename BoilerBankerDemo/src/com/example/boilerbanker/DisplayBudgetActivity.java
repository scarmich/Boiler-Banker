package com.example.boilerbanker;

import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
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

		/* Old 5 hardcoded transactions
		Transaction trans1 = new Transaction(001, "3/1/2013", "Walmart", 168.9);
		Transaction trans2 = new Transaction(002, "3/5/2013", "Gas", 46.69);
		Transaction trans3 = new Transaction(003, "3/19/2013", "Applebees",
				24.99);
		Transaction trans4 = new Transaction(004, "4/1/2013", "Cell Bill",
				75.99);
		Transaction trans5 = new Transaction(005, "4/9/2013", "Pizza Hut",
				20.00);
		transactions.add(trans1);
		transactions.add(trans2);
		transactions.add(trans3);
		transactions.add(trans4);
		transactions.add(trans5);
		*/
		Transaction [] trans = MainActivity.getClient().getUserData().getTransactions();
		for(int i = 0; i < trans.length; i++) {
			transactions.add(trans[i]);
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
					|| transactions.get(i).getLocation().contains("MCDONALDS")
					|| transactions.get(i).getLocation().contains("TACO_BELL")
					|| transactions.get(i).getLocation()
							.contains("MC_FIESTA_MEXICAN")
					|| transactions.get(i).getLocation()
							.contains("PANDA_EXPRESS")
					|| transactions.get(i).getLocation().contains("MCDONALDS")
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
			} else if (transactions.get(i).getLocation().contains("MACYS")) {
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
				while (name.contains(" ")) {
					name = name.replace(" ", "%20");
				}
				url += name;
				if (i < cats.size() - 1)
					url += "|";
			} else { // if category amount is 0
				if(i == cats.size() - 1) { // if this is last category
					url = url.substring(0, url.length() - 1); //remove last |
				}
			}
		}
		url += "&chco=0000FF,FF0000"; // set color gradient
		System.out.println("URL: " + url);
		webView.loadUrl(url);
	}

	/*
	 * public void surfaceChanged(SurfaceHolder holder, int format, int width,
	 * int height) { }
	 * 
	 * protected void tryDrawing(SurfaceHolder holder) { Log.i(TAG,
	 * "Trying to draw...");
	 * 
	 * Canvas canvas = holder.lockCanvas(); if (canvas == null) { Log.e(TAG,
	 * "Cannot draw onto canvas as it's null"); } else { drawMyStuff(canvas);
	 * holder.unlockCanvasAndPost(canvas); } sv.setBackgroundColor(Color.RED); }
	 * 
	 * private void drawMyStuff(final Canvas canvas) { Log.i(TAG, "Drawing...");
	 * // canvas.drawColor(Color.BLACK); // int[] colors = { Color.RED,
	 * Color.BLUE, Color.GREEN, Color.MAGENTA, // Color.YELLOW };
	 * 
	 * Paint paint1 = new Paint(); // Paint paint2 = new Paint();
	 * paint1.setStyle(Paint.Style.FILL); // paint2.setStyle(Paint.Style.FILL);
	 * RectF rectF = new RectF(); int height = sv.getHeight(); int width =
	 * sv.getWidth(); rectF.set(0, 0, width / 2, height / 2); double curAngle =
	 * 0; int curLine = 0; for (int i = 0; i < percents.length; i++) {
	 * paint1.setColor(colors[i]); // paint2.setColor(colors[i]);
	 * canvas.drawArc(rectF, (int) curAngle, (int) Math.ceil((360 * percents[i]
	 * / 100)), true, paint1); paint1.setTextSize(30); canvas.drawText(names[i]
	 * + " : " + percents[i] + "%", width / 2 + 10, curLine * 40 + 30, paint1);
	 * curAngle += 360 * percents[i] / 100; curLine++; } }
	 * 
	 * @SuppressLint("WrongCall") public void surfaceCreated(SurfaceHolder
	 * holder) { /* Canvas canvas = null; try { canvas = holder.lockCanvas();
	 * synchronized (holder) { onDraw(canvas); } } catch (Exception e) {
	 * e.printStackTrace(); } finally { if (canvas != null) {
	 * holder.unlockCanvasAndPost(canvas); } }
	 * 
	 * 
	 * }
	 * 
	 * private void onDraw(Canvas canvas) { drawMyStuff(canvas); }
	 * 
	 * public void surfaceDestroyed(SurfaceHolder holder) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 */

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