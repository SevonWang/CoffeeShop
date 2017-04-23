package cn.edu.neusoft.coffeeshop.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import java.util.ArrayList;

import cn.edu.neusoft.coffeeshop.R;
import cn.edu.neusoft.coffeeshop.fragment.HomeFragment;
import cn.edu.neusoft.coffeeshop.fragment.MeFragment;
import cn.edu.neusoft.coffeeshop.fragment.SearchFragment;
import cn.edu.neusoft.coffeeshop.fragment.ShopFragment;
import cn.edu.neusoft.coffeeshop.fragment.SortFragment;

public class MainActivity extends FragmentActivity {

	private ViewPager viewPager;
	private RadioGroup radioGroup;
	private RadioButton radio_home, radio_shop, radio_sort, radio_search,
			radio_me;

	private ArrayList<Fragment> fragmentList;
	private int count = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_fragment);

		initView();

		initViewPager();
	}

	@Override
	public void finish() {
		if (count == 2) {
			super.finish();
		} else {
			Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_LONG).show();
			count++;
		}
	}

	private void initView() {
		radioGroup = (RadioGroup) findViewById(R.id.main_tab_radioGroup);

		radio_home = (RadioButton) findViewById(R.id.radio_home);
		radio_shop = (RadioButton) findViewById(R.id.radio_shop);
		radio_sort = (RadioButton) findViewById(R.id.radio_sort);
		radio_search = (RadioButton) findViewById(R.id.radio_search);
		radio_me = (RadioButton) findViewById(R.id.radio_me);

		radioGroup.setOnCheckedChangeListener(new myCheckedChangeListener());
	}

	private void initViewPager() {
		viewPager = (ViewPager) findViewById(R.id.main_viewPager);

		fragmentList = new ArrayList<Fragment>();

		Fragment homeFragment = new HomeFragment();
		Fragment shopFragment = new ShopFragment();
		Fragment sortFragment = new SortFragment();
		Fragment searchFragment = new SearchFragment();
		Fragment meFragment = new MeFragment();

		fragmentList.add(homeFragment);
		fragmentList.add(shopFragment);
		fragmentList.add(sortFragment);
		fragmentList.add(searchFragment);
		fragmentList.add(meFragment);

		viewPager.setAdapter(new MyAdapter(getSupportFragmentManager(),
				fragmentList));

		viewPager.setCurrentItem(0);

		viewPager.setOnPageChangeListener(new myPageChangeListener());

	}

	class myCheckedChangeListener implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			int current = 0;
			switch (checkedId) {
			case R.id.radio_home:
				current = 0;
				break;

			case R.id.radio_shop:
				current = 1;
				break;

			case R.id.radio_sort:
				current = 2;
				break;

			case R.id.radio_search:
				current = 3;
				break;

			case R.id.radio_me:
				current = 4;
				break;
			default:
				break;
			}

			if (viewPager.getCurrentItem() != current) {
				viewPager.setCurrentItem(current);
			}
		}
	}

	class myPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int arg0) {

			int current = viewPager.getCurrentItem();
			switch (current) {
			case 0:
				radioGroup.check(R.id.radio_home);
				break;

			case 1:
				radioGroup.check(R.id.radio_shop);
				break;

			case 2:
				radioGroup.check(R.id.radio_sort);
				break;

			case 3:
				radioGroup.check(R.id.radio_search);
				break;

			case 4:
				radioGroup.check(R.id.radio_me);
				break;
			default:
				break;
			}

		}

	}

	class MyAdapter extends FragmentPagerAdapter {

		ArrayList<Fragment> list;

		public MyAdapter(FragmentManager fm, ArrayList<Fragment> list) {
			super(fm);
			this.list = list;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Fragment getItem(int arg0) {
			return list.get(arg0);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
