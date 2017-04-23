package cn.edu.neusoft.coffeeshop.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.taptwo.android.widget.CircleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.edu.neusoft.coffeeshop.R;
import cn.edu.neusoft.coffeeshop.activity.ShopActivity;
import cn.edu.neusoft.coffeeshop.db.DBUtil;
import cn.edu.neusoft.coffeeshop.viewflow.ImageAdapter;
import cn.edu.neusoft.coffeeshop.vo.Coffee;

public class HomeFragment extends Fragment {

	private ViewFlow viewFlow;
	private CircleFlowIndicator flowIndicator;

	private GridView gridView;
	private final String DB_DIR = "databases";
	private final String DB_NAME = "coffeeshop";
	private DBUtil dbUtil;
	private String databasePath;
	private ApplicationInfo appInfo;

	private LinearLayout shopLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.home_fragment, null);

		viewFlow = (ViewFlow) view.findViewById(R.id.viewFlow);
		viewFlow.setAdapter(new ImageAdapter(HomeFragment.this.getActivity()));
		viewFlow.setmSideBuffer(3);

		flowIndicator = (CircleFlowIndicator) view
				.findViewById(R.id.flowInficator);
		viewFlow.setFlowIndicator(flowIndicator);
		viewFlow.setTimeSpan(4000);
		viewFlow.setSelection(3 * 1000);
		viewFlow.startAutoFlowTimer();

		gridView = (GridView) view.findViewById(R.id.grid_rmd_goods);

		shopLayout = (LinearLayout) view.findViewById(R.id.ll_module1);
		shopLayout.setOnClickListener(new MyShopClickListener());

		init(this.getActivity());
		initDB();

		return view;
	}

	class MyShopClickListener implements OnClickListener {
		Intent intent = new Intent();

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.ll_module1:
				Toast.makeText(HomeFragment.this.getActivity(), "进入店铺",
						Toast.LENGTH_LONG).show();
				intent.setClass(HomeFragment.this.getActivity(),
						ShopActivity.class);
				startActivity(intent);
				break;

			default:
				break;
			}
		}
	}

	private void initDB() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					InputStream is = HomeFragment.this.getActivity()
							.getAssets().open("coffeeshop.sqlite");

					if (databasePath != null) {

						File file = new File(databasePath);

						if (!file.exists()) {
							file.createNewFile();
						}

						FileOutputStream outputStream = new FileOutputStream(
								file);

						byte[] buffer = new byte[1024 * 4];
						int count = 0;
						while ((count = is.read(buffer)) != -1) {

							outputStream.write(buffer, 0, count);
						}
						outputStream.close();
					}

					is.close();

					handler.sendEmptyMessage(1);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void init(Context context) {
		String packageName = context.getPackageName();

		try {
			appInfo = context.getPackageManager().getApplicationInfo(
					packageName, PackageManager.GET_META_DATA);
			String dir = appInfo.dataDir + File.separator + DB_DIR;
			File file = new File(dir);
			if (!file.exists()) {
				file.mkdir();
			}

			databasePath = appInfo.dataDir + file.separator + DB_DIR
					+ file.separator + DB_NAME;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				dbUtil = DBUtil.getInstance(databasePath);

				dbUtil.openDB();

				showRecommandCoffee();
				break;

			default:
				break;
			}
		};
	};

	protected void showRecommandCoffee() {
		final Coffee[] coffees = dbUtil.queryRecommandCoffee();

		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < coffees.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("coffee_name", coffees[i].getCoffee_name());
			map.put("coffee_price", coffees[i].getCoffee_price());
			map.put("coffee_info", coffees[i].getCoffee_info());
			// map.put("coffee_comment", coffees[i].getCoffee_comment());

			String pic_name = coffees[i].getImage_name();
			int img_id = getResources().getIdentifier(pic_name, "drawable",
					HomeFragment.this.getActivity().getPackageName());

			map.put("img_id", img_id + "");
			data.add(map);
		}

		SimpleAdapter adapter = new SimpleAdapter(
				HomeFragment.this.getActivity(), data, R.layout.item_gridview,
				new String[] { "coffee_name", "coffee_price", "coffee_info",
						"img_id" }, new int[] { R.id.coffeeName, R.id.price,
						R.id.coffeeIntro, R.id.gv_img });

		gridView.setAdapter(adapter);
	}
}
