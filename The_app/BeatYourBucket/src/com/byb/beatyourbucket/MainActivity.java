package com.byb.beatyourbucket;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.Window;
//import android.view.WindowManager;
//import android.view.WindowManager.LayoutParams;
//import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        makeMenu();
        
    }
	
	public void makeMenu(){
		
//        actionBar.setDisplayShowHomeEnabled(false);
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setDisplayHomeAsUpEnabled(false);
        
        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        
        tabHost.setup();
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("bucketlist");
        tabSpec.setContent(R.id.tab1);
        tabSpec.setIndicator("Bucketlist");
        tabHost.addTab(tabSpec);
        
        tabSpec = tabHost.newTabSpec("home");
        tabSpec.setContent(R.id.tab2);
        tabSpec.setIndicator("Home");
        tabHost.addTab(tabSpec);
        
        tabSpec = tabHost.newTabSpec("profile");
        tabSpec.setContent(R.id.tab3);
        tabSpec.setIndicator("Profiel");
        tabHost.addTab(tabSpec);
        
        tabHost.setCurrentTabByTag("home");
	}
	
//	public void seeList(View view) {
//		Intent intent = new Intent(this, ListActivity.class);
//		startActivity(intent);
//		finish();
//	}
	
	@Override
	public void onStart()
	{
		super.onStart();
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
	}

	@Override
	public void onPause()
	{
		super.onPause();
	}


}
