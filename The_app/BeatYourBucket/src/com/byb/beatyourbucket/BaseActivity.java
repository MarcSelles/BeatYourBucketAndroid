package com.byb.beatyourbucket;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BaseActivity extends ActionBarActivity {
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
    	TextView text = (TextView) findViewById(R.id.hoi);
    	final Button button = (Button) findViewById(R.id.list);
    	switch (item.getItemId())
    	{
    		
    		case R.id.bucketlists:
				text.setText("test1");
				button.setVisibility(View.VISIBLE);
    			return true;
    		
    		case R.id.home:
				text.setText("test2");
				button.setVisibility(View.INVISIBLE);
    			return true;
    			
    		case R.id.profile:
				text.setText("test3");
				button.setVisibility(View.INVISIBLE);
    			return true;
    		
    		default:
    			return super.onOptionsItemSelected(item);
    	}
        
    }
}
