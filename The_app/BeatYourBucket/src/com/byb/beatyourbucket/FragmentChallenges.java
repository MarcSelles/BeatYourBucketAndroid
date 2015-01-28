package com.byb.beatyourbucket;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentChallenges extends Fragment {
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
			final ArrayList<String> list = new ArrayList<String>();
			for (int i = 0; i < datalist.size(); ++i) {
			   	String title;
				try {
					title = datalist.get(i).get("title").toString();
					list.add(title);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
			        android.R.layout.simple_list_item_1, list);
			listview.setAdapter(adapter);
			listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			    @Override
			    public void onItemClick(AdapterView<?> parent, final View view,
			    		int position, long id) {
					FragmentManager fm = getFragmentManager();
					FragmentTransaction ft = fm.beginTransaction();
					Fragment fl = new FragmentCompleting();
					ft.replace(android.R.id.tabcontent, fl);
					ft.addToBackStack( "tag" );
					ft.commit();
					}
				});
			}
		});
		data.execute();
		return v;
	}
}