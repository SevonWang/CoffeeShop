package cn.edu.neusoft.coffeeshop.activity;

import java.io.File;

import cn.edu.neusoft.coffeeshop.R;
import cn.edu.neusoft.coffeeshop.db.DBUtil;
import cn.edu.neusoft.coffeeshop.vo.Shop;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddShopActivity extends Activity {

	private Button add_btnOk;
	private EditText shop_id, shop_name, shop_address, shop_tel, img_name;

	private String DB_DIR = "databases";
	private String DB_NAME = "coffeeshop";

	private ApplicationInfo appInfo;
	private DBUtil dbUtil;
	private String databasePath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_shop);

		init(AddShopActivity.this);
		dbUtil = DBUtil.getInstance(databasePath);
		dbUtil.openDB();

		add_btnOk = (Button) findViewById(R.id.add_btnOK);
		shop_id = (EditText) findViewById(R.id.add_shop_id);
		shop_name = (EditText) findViewById(R.id.add_shop_name);
		shop_address = (EditText) findViewById(R.id.add_shop_address);
		shop_tel = (EditText) findViewById(R.id.add_shop_tel);
		img_name = (EditText) findViewById(R.id.add_shop_img_name);

		add_btnOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Shop shop = new Shop();
				shop.setShop_id(shop_id.getText().toString());
				shop.setShop_name(shop_name.getText().toString());
				shop.setShop_address(shop_address.getText().toString());
				shop.setTel(shop_tel.getText().toString());
				shop.setImg_name(img_name.getText().toString());
				dbUtil.insertOneData(shop);
				Toast.makeText(AddShopActivity.this, "店铺已添加", 2000).show();
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

	public void back(View view) {
		finish();
	}
}
