package com.byb.beatyourbucket;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/* Adapter for setting the data of a Bucketlist
 * This class is used for showing the bucketlists and score*/
public class BucketlistAdapter extends ArrayAdapter<String> {
	private final Activity context;
	private final List<String> objects;
	private final List<String> imageURL;

	public BucketlistAdapter(Activity context, int viewresourseId,
			List<String> objects, List<String> imageURL) {
		super(context, viewresourseId, objects);
		this.context = context;
		this.objects = objects;
		this.imageURL = imageURL;
	}

	@Override
	public View getView(int position, View convertview, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		View viewOfRow = inflater.inflate(R.layout.layout_bucketlist, parent,
				false);
		// Find the textview and the imageview of the item in the list
		TextView textview = (TextView) viewOfRow.findViewById(R.id.textView);
		ImageView imageView = (ImageView) viewOfRow
				.findViewById(R.id.imageView);
		// Set the textview and the imageview of the item in the list
		textview.setText(objects.get(position));
		Picasso.with(context).load(imageURL.get(position)).fit().centerCrop()
				.placeholder(R.drawable.rsz_1rsz_logo_byb_transparent)
				.into(imageView);
		return viewOfRow;

	}

}
