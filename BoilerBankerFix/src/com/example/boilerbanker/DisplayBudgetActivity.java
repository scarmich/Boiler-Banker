package com.example.boilerbanker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.EditText;

public class DisplayBudgetActivity extends Activity implements SurfaceHolder.Callback {
	
	private SurfaceView sv;
	private SurfaceHolder sh;
	private EditText dateField;
	private Button goButton;
	public double[] percents = { 12, 22.9, 8.4, 17, 4, 15.7, 14, 6 };
	public int[] colors = { Color.BLUE, Color.GREEN, Color.CYAN, Color.GRAY, Color.MAGENTA, Color.RED, Color.WHITE, Color.YELLOW };
	public String[] names = { "Gas", "Groceries", "Savings", "Restaurants", "Entertainment", "Online", "Taxes", "School" };
	
	private static final String TAG = "BUDGET_ACTIVITY";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_budget);
		// Show the Up button in the action bar.
		setupActionBar();
		
		sv = (SurfaceView)findViewById(R.id.surfaceView1);
		sh = sv.getHolder();
		sh.addCallback(this);
		sv.setWillNotDraw(false);
		
		dateField = (EditText)findViewById(R.id.dateField);
		goButton = (Button)findViewById(R.id.goButton);
		dateField.setHint("Enter date range here");
		goButton.setText("Go");
	
	}
	

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}


	protected void tryDrawing(SurfaceHolder holder) {
		Log.i(TAG, "Trying to draw...");
		
		Canvas canvas = holder.lockCanvas();
		if(canvas == null) {
			Log.e(TAG, "Cannot draw onto canvas as it's null");
		} else {
			drawMyStuff(canvas);
			holder.unlockCanvasAndPost(canvas);
		}
		sv.setBackgroundColor(Color.RED);
	}

	private void drawMyStuff(final Canvas canvas) {
		Log.i(TAG, "Drawing...");
		//canvas.drawColor(Color.BLACK);
		//int[] colors = { Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA, Color.YELLOW };
		
		Paint paint1 = new Paint();
//		Paint paint2 = new Paint();
		paint1.setStyle(Paint.Style.FILL);
//		paint2.setStyle(Paint.Style.FILL);
		RectF rectF = new RectF();
		int height = sv.getHeight();
		int width = sv.getWidth();
		rectF.set(0, 0, width / 2, height / 2);
		double curAngle = 0;
		int curLine = 0;
		for(int i = 0; i < percents.length; i++) {
			paint1.setColor(colors[i]);
//			paint2.setColor(colors[i]);
			canvas.drawArc(rectF, (int)curAngle, (int)Math.ceil((360 * percents[i] / 100)), true, paint1);
			paint1.setTextSize(30);
			canvas.drawText(names[i] + " : " + percents[i] + "%", width / 2 + 10, curLine * 40 + 30, paint1);
			curAngle += 360 * percents[i] / 100;
			curLine++;
		}
	}
	
	@SuppressLint("WrongCall")
	public void surfaceCreated(SurfaceHolder holder) {
		Canvas canvas = null;
		try {
			canvas = holder.lockCanvas();
			synchronized(holder) {
				onDraw(canvas);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(canvas != null) {
				holder.unlockCanvasAndPost(canvas);
			}
		}
		
	}

	private void onDraw(Canvas canvas) {
		drawMyStuff(canvas);
	}


	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
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


}
