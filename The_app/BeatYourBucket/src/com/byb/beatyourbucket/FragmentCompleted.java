package com.byb.beatyourbucket;

import java.net.URI;
import java.net.URISyntaxException;
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
import android.widget.ListView;

/* Fragment of the Completed challenges tab.
 * Ensures that the challenges will show up
 * which are completed by one of the bucketlist members
 */
public class FragmentCompleted extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater
				.inflate(R.layout.fragment_completed, container, false);
		// Find the listview of the fragment
		final ListView listview = (ListView) v.findViewById(android.R.id.list);

		// Get the Bucket ID from the shared preferences
		SharedPreferences preferences = getActivity().getSharedPreferences(
				"pref", Context.MODE_PRIVATE);
		String bucketid = preferences.getString("bucketID", null);

		// Set the location of the php file and the POST variables needed for
		// the data
		String location = "completedchallengesforbucketlist.php";
		String key = "bucketlist_id";
		String value = bucketid;

		final GetFromDatabase data = new GetFromDatabase(location, key, value,
				getActivity(), new onLoadingFinishedListener() {
					@Override
					public void onLoadingFinished(ArrayList<JSONObject> datalist) {
						// Initiate the Arraylists of the content of the
						// listview
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
								// Make sure the http is in the right setup to
								// get the image
								URI uri = new URI("http",
										"alpha.beatyourbucket.com", datalist
												.get(i).get("url").toString(),
										null);
								// Get the image link
								imagelink = uri.toString();

								// Getting the right content from the datalist
								firstname = datalist.get(i).get("first_name")
										.toString();
								lastname = datalist.get(i).get("last_name")
										.toString();
								challenge = datalist.get(i).get("challenge")
										.toString();
								description = datalist.get(i)
										.get("description").toString();

								// Add the data to the lists
								namelist.add(firstname + " " + lastname);
								urlImages.add(imagelink);
								infolist.add(challenge + "      " + description);
							} catch (JSONException e) {
								e.printStackTrace();
							} catch (URISyntaxException e) {
								e.printStackTrace();
							}
						}
						// Make and set the adapter to the listview
						final NewsAdapter adapter = new NewsAdapter(
								getActivity(), R.layout.layout_bucketlist,
								namelist, urlImages, infolist);
						listview.setAdapter(adapter);
					}
				});
		// Execute the getfromdatabase function
		data.execute();
		return v;
	}
}
