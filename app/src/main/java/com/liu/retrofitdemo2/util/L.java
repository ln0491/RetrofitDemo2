package com.liu.retrofitdemo2.util;


import android.util.Log;

public class L {

	private static boolean debug =true;
	private static String TAG= "vivi";
	private L() {
		super();
	}

	
	public static void d(String tag,String msg){
		if(debug){
			Log.d(tag,msg);
		}
	}
	
	public static void d(String msg){
		if(debug){
			Log.d(TAG,msg);
		}
	}
}
