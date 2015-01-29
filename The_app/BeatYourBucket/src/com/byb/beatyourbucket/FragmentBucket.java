package com.byb.beatyourbucket;

import java.net.URI;
import java.net.URISyntaxException;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/* Fragment of the Bucketlists tab.
 * Ensures that the bucketlists will show up
 */
public class FragmentBucket extends ListFragment {
	// Make a Hashmap to make sure the right id will be given to the new
	// fragment
	HashMap<String, String> dictionary = new HashMap<String, String>();

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_buckets, container, false);
		// Find the listview of the fragment
		final ListView listview = (ListView) v.findViewById(android.R.id.list);

		// Get the Email Address of the user from the shared preferences
		SharedPreferences preferences = getActivity().getSharedPreferences(
				"pref", Context.MODE_PRIVATE);
		String mail = preferences.getString("mail", null);

		// Set the location of the php file and the POST variables needed for
		// the data
		String location = "bucketlistsforuser.php";
		String key = "email";
		String value = mail;

		final GetFromDatabase data = new GetFromDatabase(location, key, value,
				getActivity(), new onLoadingFinishedListener() {
					@Override
					public void onLoadingFinished(ArrayList<JSONObject> datalist) {
						// Initiate the Arraylists of the content of the
						// listview
						final ArrayList<String> list = new ArrayList<String>();
						final ArrayList<String> urlImages = new ArrayList<String>();
						for (int i = 0; i < datalist.size(); ++i) {
							String title;
							String imagelink;
							String bucketlistid;
							try {
								// Make sure the http is in the right setup to
								// get the image
								URI uri = new URI(
										"http",
										"alpha.beatyourbucket.com",
										datalist.get(i).get("image").toString(),
										null);
								// Get the image link
								imagelink = uri.toString();

								// Getting the title and bucketid from the
								// datalist
								title = datalist.get(i).get("title").toString();
								bucketlistid = datalist.get(i)
										.get("bucketlist_id").toString();

								// Add the data to the lists
								urlImages.add(imagelink);
								list.add(title);
								// Set the id and title as key and value
								dictionary.put(bucketlistid, title);
							} catch (JSONException e) {
								e.printStackTrace();
							} catch (URISyntaxException e) {
								e.printStackTrace();
							}
						}
						// Make and set the adapter to the listview
						final BucketlistAdapter adapter = new BucketlistAdapter(
								getActivity(), R.layout.layout_bucketlist,
								list, urlImages);
						listview.setAdapter(adapter);
						// Make a itemclicklistener when a item is clicked
						listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
							@Override
							public void onItemClick(AdapterView<?> parent,
									final View view, int position, long id) {
								// Set the fragment that must start when clicked
								Fragment fl = new FragmentLists();
								// Get the title of the item
								final String item = (String) parent
										.getItemAtPosition(position);
								for (HashMap.Entry<String, String> entry : dictionary
										.entrySet()) {
									String key = entry.getKey();
									String value = entry.getValue();
									// If the value of the dictionary is the
									// title,
									// then set the key of the dictionary
									// (Bucketid)
									// Inside the shared preferences
									if (value == item) {
										SharedPreferences preferences = getActivity()
												.getSharedPreferences("pref",
														Context.MODE_PRIVATE);
										SharedPreferences.Editor editor = preferences
												.edit();
										editor.putString("bucketID", key);
										editor.commit();
									}
								}
								// Create the fragment
								FragmentManager fm = getFragmentManager();
								FragmentTransaction ft = fm.beginTransaction();
								ft.replace(android.R.id.tabcontent, fl);
								ft.addToBackStack("tag");
								ft.commit();
							}
						});
					}
				});
		// Execute the getfromdatabase function
		data.execute();
		return v;
	}
}
