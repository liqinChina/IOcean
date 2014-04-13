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

public class BymeCommentAdapter extends BaseAdapter {

	private Context context;
	private List<Comment> data;
	private LayoutInflater inflater;

	

	public BymeCommentAdapter(Context context, List<Comment> data) {
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

		ImageView imageViewByme;
		TextView textViewBymeName;
		TextView textViewBymeTime;
		
		TextView textViewBymeComment;
		
		TextView textViewBymeUserName;
		TextView textViewBymeUserText;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;
		
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.listitem_byme_comments, null);
			holder  = new ViewHolder();
			
			holder.imageViewByme = (ImageView) convertView.findViewById(R.id.imageView_byme);
			holder.textViewBymeName = (TextView) convertView.findViewById(R.id.textView_byme_name);
			holder.textViewBymeTime = (TextView) convertView.findViewById(R.id.textView_byme_time);
			holder.textViewBymeComment = (TextView) convertView.findViewById(R.id.textView_byme_comment);
		
			holder.textViewBymeUserName = (TextView) convertView.findViewById(R.id.textView_byme_username);
			holder.textViewBymeUserText = (TextView) convertView.findViewById(R.id.textView_byme_usertext);
		
			convertView.setTag(holder);
			
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		//holder.imageViewByme.setImageResource(R.drawable.ic_com_sina_weibo_sdk_logo);
		
		String url = data.get(position).user.profile_image_url;
		Picasso.with(context).load(url).into(holder.imageViewByme);
		
		
		holder.textViewBymeName.setText(data.get(position).user.name);
		holder.textViewBymeTime.setText(data.get(position).created_at);
		holder.textViewBymeComment.setText(data.get(position).text);
		holder.textViewBymeUserName.setText(data.get(position).status.user.name);
		holder.textViewBymeUserText.setText(data.get(position).status.text);
		
		return convertView;
	}

}
