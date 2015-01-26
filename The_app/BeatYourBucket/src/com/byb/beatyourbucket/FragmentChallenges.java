package com.byb.beatyourbucket;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class FragmentChallenges extends ListFragment {
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_challenges, container, false);
		
		SharedPreferences preferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
		String bucketid = preferences.getString("bucketID", "empty");
		Log.d("ID",bucketid);
		
		final ListView listview = (ListView) v.findViewById(android.R.id.list);
		
		String location = "challengesforbucketlist.php";
		String key = "bucketlist_id";
		String value = bucketid;
		
		final GetFromDatabase data = new GetFromDatabase(location,key, value, new onLoadingFinishedListener() {
			
			@Override
			public void onLoadingFinished(ArrayList<JSONObject> datalist) {
				// TODO Auto-generated method stub

			    final ArrayList<String> list = new ArrayList<String>();
			    for (int i = 0; i < datalist.size(); ++i) {
			    	Log.d("data", datalist.toString());
			    	String title;
					try {
						title = datalist.get(i).get("title").toString();
						
						
//						InputStream is = (InputStream) new URL("http://alpha.beatyourbucket.com" + imagelink).getContent();
//				        Drawable d = Drawable.createFromStream(is, "byb");
						
						list.add(title);
						
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
							Log.v("das", "hoiiiii");
							// TODO Auto-generated method stub
							FragmentManager fm = getFragmentManager();
							FragmentTransaction ft = fm.beginTransaction();
							Fragment fl = new FragmentCompleting();
							ft.replace(android.R.id.tabcontent, fl);
							ft.addToBackStack( "tag" );
					//		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
					//		FragmentLists fl = new FragmentLists();
					//		fragmentTransaction.replace(android.R.id.tabcontent, fl);
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
	
