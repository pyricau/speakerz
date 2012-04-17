package com.devoxx.speakerz;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.Fullscreen;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.res.AnimationRes;
import com.googlecode.androidannotations.annotations.res.HtmlRes;

@NoTitle
@Fullscreen
@EActivity(R.layout.speakerz_detailz)
public class SpeakerzDetailzActivity extends Activity {

	@Extra("title")
	String title;

	@Extra("speaker")
	String speaker;

	@Extra("photoId")
	int photoId;

	@AnimationRes
	Animation slideOutToLeft;

	@AnimationRes
	Animation slideInToRight;

	@AnimationRes
	Animation slideOutToBottom;

	@ViewById
	TextView titleTextView;

	@ViewById
	TextView showButton;

	@ViewById
	View timingButton;

	@ViewById
	TextView timingCount;

	@ViewById
	ImageView speakerPhoto;

	@HtmlRes
	CharSequence timingEndText;

	@Click
	void timingButtonClicked() {
		timingButton.startAnimation(slideOutToBottom);
		timingButton.setVisibility(View.GONE);
		doSomethingInBackground();
	}

	@Click({ R.id.showButton, R.id.showButton2 })
	void showHidePhoto() {
		if (showButton.getVisibility() == View.VISIBLE) {
			showButton.startAnimation(slideOutToLeft);
			showButton.setVisibility(View.GONE);
		} else {
			showButton.startAnimation(slideInToRight);
			showButton.setVisibility(View.VISIBLE);
		}
	}

	@AfterViews
	void init() {
		titleTextView.setText(title);
		showButton.setText(speaker);
		speakerPhoto.setImageDrawable(getResources().getDrawable(photoId));
	}

	@Background
	void doSomethingInBackground() {
		for (int i = 5; i > 0; i--) {
			updateTime(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Log.e("Speakerz", "Quelqu'un m'a volé mon slip !", e);
			}
		}
		countDone();
	}

	@UiThread
	void updateTime(int durationInSeconds) {
		timingCount.setText(durationInSeconds + " s");
	}

	@UiThread
	void countDone() {
		timingCount.setText(timingEndText);
	}

}