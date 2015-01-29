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

public class FragmentScore extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_score, container, false);
		// Find the listviews of the fragment
		final ListView listview = (ListView) v.findViewById(android.R.id.list);
		final ListView scoreview = (ListView) v.findViewById(R.id.scorelist);

		// Get the Bucket ID from the shared preferences
		SharedPreferences preferences = getActivity().getSharedPreferences(
				"pref", Context.MODE_PRIVATE);
		String bucketid = preferences.getString("bucketID", null);

		// Set the location of the php file and the POST variables needed for
		// the data
		String location = "usersforbucketlist.php";
		String key = "bucketlist_id";
		String value = bucketid;

		final GetFromDatabase data = new GetFromDatabase(location, key, value,
				getActivity(), new onLoadingFinishedListener() {
					@Override
					public void onLoadingFinished(ArrayList<JSONObject> datalist) {
						// Initiate the Arraylists of the content of the
						// listviews
						final ArrayList<String> names = new ArrayList<String>();
						final ArrayList<String> scorelist = new ArrayList<String>();
						for (int i = 0; i < datalist.size(); ++i) {
							String name;
							String number;
							String score;
							try {
								// Getting the score and name of the users of
								// the bucket
								score = datalist.get(i).get("points")
										.toString();
								number = String.valueOf(i + 1);
								name = datalist.get(i).get("first_name")
										.toString()
										+ " "
										+ datalist.get(i).get("last_name")
												.toString();
								// Add the data to the lists
								names.add(number + ". " + name);
								scorelist.add("Score: " + score);
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
						// Make and set the adapters to the listviews
						final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
								getActivity(),
								android.R.layout.simple_list_item_1, names);
						listview.setAdapter(adapter);

						final ArrayAdapter<String> adapterscore = new ArrayAdapter<String>(
								getActivity(),
								android.R.layout.simple_list_item_1, scorelist);
						scoreview.setAdapter(adapterscore);
					}
				});
		// Execute the getfromdatabase function
		data.execute();
		return v;
	}
}
