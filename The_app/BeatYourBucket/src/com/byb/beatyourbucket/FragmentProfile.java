package com.byb.beatyourbucket;

import com.facebook.Session;
import com.facebook.SessionState;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentProfile  extends MainFragment {
	private static final String TAG = "Uitloggen";


	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.tab3_view, container, false);
		return v;
	}
	
	
}