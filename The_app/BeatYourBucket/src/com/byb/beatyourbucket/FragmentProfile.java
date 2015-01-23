package com.byb.beatyourbucket;

import java.util.Arrays;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentProfile  extends Fragment {
	private UiLifecycleHelper uiHelper;
	private static final String TAG = "Uitloggen";


	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.tab3_view, container, false);
		
		LoginButton authButton = (LoginButton) v.findViewById(R.id.authButton);
	    authButton.setFragment(this);
	    authButton.setReadPermissions(Arrays.asList("public_profile"));
		
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
	    public void call(final Session session, SessionState state, Exception exception) {
	        onSessionStateChange(session, state, exception);
		    if (session != null && session.isOpened()) {
		        // If the session is open, make an API call to get user data
		        // and define a new callback to handle the response
		    	Log.d("sfa", "sadfm");
		        Request request = Request.newMeRequest(session, new Request.GraphUserCallback() {
		            @Override
		            public void onCompleted(GraphUser user, Response response) {
		                // If the response is successful
		                if (session == Session.getActiveSession()) {
		                    if (user != null) {
		                        String user_ID = user.getId();//user id
		                        String profileName = user.getName();//user's profile name
		                        Log.d("hoi", user_ID);
		                        Log.d("hoi", profileName);
		                    }   
		                }   
		            }   
		        }); 
		        Request.executeBatchAsync(request);
		    }  
	    }
	};
	
	
	
	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
	    if (state.isClosed()) {
	        Log.i(TAG, "Logged out...");
	        Intent intent = new Intent(getActivity(), MainActivity.class);
	        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
	        startActivity(intent);
//	        finish();
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