package com.byb.beatyourbucket;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;


public class MainScreen extends ActionBarActivity {
	
//	private FragmentTabHost mHost;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        // Hide the actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
	
	
	public void makeTabs(View v){
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		if(v.getId()==R.id.bucketlists){
			Fragment fb = new FragmentBucket();
			ft.replace(android.R.id.tabcontent, fb);
		}
		else if(v.getId()==R.id.newsfeed){
			Fragment fb = new FragmentNews();
			ft.replace(android.R.id.tabcontent, fb);
		}
		else if(v.getId()==R.id.profile){
			Fragment fb = new FragmentProfile();
			ft.replace(android.R.id.tabcontent, fb);
		}
		ft.commit();
	}
	
	public void makeTabs2(View v){
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		if(v.getId()==R.id.challenges){
			Fragment fb = new FragmentChallenges();
			ft.replace(R.id.tabcontent2, fb);
		}
		else if(v.getId()==R.id.completed){
			Fragment fb = new FragmentCompleted();
			ft.replace(R.id.tabcontent2, fb);
		}
		else if(v.getId()==R.id.score){
			Fragment fb = new FragmentScore();
			Log.d("vla", "hodasd");
			ft.replace(R.id.tabcontent2, fb);
		}
		ft.commit();
	}
	
	
	@Override
	public void onStart()
	{
		super.onStart();
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		
		// Logs 'install' and 'app activate' App Events.
//		  AppEventsLogger.activateApp(this);
	}

	@Override
	public void onPause()
	{
		super.onPause();
		
		// Logs 'app deactivate' App Event.
//		  AppEventsLogger.deactivateApp(this);
	}


}
