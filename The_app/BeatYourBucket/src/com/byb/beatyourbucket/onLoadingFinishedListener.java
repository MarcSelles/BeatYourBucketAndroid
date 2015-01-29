package com.byb.beatyourbucket;

import java.util.ArrayList;

import org.json.JSONObject;

/* Ensures that the data will return only when completed*/
public interface onLoadingFinishedListener {
	public void onLoadingFinished(ArrayList<JSONObject> list);
}
