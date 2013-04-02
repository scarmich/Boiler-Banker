package com.example.boilerbanker;

import android.app.Application;
import android.content.Context;

public class ApplicationContext extends Application{
	private static Context context;
	
	public void onCreate(){
		context = getApplicationContext();
	}
	
	public static Context getMyApplicationContext() {
		return context;
	}
}
