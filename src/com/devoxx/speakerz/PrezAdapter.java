package com.devoxx.speakerz;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PrezAdapter extends BaseAdapter {

	private static class ViewHolder {

		public final TextView prezTitle;
		public final ImageView prezPhoto;

		public ViewHolder(TextView prezTitle, ImageView prezPhoto) {
			this.prezTitle = prezTitle;
			this.prezPhoto = prezPhoto;
		}
	}

	Context context;

	String[] prezTitles;

	String[] prezSpeakers;

	TypedArray prezPhotoIds;

	public PrezAdapter(Context context) {
		this.context = context;
		initAdapter();
	}

	void initAdapter() {
		Resources resources = context.getResources();
		prezTitles = resources.getStringArray(R.array.prezTitles);
		prezSpeakers = resources.getStringArray(R.array.prezSpeakers);
		prezPhotoIds = resources.obtainTypedArray(R.array.prezPhotoIds);
	}

	@Override
	public int getCount() {
		return prezTitles.length;
	}

	@Override
	public Prez getItem(int position) {
		return new Prez(prezTitles[position], prezSpeakers[position], prezPhotoIds.getResourceId(position, -1));
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.prez_list_item, null);
			TextView prezTitle = (TextView) convertView.findViewById(R.id.prezTitle);
			ImageView prezPhoto = (ImageView) convertView.findViewById(R.id.prezPhoto);
			viewHolder = new ViewHolder(prezTitle, prezPhoto);
			convertView.setTag(viewHolder);
		} else
			viewHolder = (ViewHolder) convertView.getTag();

		viewHolder.prezTitle.setText(prezTitles[position]);
		Drawable photo = prezPhotoIds.getDrawable(position);
		viewHolder.prezPhoto.setImageDrawable(photo);

		return convertView;
	}

}
