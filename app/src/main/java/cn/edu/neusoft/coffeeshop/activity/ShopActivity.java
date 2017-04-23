package cn.edu.neusoft.coffeeshop.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.neusoft.coffeeshop.R;
import cn.edu.neusoft.coffeeshop.db.DBUtil;
import cn.edu.neusoft.coffeeshop.vo.Shop;

public class ShopActivity extends Activity {

	private ArrayList<HashMap<String, Object>> data;
	private ListView listView;
	private final String DB_DIR = "databases";
	private final String DB_NAME = "coffeeshop";
	private String databasePath;
	private ApplicationInfo appInfo;
	private DBUtil dbUtil;
	private Shop[] shops;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shop_list);

		listView = (ListView) findViewById(R.id.lv_shop_list);
		init(this);
		dbUtil = DBUtil.getInstance(databasePath);
		dbUtil.openDB();
		showShops();
		registerForContextMenu(listView); // 注册菜单信息
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		showShops();
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
			e.printStackTrace();
		}
	}

	private void showShops() {
		shops = dbUtil.queryAllShops();
		data = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < shops.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("shop_name", shops[i].getShop_name());
			map.put("shop_address", shops[i].getShop_address());
			map.put("tel", shops[i].getTel());
			String pic_name = shops[i].getImg_name();
			int pic_id = getResources().getIdentifier(pic_name, "drawable",
					ShopActivity.this.getPackageName());
			map.put("pic_id", pic_id + "");
			data.add(map);
		}

		MyAdapter adapter = new MyAdapter(ShopActivity.this, data,
				R.layout.item_listview_shop, new String[] { "shop_name",
						"shop_address", "tel", "pic_id" }, new int[] {
						R.id.shop_name, R.id.shop_address, R.id.shop_tel,
						R.id.iv_img });

		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent intent = new Intent();
				intent.setClass(ShopActivity.this, ShopDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("shop", shops[position]);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

	class MyAdapter extends SimpleAdapter {

		public MyAdapter(Context context, List<? extends Map<String, ?>> data,
				int resource, String[] from, int[] to) {
			super(context, data, resource, from, to);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return super.getView(position, convertView, parent);
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.shop_manager, menu);

		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		final int index = (int) info.id;
		switch (item.getItemId()) {
		case R.id.update:
			Intent updateIntent = new Intent();
			updateIntent.setClass(ShopActivity.this, UpdateShopActivity.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable("shop", shops[index]);
			updateIntent.putExtras(bundle);
			startActivity(updateIntent);
			break;

		case R.id.add:
			Intent addIntent = new Intent();
			addIntent.setClass(ShopActivity.this, AddShopActivity.class);
			addIntent.putExtra("shop_id", shops[index].getShop_id());
			startActivity(addIntent);

		case R.id.delete:
			new AlertDialog.Builder(ShopActivity.this).setMessage("确定删除此店铺？")
					.setPositiveButton("否", new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

						}
					}).setNegativeButton("是", new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							String id = shops[index].getShop_id();
							dbUtil.deleteOneData(id);
							Intent delete = new Intent();
							delete.setClass(ShopActivity.this,
									ShopActivity.class);
							startActivity(delete);
						}
					}).show();
		default:
			break;
		}

		return super.onContextItemSelected(item);
	}
}
