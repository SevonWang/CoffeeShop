package cn.edu.neusoft.coffeeshop.fragment;

import java.util.ArrayList;
import java.util.HashMap;

import cn.edu.neusoft.coffeeshop.R;
import cn.edu.neusoft.coffeeshop.activity.LoginActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MeFragment extends Fragment {

	private Button btnLogin;
	private ListView listView;
	private View view, headerView;

	private ArrayList<HashMap<String, Object>> items;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.bottom_me_fragment, null);
		headerView = inflater.inflate(R.layout.header_me_fragment, null);

		btnLogin = (Button) headerView.findViewById(R.id.login_register);
		listView = (ListView) view.findViewById(R.id.lv_functions);

		btnLogin.setOnClickListener(new MyLoginClickListener());

		items = MeDataFragment.getItem();

		SimpleAdapter adapter = new SimpleAdapter(this.getActivity(), items,
				R.layout.item_me_fragment, new String[] { "function_name",
						"image_id" }, new int[] { R.id.tv_functionName,
						R.id.iv_icon });

		listView.addHeaderView(headerView);
		listView.setAdapter(adapter);

		init();

		return view;
	}

	private void init() {
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 1:
					Toast.makeText(MeFragment.this.getActivity(),
							"你选中了 “我的信息” 功能！", 3000).show();
					break;

				case 2:
					Toast.makeText(MeFragment.this.getActivity(),
							"你选中了 “我的订单” 功能！", 3000).show();
					break;
				case 3:
					Toast.makeText(MeFragment.this.getActivity(),
							"你选中了 “我的地址” 功能！", 3000).show();
					break;
				case 4:
					Toast.makeText(MeFragment.this.getActivity(),
							"你选中了 “我的收藏” 功能！", 3000).show();
					break;
				case 5:
					Toast.makeText(MeFragment.this.getActivity(),
							"你选中了 “我的礼券” 功能！", 3000).show();
					break;
				case 6:
					Toast.makeText(MeFragment.this.getActivity(),
							"你选中了 “我的积分” 功能！", 3000).show();
					break;
				case 7:
					Toast.makeText(MeFragment.this.getActivity(),
							"你选中了 “意见反馈” 功能！", 3000).show();
					break;
				case 8:
					Toast.makeText(MeFragment.this.getActivity(),
							"你选中了 “客服电话” 功能！", 3000).show();
					servicePhone();

					break;
				default:
					break;
				}
			}
		});
	}

	protected void servicePhone() {

		AlertDialog.Builder builder = new AlertDialog.Builder(
				MeFragment.this.getActivity());

		builder.setTitle("客服电话");
		builder.setIcon(R.drawable.ic_launcher);
		builder.setMessage("拨打电话：18866668888");

		// 设置对话框的取消按钮
		DialogInterface.OnClickListener cancelClickListener = new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		};
		builder.setNegativeButton("取消", cancelClickListener);

		// 设置对话框的确认按钮
		DialogInterface.OnClickListener confirmClickListener = new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				callPhone();
			}
		};
		builder.setPositiveButton("拨打", confirmClickListener);

		AlertDialog dialog = builder.create();
		dialog.show();
	}

	protected void callPhone() {
		String data = "tel:" + "18866668888";
		Uri uri = Uri.parse(data);
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_CALL);
		intent.setData(uri);
		startActivity(intent);
	}

	class MyLoginClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {

			Intent intent = new Intent(MeFragment.this.getActivity(),
					LoginActivity.class);
			startActivity(intent);

		}
	}

}
