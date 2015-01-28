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
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

class GetFromDatabase extends AsyncTask<Void, Integer, String> {

	private ArrayList<JSONObject> list;
	private onLoadingFinishedListener listener;
	private String location;
	private String key;
	private String value;

	public GetFromDatabase(String location, String key, String value,
			onLoadingFinishedListener listener) {
		this.listener = listener;
		this.location = location;
		this.key = key;
		this.value = value;
	}

	@Override
	protected String doInBackground(Void... params) {
		return GetBucketData(location, key, value);
	}

	protected void onPostExecute(String result) {
		list = new ArrayList<JSONObject>();
		Log.d("loggin", "hoi");
		try {
			JSONArray jsonarray = new JSONArray(result);

			for (int i = 0; i < jsonarray.length(); i++) {
				JSONObject jsonobject = jsonarray.getJSONObject(i);
				list.add(jsonobject);
				listener.onLoadingFinished(list);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public String GetBucketData(String location, String key, String value) {
		String jstring = null;

		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://alpha.beatyourbucket.com/api/"
				+ location);
		Log.d("ksad", "http://alpha.beatyourbucket.com/api/" + location);

		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair(key, value));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);
			jstring = EntityUtils.toString(response.getEntity());
			Log.d("entity", jstring);

		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		}

		return jstring;
	}
}