package com.byb.beatyourbucket;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
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


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;




public class FragmentBucket  extends Fragment {
	private static final String TAG = "MyActivity";
	private EditText value;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.view_buckets, container, false);
		
		return v;
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
			if(value.getText().toString().length()<1){
				
				// out of range


			}else{
				
				new MyAsyncTask().execute(value.getText().toString());		
			}
		
 
	} 
	
//	@Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//     
//        super.onActivityCreated(savedInstanceState);
//        Log.v(TAG, "hoi");
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.buckets, android.R.layout.simple_list_item_1);
//       
//        setListAdapter(adapter);
//        getListView().setOnItemClickListener(this);
//
//    }
	
	private class MyAsyncTask extends AsyncTask<String, Integer, Double>{
		 
		@Override
		protected Double doInBackground(String... params) {
			// TODO Auto-generated method stub
			GetBucketData();
			return null;
		}
 
//		protected void onPostExecute(Double result){
//			pb.setVisibility(View.GONE);
//			Toast.makeText(getApplicationContext(), "command sent", Toast.LENGTH_LONG).show();
//		}
//		protected void onProgressUpdate(Integer... progress){
//			pb.setProgress(progress[0]);
	
	public void GetBucketData() {
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
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }
	}

//	@Override
//	public void onItemClick(AdapterView<?> parent, View view, int position,
//			long id) {
//		Log.v(TAG, "hoiiiii");
//		// TODO Auto-generated method stub
//		FragmentManager fm = getFragmentManager();
//		FragmentTransaction ft = fm.beginTransaction();
//		Fragment fl = new FragmentLists();
//		ft.replace(android.R.id.tabcontent, fl);
//		ft.addToBackStack( "tag" );
////		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
////		FragmentLists fl = new FragmentLists();
////		fragmentTransaction.replace(android.R.id.tabcontent, fl);
//		ft.commit();
//	}
//	
//	public static void main(String[] args) throws IOException {
//		long fb = 735154943247882L;
//		
//		
//		URL url = new URL("http://alpha.beatyourbucket.com/api/bucketlistsforuser.php");
//		
//		
//		HttpURLConnection con = (HttpURLConnection) url.openConnection();
//		
////		httpCon.addParameter("facebook_id", fb);
//		con.setRequestMethod("POST");
//		con.setDoOutput(true);
//		con.setDoInput(true);
//		
//		OutputStreamWriter out = new OutputStreamWriter(
//		con.getOutputStream());
//		Log.v(TAG, con.getResponseMessage());
////		System.out.println(httpCon.getResponseCode());
////		System.out.println(httpCon.getResponseMessage());
//		out.close();
//		}
	
	
	
	}
}