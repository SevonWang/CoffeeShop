package cn.edu.neusoft.coffeeshop.db;

import cn.edu.neusoft.coffeeshop.vo.Coffee;
import cn.edu.neusoft.coffeeshop.vo.Shop;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBUtil {

	private final static String DB_NAME = "coffeeshop";
	private final static int DB_VERSION = 1;

	private final static String TABLE_SHOP = "shop";
	private final static String TABLE_COFFEE = "recom_coffee";
	private final static String KEY_ID = "shop_id";
	private final static String KEY_NAME = "shop_name";
	private final static String KEY_ADDRESS = "shop_address";
	private final static String KEY_TEL = "tel";
	private final static String KEY_IMG_NAME = "img_name";

	private String databasePath;
	private SQLiteDatabase database;

	private static DBUtil dbUtil;

	private DBUtil(String databasePath) {

	}

	public static DBUtil getInstance(String databasePath) {

		if (dbUtil == null) {
			dbUtil = new DBUtil(databasePath);
		}
		dbUtil.databasePath = databasePath;
		return dbUtil;
	}

	public int openDB() {
		try {
			if (database == null || !database.isOpen()) {
				database = SQLiteDatabase.openDatabase(databasePath, null,
						SQLiteDatabase.OPEN_READWRITE);
			}
		} catch (Exception e) {
			return -1;
		}
		return 0;
	}

	public Coffee[] queryRecommandCoffee() {
		Cursor result = database.query(TABLE_COFFEE, null, null, null, null,
				null, null);
		return ConvertToCoffee(result);
	}

	public Shop[] queryAllShops() {
		Cursor result = database.query(TABLE_SHOP, null, null, null, null,
				null, null);
		return ConvertToShop(result);
	}

	private Shop[] ConvertToShop(Cursor cursor) {
		int resultCounts = cursor.getCount();
		if (resultCounts == 0 || !cursor.moveToFirst()) {
			return null;
		}

		Shop[] shops = new Shop[resultCounts];
		for (int i = 0; i < resultCounts; i++) {
			shops[i] = new Shop();
			shops[i].setShop_id(cursor.getString(0));
			shops[i].setShop_name(cursor.getString(cursor
					.getColumnIndex("shop_name")));
			shops[i].setShop_address(cursor.getString(2));
			shops[i].setTel(cursor.getString(3));
			shops[i].setImg_name(cursor.getString(4));
			shops[i].setImg_id(cursor.getInt(5));
			cursor.moveToNext();
		}

		return shops;
	}

	private Coffee[] ConvertToCoffee(Cursor cursor) {
		int resultCounts = cursor.getCount();
		if (resultCounts == 0 || !cursor.moveToFirst()) {
			return null;
		}

		Coffee[] coffees = new Coffee[resultCounts];

		for (int i = 0; i < resultCounts; i++) {
			coffees[i] = new Coffee();
			coffees[i].setCoffee_id(cursor.getInt(0));
			coffees[i].setCoffee_name(cursor.getString(cursor
					.getColumnIndex("coffee_name")));
			coffees[i].setCoffee_price(cursor.getInt(2));
			coffees[i].setCoffee_info(cursor.getString(3));
			coffees[i].setCoffee_comment(cursor.getString(4));
			coffees[i].setImage_name(cursor.getString(5));
			cursor.moveToNext();
		}

		return coffees;
	}

	public long updateOneData(String id, Shop shop) {
		ContentValues values = new ContentValues();

		values.put(KEY_NAME, shop.getShop_name());
		values.put(KEY_ADDRESS, shop.getShop_address());
		values.put(KEY_TEL, shop.getTel());
		values.put(KEY_IMG_NAME, shop.getImg_name());

		return database.update(TABLE_SHOP, values, KEY_ID + "=" + "'" + id
				+ "'", null);
	}

	public long insertOneData(Shop shop) {

		ContentValues values = new ContentValues();

		values.put(KEY_ID, shop.getShop_id());
		values.put(KEY_NAME, shop.getShop_name());
		values.put(KEY_ADDRESS, shop.getShop_address());
		values.put(KEY_TEL, shop.getTel());
		values.put(KEY_IMG_NAME, shop.getImg_name());

		return database.insert(TABLE_SHOP, null, values);
	}

	public long deleteOneData(String id) {

		return database.delete(TABLE_SHOP, "shop_id" + "=" + "'" + id + "'",
				null);
	}
}
