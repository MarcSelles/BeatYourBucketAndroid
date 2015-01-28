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
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_score, container, false);

		SharedPreferences preferences = getActivity().getSharedPreferences(
				"pref", Context.MODE_PRIVATE);
		String bucketid = preferences.getString("bucketID", "empty");

		final ListView listview = (ListView) v.findViewById(android.R.id.list);
		final ListView scoreview = (ListView) v.findViewById(R.id.scorelist);

		String location = "usersforbucketlist.php";
		String key = "bucketlist_id";
		String value = bucketid;

		final GetFromDatabase data = new GetFromDatabase(location, key, value,
				new onLoadingFinishedListener() {

					@Override
					public void onLoadingFinished(ArrayList<JSONObject> datalist) {
						final ArrayList<String> names = new ArrayList<String>();
						final ArrayList<String> scorelist = new ArrayList<String>();
						for (int i = 0; i < datalist.size(); ++i) {
							String name;
							String number;
							String score;
							try {
								score = datalist.get(i).get("points")
										.toString();
								number = String.valueOf(i + 1);
								name = datalist.get(i).get("first_name")
										.toString()
										+ " "
										+ datalist.get(i).get("last_name")
												.toString();
								names.add(number + ". " + name);
								scorelist.add("Score: " + score);
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}

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
		data.execute();

		return v;
	}
}
