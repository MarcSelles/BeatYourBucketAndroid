package com.byb.beatyourbucket;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.squareup.picasso.Picasso;

public class FragmentProfile extends Fragment {
	private UiLifecycleHelper uiHelper;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_profile, container, false);
		// Initiate the Facebook logout button
		LoginButton authButton = (LoginButton) v.findViewById(R.id.authButton);
		authButton.setFragment(this);

		// Find the imageview and textview of the fragment
		ImageView userpicture = (ImageView) v.findViewById(R.id.imageView);
		TextView textview = (TextView) v.findViewById(R.id.nameview);

		// Get the name and ID of user from the shared preferences
		SharedPreferences preferences = getActivity().getSharedPreferences(
				"pref", Context.MODE_PRIVATE);
		String username = preferences.getString("profileName", null);
		String userID = preferences.getString("user_ID", null);

		// Set the name of the user in the textview
		textview.setText(username);

		// Set the image of the user in the imageview
		String img_value = null;
		img_value = new String("https://graph.facebook.com/" + userID
				+ "/picture?type=large");
		Picasso.with(getActivity()).load(img_value).fit().centerCrop()
				.into(userpicture);
		return v;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		uiHelper = new UiLifecycleHelper(getActivity(), callback);
		uiHelper.onCreate(savedInstanceState);
	}

	private Session.StatusCallback callback = new Session.StatusCallback() {
		@Override
		public void call(final Session session, SessionState state,
				Exception exception) {
			onSessionStateChange(session, state, exception);
			if (session != null && session.isOpened()) {
				// If the session is open, make an API call to get user data
				// and define a new callback to handle the response
				Log.d("sfa", "sadfm");
				Request request = Request.newMeRequest(session,
						new Request.GraphUserCallback() {
							@Override
							public void onCompleted(GraphUser user,
									Response response) {
								// If the response is successful
								if (session == Session.getActiveSession()) {
									if (user != null) {
									}
								}
							}
						});
				Request.executeBatchAsync(request);
			}
		}
	};

	private void onSessionStateChange(Session session, SessionState state,
			Exception exception) {
		// When logged out, return to the main activity
		if (state.isClosed()) {
			Intent intent = new Intent(getActivity(), MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
					| Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(intent);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		uiHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
		super.onPause();
		uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		uiHelper.onSaveInstanceState(outState);
	}
}