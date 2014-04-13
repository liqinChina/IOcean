package com.newer.iocean;

import java.util.List;

import com.sina.weibo.sdk.openapi.models.Comment;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TomeCommentAdapter extends BaseAdapter {

	private Context context;
	private List<Comment> data;
	private LayoutInflater inflater;

	public TomeCommentAdapter(Context context, List<Comment> data) {
		super();
		this.context = context;
		this.data = data;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Comment getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	static class ViewHolder {

		ImageView imageView;
		TextView textViewTomeName;
		TextView textViewTomeTime;
		
		TextView textViewComment;
		
		TextView textViewTomeUserName;
		TextView textViewTomeUserText;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;
		
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.listitem_tome_comments, null);
			holder  = new ViewHolder();
			
			holder.imageView = (ImageView) convertView.findViewById(R.id.imageView_tome);
			holder.textViewTomeName = (TextView) convertView.findViewById(R.id.textView_tome_name);
			holder.textViewTomeTime = (TextView) convertView.findViewById(R.id.textView_tome_time);
			holder.textViewComment = (TextView) convertView.findViewById(R.id.textView_comment);
		
			holder.textViewTomeUserName = (TextView) convertView.findViewById(R.id.textView_tome_username);
			holder.textViewTomeUserText = (TextView) convertView.findViewById(R.id.textView_tome_usertext);
		
			convertView.setTag(holder);
			
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		//holder.imageView.setImageResource(R.drawable.ic_com_sina_weibo_sdk_logo);
		
		String url = data.get(position).user.profile_image_url;
		Picasso.with(context).load(url).into(holder.imageView);
		holder.textViewTomeName.setText(data.get(position).user.name);
		holder.textViewTomeTime.setText(data.get(position).created_at);
		holder.textViewComment.setText(data.get(position).text);
		holder.textViewTomeUserName.setText(data.get(position).status.user.name);
		holder.textViewTomeUserText.setText(data.get(position).status.text);
		
		return convertView;
	}

}
