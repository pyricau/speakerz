package com.devoxx.speakerz;

import android.app.Activity;
import android.widget.ListView;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Fullscreen;
import com.googlecode.androidannotations.annotations.ItemClick;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.googlecode.androidannotations.annotations.ViewById;

@NoTitle
@Fullscreen
@EActivity(R.layout.prez_list)
public class PrezListActivity extends Activity {

	@Bean
	PrezAdapter adapter;

	@ViewById
	ListView prezList;

	@AfterViews
	void fillList() {
		prezList.setAdapter(adapter);
	}

	@ItemClick
	void prezListItemClicked(Prez prez) {
		SpeakerzDetailzActivity_ //
				.intent(this) //
				.title(prez.title) //
				.speaker(prez.speaker) //
				.photoId(prez.photoId) //
				.start();
	}

}