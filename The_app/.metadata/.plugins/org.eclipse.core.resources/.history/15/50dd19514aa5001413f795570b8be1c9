package com.byb.beatyourbucket;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.support.v4.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
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

			    final ArrayList<String> list = new ArrayList<String>();
			    for (int i = 0; i < datalist.size(); ++i) {
			    	String firstname;
			    	String lastname;
			    	String challenge;
			    	String description;
					try {
						firstname = datalist.get(i).get("first_name").toString();
						lastname = datalist.get(i).get("last_name").toString();
						challenge = datalist.get(i).get("challenge").toString();
						description = datalist.get(i).get("description").toString();
						
//						InputStream is = (InputStream) new URL("http://alpha.beatyourbucket.com" + imagelink).getContent();
//				        Drawable d = Drawable.createFromStream(is, "byb");
						
						list.add(firstname + " " + lastname + "         " + challenge + "      " + description);
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			      
			    }
			    final BucketlistAdapter adapter = new BucketlistAdapter(getActivity(),
			            android.R.layout.simple_list_item_1, list);
			        listview.setAdapter(adapter);
			}
		});
		Log.d("aah","aah");
		data.execute();
		
		
		return v;
	}
}
