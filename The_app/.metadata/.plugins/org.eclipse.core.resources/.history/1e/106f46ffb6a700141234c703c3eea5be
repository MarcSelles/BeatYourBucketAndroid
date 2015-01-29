package com.byb.beatyourbucket;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentLists extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// Create the upper tabmenu
		View v = inflater.inflate(R.layout.fragment_challenge_buttons,
				container, false);

		// Create the Challenges fragement
		getFragmentManager().beginTransaction()
				.add(R.id.tabcontent2, new FragmentChallenges()).commit();

		return v;

	}
}
