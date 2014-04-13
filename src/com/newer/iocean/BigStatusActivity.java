package com.newer.iocean;

import com.squareup.picasso.Picasso;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class BigStatusActivity extends ActionBarActivity {

	private ActionBar actionBar;

	private static final String TAG = "BigStatusActivity";
	private ImageView imageView;
	private TextView textViewName;
	private TextView textViewTime;
	private TextView textViewText;
	private ImageView imageViewBig;
	private TextView textViewRepost;
	private TextView textViewComment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_big_status);

		imageView = (ImageView) findViewById(R.id.imageView_big);
		textViewName = (TextView) findViewById(R.id.textView_big_name);
		textViewTime = (TextView) findViewById(R.id.textView_big_time);
		textViewText = (TextView) findViewById(R.id.textView_big_text);
		imageViewBig = (ImageView) findViewById(R.id.imageView_big_big);
		textViewRepost = (TextView) findViewById(R.id.textView_big_repost);
		textViewComment = (TextView) findViewById(R.id.textView_big_comment);

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		//
		Log.d(TAG, bundle.toString());

		String image = bundle.getString("image");
		String name = bundle.getString("name");
		String time = bundle.getString("time");
		String text = bundle.getString("text");
		String imageBig = bundle.getString("imageBig");
		int repost = bundle.getInt("repost");
		int comment = bundle.getInt("comment");

		textViewName.setText(name);
		textViewTime.setText(time);
		textViewText.setText(text);

		textViewRepost.setText(String.valueOf(repost));
		textViewComment.setText(String.valueOf(comment));

		Picasso.with(this).load(image).into(imageView);
		// Picasso.with(this).load(imageBig).into(imageViewBig);

		actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.menu_big_status, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item_store:

			break;
		case R.id.item_comment:

			break;
		case R.id.item_repost:

			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
