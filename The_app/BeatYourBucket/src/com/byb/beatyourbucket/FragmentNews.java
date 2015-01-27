package com.byb.beatyourbucket;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentNews  extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.tab2_view, container, false);
		
		final ListView listview = (ListView) v.findViewById(android.R.id.list);
		
//		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
		String location = "updatesforuser.php";
		String key = "facebook_id";
		String value = "735154943247882";
		
//        String value = settings.getString("user_ID", "");
		
		final GetFromDatabase data = new GetFromDatabase(location,key, value, new onLoadingFinishedListener() {
			
			@Override
			public void onLoadingFinished(ArrayList<JSONObject> datalist) {
				// TODO Auto-generated method stub
				Collections.sort(datalist, new NewsComparator());
			    final ArrayList<String> namelist = new ArrayList<String>();
			    final ArrayList<String> urlImages = new ArrayList<String>();
			    final ArrayList<String> infolist = new ArrayList<String>();
			    for (int i = 0; i < datalist.size(); ++i) {
			    	String firstname;
			    	String lastname;
			    	String challenge;
			    	String description;
			    	String imagelink;
			    	
					try {
						URI uri = new URI(
				    			"http",
				    			"alpha.beatyourbucket.com",
				    			datalist.get(i).get("url").toString(),
				    			null);
						
						firstname = datalist.get(i).get("first_name").toString();
						lastname = datalist.get(i).get("last_name").toString();
						challenge = datalist.get(i).get("challenge").toString();
						description = datalist.get(i).get("description").toString();
//						time = datalist.get(i).get("description").toString();
						imagelink = uri.toString();
						Log.d("link", imagelink);
						
						namelist.add(firstname + " " + lastname);
						urlImages.add(imagelink);
						infolist.add(challenge + "      " + description);
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			      
			    }
			final NewsAdapter adapter = new NewsAdapter(getActivity(),
	            R.layout.bucketlist_layout, namelist, urlImages, infolist);
	        listview.setAdapter(adapter);
			}
		});
		Log.d("aah","aah");
		data.execute();
		
		
		return v;
	}
	private class NewsComparator implements Comparator<JSONObject>{
		@Override
		public int compare(JSONObject o1, JSONObject o2) {
			try {
				if(o1.getLong("timetamp")>o2.getLong("timetamp")){
					return -1;
				} else if(o1.getLong("timetamp")==o2.getLong("timetamp")){
					return 0;
				}
				else{
					return 1;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}
	}
}
