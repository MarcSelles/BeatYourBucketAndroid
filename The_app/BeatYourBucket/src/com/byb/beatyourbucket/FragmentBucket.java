package com.byb.beatyourbucket;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.client.methods.HttpPost;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;




public class FragmentBucket  extends ListFragment implements OnItemClickListener{
	private static final String TAG = "MyActivity";
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.view_buckets, container, false);
		return v;
	}
	
	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
     
        super.onActivityCreated(savedInstanceState);
        Log.v(TAG, "hoi");
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.buckets, android.R.layout.simple_list_item_1);
       
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);

    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Log.v(TAG, "hoiiiii");
		// TODO Auto-generated method stub
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		Fragment fl = new FragmentLists();
		ft.replace(android.R.id.tabcontent, fl);
		ft.addToBackStack( "tag" );
//		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//		FragmentLists fl = new FragmentLists();
//		fragmentTransaction.replace(android.R.id.tabcontent, fl);
		ft.commit();
	}
	
	public static void main(String[] args) throws IOException {
		long fb = 735154943247882L;
		
		
		URL url = new URL("http://alpha.beatyourbucket.com/api/bucketlistsforuser.php");
		
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
//		httpCon.addParameter("facebook_id", fb);
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		con.setDoInput(true);
		
		OutputStreamWriter out = new OutputStreamWriter(
		con.getOutputStream());
		Log.v(TAG, con.getResponseMessage());
//		System.out.println(httpCon.getResponseCode());
//		System.out.println(httpCon.getResponseMessage());
		out.close();
		}
	
	
	
	
}