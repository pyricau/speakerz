package com.devoxx.speakerz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class PrezListActivity extends Activity {

	PrezAdapter adapter;

	ListView prezList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.prez_list);

		adapter = new PrezAdapter(this);
		prezList = (ListView) findViewById(R.id.prez_list);

		prezList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				prezListItemClicked(adapter.getItem(position));
			}

		});

		fillList();
	}

	void fillList() {
		prezList.setAdapter(adapter);
	}

	void prezListItemClicked(Prez prez) {
		Intent intent = new Intent(this, SpeakerzDetailzActivity.class);
		intent.putExtra("title", prez.title);
		intent.putExtra("speaker", prez.speaker);
		intent.putExtra("photoId", prez.photoId);
		startActivity(intent);
	}

}