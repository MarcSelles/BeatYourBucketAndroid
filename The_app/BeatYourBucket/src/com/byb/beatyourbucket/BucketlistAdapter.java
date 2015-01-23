package com.byb.beatyourbucket;

import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.content.Context;
import android.widget.ArrayAdapter;

public class BucketlistAdapter extends ArrayAdapter<String>{
	HashMap<String, Integer> hmID = new HashMap<String, Integer>();
	public BucketlistAdapter(Context context, int viewresourseId, List<String> objects) {
		// TODO Auto-generated constructor stub
		super (context, viewresourseId, objects);
		
		for (int i = 0; i < objects.size(); ++i) {
			hmID.put(objects.get(i).toString(), i);
	      }
	}
	
	@Override
    public long getItemId(int position) {
      String item = getItem(position).toString();
      return hmID.get(item);
    }

    @Override
    public boolean hasStableIds() {
      return true;
    }
}