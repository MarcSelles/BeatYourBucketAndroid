package com.byb.beatyourbucket;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<String> {
	private final Activity context;
	private final String[] itemtext;
	private final Integer[] idOfImg;
	
	public CustomListAdapter(Activity context, String[] itemtext, Integer[] idOfImg) {
		super(context, R.layout.list_layout, itemtext);
		// TODO Auto-generated constructor stub
		
		this.context=context;
		this.itemtext=itemtext;
		this.idOfImg=idOfImg;
	}
	
	public View getView(int position,View view,ViewGroup parent) {
		LayoutInflater inflater=context.getLayoutInflater();
		View viewOfRow=inflater.inflate(R.layout.list_layout, null,true);
		
		TextView textview = (TextView) viewOfRow.findViewById(R.id.textView);
		ImageView imageView = (ImageView) viewOfRow.findViewById(R.id.imageView);
		
		textview.setText(itemtext[position]);
		imageView.setImageResource(idOfImg[position]);
		return viewOfRow;
		
	};
}
