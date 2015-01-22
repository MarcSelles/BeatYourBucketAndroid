package com.byb.beatyourbucket;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




public class FragmentBucket  extends Fragment {
	// JSON node keys
	private static final String TAG_TITLE = "title";
	private static final String TAG_IMAGE = "image";
	

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.view_buckets, container, false);
		
		Log.d("execute", "start");
		new GetFromDatabase().execute();	
		
		return v;
	}
	
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//			if(value.getText().toString().length()<1){
//				
//				// out of range
//				
//
//			}else{
//					
//			}
//		
// 
//	} 
	
	
	
//	@Override
//	public void onItemClick(AdapterView<?> parent, View view, int position,
//			long id) {
//		Log.v(TAG, "hoiiiii");
//		// TODO Auto-generated method stub
//		FragmentManager fm = getFragmentManager();
//		FragmentTransaction ft = fm.beginTransaction();
//		Fragment fl = new FragmentLists();
//		ft.replace(android.R.id.tabcontent, fl);
//		ft.addToBackStack( "tag" );
////		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
////		FragmentLists fl = new FragmentLists();
////		fragmentTransaction.replace(android.R.id.tabcontent, fl);
//		ft.commit();
//	}

}