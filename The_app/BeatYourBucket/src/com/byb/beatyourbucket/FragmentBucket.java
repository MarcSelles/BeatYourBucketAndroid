package com.byb.beatyourbucket;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.internal.widget.AdapterViewCompat.OnItemClickListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


public class FragmentBucket  extends ListFragment {
	// JSON node keys
	

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.view_buckets, container, false);
		
		final ListView listview = (ListView) v.findViewById(android.R.id.list);
		
		String location = "bucketlistsforuser.php";
		String key = "facebook_id";
		String value = "735154943247882";
		
		final GetFromDatabase data = new GetFromDatabase(location, key,
				value, new onLoadingFinishedListener() {
			
			@Override
			public void onLoadingFinished(ArrayList<JSONObject> datalist) {
				// TODO Auto-generated method stub

			    final ArrayList<String> list = new ArrayList<String>();
			    for (int i = 0; i < datalist.size(); ++i) {
			    	String title;
			    	String imagelink;
					try {
						title = datalist.get(i).get("title").toString();
						imagelink = datalist.get(i).get("image").toString();
						
//						InputStream is = (InputStream) new URL("http://alpha.beatyourbucket.com" + imagelink).getContent();
//				        Drawable d = Drawable.createFromStream(is, "byb");
						
						list.add("http://alpha.beatyourbucket.com" + imagelink + "          " + title);
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
			      
			    }
			    final BucketlistAdapter adapter = new BucketlistAdapter(getActivity(),
			            android.R.layout.simple_list_item_1, list);
			        listview.setAdapter(adapter);
			        
			        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			            @Override
			            public void onItemClick(AdapterView<?> parent, final View view,
			                int position, long id) {
			            	
			            	Log.d("ldoosad", "" + position);
							FragmentManager fm = getFragmentManager();
							FragmentTransaction ft = fm.beginTransaction();
							Fragment fl = new FragmentLists();
							ft.replace(android.R.id.tabcontent, fl);
							ft.addToBackStack( "tag" );
							ft.commit();
						}
					});
			        
			}
		});
		Log.d("aah","aah");
		data.execute();
		
		
		return v;
		}

}