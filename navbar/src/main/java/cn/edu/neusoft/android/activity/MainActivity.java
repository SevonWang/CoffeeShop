package cn.edu.neusoft.android.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import cn.edu.neusoft.android.fragment.HomeFragment;
import cn.edu.neusoft.android.fragment.MeFragment;
import cn.edu.neusoft.android.fragment.SearchFragment;
import cn.edu.neusoft.android.fragment.ShopFragment;
import cn.edu.neusoft.android.fragment.SortFragment;


public class MainActivity extends FragmentActivity {

    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private RadioButton radio_home, radio_shop, radio_sort, radio_search,
            radio_me;
    private ArrayList<Fragment> fragmentList;
    private int flag = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navbar);

        initView();
        initViewPager();
    }

    @Override
    public void finish() {
        if (flag == 2) {
            super.finish();
        } else {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_LONG).show();
            flag++;
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

    class myCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

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

    class myPageChangeListener implements ViewPager.OnPageChangeListener {

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

        private ArrayList<Fragment> list;

        public MyAdapter(FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Fragment getItem(int i) {
            return list.get(i);
        }

    }


}

