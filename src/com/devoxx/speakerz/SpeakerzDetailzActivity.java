package com.devoxx.speakerz;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SpeakerzDetailzActivity extends Activity {

	String title;

	String speaker;

	int photoId;

	Animation slideOutToLeft;

	Animation slideInToRight;

	Animation slideOutToBottom;

	CharSequence timingEndText;

	TextView titleTextView;

	TextView showButton;

	View timingButton;

	TextView timingCount;

	ImageView speakerImageView;

	// Ma longue méthode d'init
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Copy & paste from da web
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// Les strings, c'est extra
		Intent intent = getIntent();
		title = intent.getStringExtra("title");
		speaker = intent.getStringExtra("speaker");
		photoId = intent.getIntExtra("photoId", -1);

		slideOutToBottom = AnimationUtils.loadAnimation(this, R.anim.slide_out_to_bottom);
		slideInToRight = AnimationUtils.loadAnimation(this, R.anim.slide_in_to_right);
		slideOutToLeft = AnimationUtils.loadAnimation(this, R.anim.slide_out_to_left);

		timingEndText = Html.fromHtml(getString(R.string.timing_end_text));

		setContentView(R.layout.speakerz_detailz);

		// Tu pars du Nord Ouest pour aller au Sud Est, et tu cast !
		titleTextView = (TextView) findViewById(R.id.titleTextView);
		showButton = (TextView) findViewById(R.id.showButton);
		timingButton = findViewById(R.id.timingButton);
		timingCount = (TextView) findViewById(R.id.timingCount);
		speakerImageView = (ImageView) findViewById(R.id.speakerPhoto);

		// You listening to me?
		timingButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				timingButtonClicked();
			}

		});

		showButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				showHidePhoto();
			}

		});

		findViewById(R.id.showButton2).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				showHidePhoto();
			}

		});

		init();
	}

	void timingButtonClicked() {
		timingButton.startAnimation(slideOutToBottom);
		timingButton.setVisibility(View.GONE);
		doSomethingInBackground();
	}

	void showHidePhoto() {
		if (showButton.getVisibility() == View.VISIBLE) {
			showButton.startAnimation(slideOutToLeft);
			showButton.setVisibility(View.GONE);
		} else {
			showButton.startAnimation(slideInToRight);
			showButton.setVisibility(View.VISIBLE);
		}
	}

	void init() {
		titleTextView.setText(title);
		showButton.setText(speaker);
		speakerImageView.setImageDrawable(getResources().getDrawable(photoId));
	}

	void doSomethingInBackground() {

		// I CAN HAZ GENERIC PARAMZ?
		AsyncTask<Void, Integer, Void> task = new AsyncTask<Void, Integer, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				for (int i = 5; i > 0; i--) {
					publishProgress(i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						Log.e("Speakerz", "Quelqu'un m'a volé mon slip !", e);
					}
				}
				return null;
			}

			@Override
			protected void onProgressUpdate(Integer... values) {
				Integer durationInSeconds = values[0];
				updateTime(durationInSeconds);
			}

			@Override
			protected void onPostExecute(Void result) {
				countDone();
			}

		};

		task.execute();
	}

	void updateTime(int durationInSeconds) {
		timingCount.setText(durationInSeconds + " s");
	}

	void countDone() {
		timingCount.setText(timingEndText);
	}

}