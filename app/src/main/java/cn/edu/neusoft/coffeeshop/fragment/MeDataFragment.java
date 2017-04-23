package cn.edu.neusoft.coffeeshop.fragment;

import java.util.ArrayList;
import java.util.HashMap;

import cn.edu.neusoft.coffeeshop.R;

public class MeDataFragment {

	private static ArrayList<HashMap<String, Object>> list;

	public static ArrayList<HashMap<String, Object>> getItem() {
		list = new ArrayList<HashMap<String, Object>>();

		addItem("我的信息", R.drawable.icon_more_context);
		addItem("我的订单", R.drawable.icon_more_context);
		addItem("我的地址", R.drawable.icon_more_context);
		addItem("我的收藏", R.drawable.icon_more_context);
		addItem("我的礼券", R.drawable.icon_more_context);
		addItem("我的积分", R.drawable.icon_more_context);
		addItem("意见反馈", R.drawable.icon_more_context);
		addItem("客服热线", R.drawable.icon_more_context);
		
		return list;

	}

	public static void addItem(String name, int imageId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("function_name", name);
		map.put("image_id", imageId);
		list.add(map);
	}

}
