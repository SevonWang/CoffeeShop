package cn.edu.neusoft.coffeeshop.actionbar;

import android.content.Context;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.widget.Toast;

public class PlusActionProvider extends ActionProvider {

	private Context context;

	public PlusActionProvider(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	public View onCreateActionView() {
		return null;
	}

	@Override
	public void onPrepareSubMenu(SubMenu subMenu) {
		subMenu.clear();
		subMenu.add("个人").setOnMenuItemClickListener(
				new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item) {
						// TODO Auto-generated method stub
						Toast.makeText(context, "您选中了 “个人” 的菜单选项",
								Toast.LENGTH_LONG).show();
						return true;
					}
				});

		subMenu.add("已浏览").setOnMenuItemClickListener(
				new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item) {
						// TODO Auto-generated method stub
						Toast.makeText(context, "您选中了 “已浏览” 的菜单选项",
								Toast.LENGTH_LONG).show();
						return true;
					}
				});

		subMenu.add("已收藏").setOnMenuItemClickListener(
				new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item) {
						// TODO Auto-generated method stub
						Toast.makeText(context, "您选中了 “已收藏” 的菜单选项",
								Toast.LENGTH_LONG).show();
						return true;
					}
				});
	}

	@Override
	public boolean hasSubMenu() {
		return true;
	}
}
