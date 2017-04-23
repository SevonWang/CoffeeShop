package cn.edu.neusoft.coffeeshop.viewflow;

import java.util.zip.Inflater;

import cn.edu.neusoft.coffeeshop.R;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	private Context context;
	private Fragment iFragment;
	private LayoutInflater inflater;
	private static final int[] ids = { R.drawable.img_viewflow1,
			R.drawable.img_viewflow2, R.drawable.img_viewflow3 };

	public ImageAdapter(Context context) {
		this.context = context;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	public Object getItem(int position) {
		return ids[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.img_item_view_flow, null);
		}

		((ImageView) convertView.findViewById(R.id.imgItem))
				.setImageResource(ids[position % ids.length]);

		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(context, DetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putInt("img_id", ids[position % ids.length]);
				intent.putExtras(bundle);
				context.startActivity(intent);

			}
		});

		return convertView;
	}

}
