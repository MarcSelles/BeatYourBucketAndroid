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
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/* Class to get all the data needed
 * for a fragment
 */
class GetFromDatabase extends AsyncTask<Void, Integer, String> {

	private ArrayList<JSONObject> list;
	private onLoadingFinishedListener listener;
	private String location;
	private String key;
	private String value;
	private Context c;

	public GetFromDatabase(String location, String key, String value,
			Context c, onLoadingFinishedListener listener) {
		this.listener = listener;
		this.location = location;
		this.key = key;
		this.value = value;
		this.c = c;
	}

	@Override
	protected String doInBackground(Void... params) {
		try {
			return GetBucketData(location, key, value);
		} catch (Exception e) {
			return "";
		}
	}

	protected void onPostExecute(String result) {
		// Create a list with JSONObject inside
		list = new ArrayList<JSONObject>();
		try {
			JSONArray jsonarray = new JSONArray(result);
			// Set all the JSONObjects in the list
			for (int i = 0; i < jsonarray.length(); i++) {
				JSONObject jsonobject = jsonarray.getJSONObject(i);
				list.add(jsonobject);
				listener.onLoadingFinished(list);
			}
		} catch (Exception e) {
			Toast.makeText(c, "Geen internet. Probeer opnieuw!",
					Toast.LENGTH_SHORT).show();
		}
	}

	public String GetBucketData(String location, String key, String value)
			throws Exception {
		String jstring = null;

		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://alpha.beatyourbucket.com/api/"
				+ location);
		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair(key, value));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);
			jstring = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
		} catch (IOException e) {

		}
		return jstring;
	}
}
