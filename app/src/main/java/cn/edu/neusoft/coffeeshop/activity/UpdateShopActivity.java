package cn.edu.neusoft.coffeeshop.activity;

import java.io.File;

import cn.edu.neusoft.coffeeshop.R;
import cn.edu.neusoft.coffeeshop.db.DBUtil;
import cn.edu.neusoft.coffeeshop.vo.Shop;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class UpdateShopActivity extends Activity {

	private Button btnOk;
	private EditText shopName, shopAddress, shopTel, imgName;
	private String id;

	private String DB_DIR = "databases";
	private String DB_NAME = "coffeeshop";

	private ApplicationInfo appInfo;
	private DBUtil dbUtil;
	private String databasePath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_shop);

		init(UpdateShopActivity.this);
		dbUtil = DBUtil.getInstance(databasePath);
		dbUtil.openDB();

		btnOk = (Button) findViewById(R.id.btnOK);
		shopName = (EditText) findViewById(R.id.shop_name);
		shopAddress = (EditText) findViewById(R.id.shop_address);
		shopTel = (EditText) findViewById(R.id.shop_tel);
		imgName = (EditText) findViewById(R.id.shop_img_name);

		Intent intent = this.getIntent();
		Shop shop = (Shop) intent.getSerializableExtra("shop");
		id = shop.getShop_id();
		shopName.setText(shop.getShop_name());
		shopAddress.setText(shop.getShop_address());
		shopTel.setText(shop.getTel());
		imgName.setText(shop.getImg_name());

		btnOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Shop shop = new Shop();
				shop.setShop_name(shopName.getText().toString());
				shop.setShop_address(shopAddress.getText().toString());
				shop.setTel(shopTel.getText().toString());
				shop.setImg_name(imgName.getText().toString());

				dbUtil.updateOneData(id, shop);

			}
		});

	}

	private void init(Context context) {
		String packageName = context.getPackageName();
		try {
			appInfo = context.getPackageManager().getApplicationInfo(
					packageName, PackageManager.GET_META_DATA);
			String dbDir = appInfo.dataDir + File.separator + DB_DIR;
			File file = new File(dbDir);
			if (!file.exists()) {
				file.mkdir();
			}
			databasePath = appInfo.dataDir + File.separator + DB_DIR
					+ File.separator + DB_NAME;

		} catch (NameNotFoundException e) {

		}

	}

	public void back(View v) {
		finish();
	}

}
