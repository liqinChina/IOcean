package com.newer.iocean;

import java.util.List;

import com.sina.weibo.sdk.openapi.models.Status;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StatusAdapter extends BaseAdapter{
	
	private Context context;
	private List<Status> data;
	private LayoutInflater layoutInflater;
	
	
	

	public StatusAdapter(Context context, List<Status> data) {		
		this.context = context;
		this.data = data;
		
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Status getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	static class ViewHolder {
		
		
		ImageView imageViewStatus;
		
		TextView textViewStatusName;
		TextView textViewStatusTime;
		TextView textViewStatusText;
		
		//ImageView imageViewStatusSmall;
	
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;
		
		if (convertView == null) {
			//系统服务加载
			convertView = layoutInflater.inflate(R.layout.list_item_statuses_listview, null);
			
			holder = new ViewHolder();
			holder.imageViewStatus = (ImageView) convertView.findViewById(R.id.imageView_status);
			holder.textViewStatusName = (TextView) convertView.findViewById(R.id.textView_status_username);
			holder.textViewStatusTime = (TextView) convertView.findViewById(R.id.textView_status_time);
			holder.textViewStatusText = (TextView) convertView.findViewById(R.id.textView_status_text);
			//holder.imageViewStatusSmall = (ImageView) convertView.findViewById(R.id.imageView_status_small);
			
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		
		/*String url = data.get(position).user.profile_image_url;
		StatusesDao dao = new StatusesDao();
		Bitmap bitmap = dao.getHttpBitmap(url);*/
		
		//holder.imageViewStatus.setImageBitmap(bitmap);
		
		//Picasso
		
		String url = data.get(position).user.profile_image_url;
		Picasso.with(context).load(url).into(holder.imageViewStatus);
		
		
		
		//holder.imageViewStatus.setImageResource(R.drawable.ic_com_sina_weibo_sdk_logo);
		holder.textViewStatusName.setText(data.get(position).user.name);
		holder.textViewStatusTime.setText(data.get(position).created_at);
		holder.textViewStatusText.setText(data.get(position).text);
		
		String urlSmall = data.get(position).thumbnail_pic;
		//Picasso.with(context).load(urlSmall).into(holder.imageViewStatusSmall);
		
		
		return convertView;
	}

}
