package com.byb.beatyourbucket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import android.os.AsyncTask;
import android.util.Log;

class GetFromDatabase extends AsyncTask<Void, Integer, String>{
		 
	@Override
	protected String doInBackground(Void... params) {
		// TODO Auto-generated method stub
		return GetBucketData();
	}
	
	protected void onPostExecute(String result){
		
	}

	
	public String GetBucketData() {
		String jstring = null;
		
		// Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://alpha.beatyourbucket.com/api/bucketlistsforuser.php");
	    
	    try {
	        // Add your data
	    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("facebook_id", "735154943247882"));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        
	     // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	        jstring = EntityUtils.toString(response.getEntity());
	        Log.d("entity", jstring);
	        
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }
	    
		return jstring;
	    
		
	}
}