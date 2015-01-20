package com.byb.beatyourbucket;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class FragmentChallenges extends ListFragment implements OnItemClickListener {
	private static final String TAG = "MyActivity";
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_completing, container, false);
		return v;
	}
	
	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
     
        super.onActivityCreated(savedInstanceState);
        Log.v(TAG, "hoi");
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.challenges, android.R.layout.simple_list_item_1);
       
        setListAdapter(adapter);
        getListView();
//        .setOnItemClickListener(this);

    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		Fragment fl = new FragmentCompleting();
		ft.replace(android.R.id.tabcontent, fl);
		ft.addToBackStack( "tag" );
//		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//		FragmentLists fl = new FragmentLists();
//		fragmentTransaction.replace(android.R.id.tabcontent, fl);
		ft.commit();
	}
}