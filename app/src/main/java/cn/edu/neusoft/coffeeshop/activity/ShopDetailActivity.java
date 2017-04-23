package cn.edu.neusoft.coffeeshop.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.neusoft.coffeeshop.R;
import cn.edu.neusoft.coffeeshop.vo.Shop;

public class ShopDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shop_detail);

		TextView detail = (TextView) findViewById(R.id.shop_detail);
		ImageView img = (ImageView) findViewById(R.id.iv_img_detail);
		Intent intent = this.getIntent();
		Shop shop = (Shop) intent.getSerializableExtra("shop");

		detail.setText("店铺名称：" + shop.getShop_name() + "\n" + "店铺地址："
				+ shop.getShop_address() + "\n" + "店铺电话：" + shop.getTel());
		int pic_id = getResources().getIdentifier(shop.getImg_name(),
				"drawable", ShopDetailActivity.this.getPackageName());
		img.setBackgroundResource(pic_id);
	}
}
