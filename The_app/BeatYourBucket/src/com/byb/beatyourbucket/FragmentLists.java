package com.byb.beatyourbucket;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentLists extends Fragment {
	 private FragmentTabHost mTabHost;
	 
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		// Make the tabmenu
		
		View v = inflater.inflate(R.layout.fragment_lists, container, false);
		
//		mTabHost = (FragmentTabHost)v.findViewById(android.R.id.tabhost);
//        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);
//
//        mTabHost.addTab(mTabHost.newTabSpec("Challenges").setIndicator("Challenges"),
//                FragmentChallenges.class, null);
//        mTabHost.addTab(mTabHost.newTabSpec("Voltooid").setIndicator("Voltooid"),
//                FragmentCompleted.class, null);
//        mTabHost.addTab(mTabHost.newTabSpec("Stand").setIndicator("Stand"),
//                FragmentScore.class, null);
//        mTabHost.setCurrentTabByTag("Challenges");
		
		return v;
	}
	
	
}
