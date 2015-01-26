package com.byb.beatyourbucket;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


public class FragmentBucket  extends ListFragment {
	// JSON node keys
	
	HashMap<String, String> dictionary = new HashMap<String, String>();
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		
		
		View v = inflater.inflate(R.layout.view_buckets, container, false);
		
		final ListView listview = (ListView) v.findViewById(android.R.id.list);
		
		SharedPreferences preferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
		String userID = preferences.getString("user_ID", "empty");
		Log.d("ID",userID);
		
		String location = "bucketlistsforuser.php";
		String key = "facebook_id";
//		String value = settings.getString("user_ID", "");
		String value = "735154943247882";
		
		Log.d("Loginengeinasd", value);
		
		final GetFromDatabase data = new GetFromDatabase(location, key,
				value, new onLoadingFinishedListener() {
			
			@Override
			public void onLoadingFinished(ArrayList<JSONObject> datalist) {
				// TODO Auto-generated method stub

			    final ArrayList<String> list = new ArrayList<String>();
			    for (int i = 0; i < datalist.size(); ++i) {
			    	String title;
			    	String imagelink;
			    	String bucketlistid;
			    	
					try {
						title = datalist.get(i).get("title").toString();
						imagelink = "http://alpha.beatyourbucket.com" + datalist.get(i).get("image").toString();
						bucketlistid = datalist.get(i).get("bucketlist_id").toString();
						String view = imagelink + "          " + title;
						
//						InputStream is = (InputStream) new URL("http://alpha.beatyourbucket.com" + imagelink).getContent();
//				        Drawable d = Drawable.createFromStream(is, "byb");
						
						list.add(view);
						dictionary.put(bucketlistid, view);
						
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
			            	
							Fragment fl = new FragmentLists();
			            	
			            	final String item = (String) parent.getItemAtPosition(position);
			            	String theval = null;
			            	for (HashMap.Entry<String,String> entry : dictionary.entrySet()) {
			            		String key = entry.getKey();
			            		String value = entry.getValue();
			            		if(value==item){
			            			theval = key;
			            			SharedPreferences preferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
			            			SharedPreferences.Editor editor = preferences.edit();
			            			editor.putString("bucketID", key);
			            			editor.commit();
			            		}
			            	}
			            	
			            	Log.d("daaa", theval);
			            	Log.d("item", item);
			            	Log.d("dict", dictionary.keySet().toString());
			            	
			            	Log.d("ldoosad", "" + position);
							
			            	FragmentManager fm = getFragmentManager();
							FragmentTransaction ft = fm.beginTransaction();
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