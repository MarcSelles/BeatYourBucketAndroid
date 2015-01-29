package com.byb.beatyourbucket;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentChallenges extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_challenges, container,
				false);
		// Find the listview of the fragment
		final ListView listview = (ListView) v.findViewById(android.R.id.list);

		// Get the Bucket ID from the shared preferences
		SharedPreferences preferences = getActivity().getSharedPreferences(
				"pref", Context.MODE_PRIVATE);
		String bucketid = preferences.getString("bucketID", null);

		// Set the location of the php file and the POST variables needed for
		// the data
		String location = "challengesforbucketlist.php";
		String key = "bucketlist_id";
		String value = bucketid;

		final GetFromDatabase data = new GetFromDatabase(location, key, value,
				getActivity(), new onLoadingFinishedListener() {
					@Override
					public void onLoadingFinished(ArrayList<JSONObject> datalist) {
						// Initiate the Arraylist of the content of the listview
						final ArrayList<String> list = new ArrayList<String>();
						for (int i = 0; i < datalist.size(); ++i) {
							String title;
							try {
								// Getting the title of the challenge
								title = datalist.get(i).get("title").toString();
								// Add the data to the list
								list.add(title);
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
						// Make and set the adapter to the listview
						final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
								getActivity(),
								android.R.layout.simple_list_item_1, list);
						listview.setAdapter(adapter);
					}
				});
		// Execute the getfromdatabase function
		data.execute();
		return v;
	}
}