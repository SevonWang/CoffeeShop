package cn.edu.neusoft.android.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import cn.edu.neusoft.android.fragment.HomeFragment;
import cn.edu.neusoft.android.fragment.MeFragment;
import cn.edu.neusoft.android.fragment.SearchFragment;
import cn.edu.neusoft.android.fragment.ShopCartFragment;
import cn.edu.neusoft.android.fragment.SortFragment;

public class MainActivity extends FragmentActivity {

    private RadioGroup radioGroup;
    private RadioButton home, sort, search, shopcart, me;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragmentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navbar);

        initView();
        initViewPager();


    }

    public void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.main_tab_radioGroup);

        home = (RadioButton) findViewById(R.id.radio_home);
        search = (RadioButton) findViewById(R.id.radio_search);
        shopcart = (RadioButton) findViewById(R.id.radio_shop);
        sort = (RadioButton) findViewById(R.id.radio_sort);
        me = (RadioButton) findViewById(R.id.radio_me);

        radioGroup.setOnCheckedChangeListener(radioGroupListener);

    }

    RadioGroup.OnCheckedChangeListener radioGroupListener =
            new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged
                (RadioGroup radioGroup, @IdRes int i) {
            int current = 0;
            switch (i){
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

            }
            if(viewPager.getCurrentItem() != current){
                viewPager.setCurrentItem(current);
            }
        }
    };

    public void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.main_viewPager);

        HomeFragment home = new HomeFragment();
        SortFragment sort = new SortFragment();
        SearchFragment search = new SearchFragment();
        MeFragment me = new MeFragment();
        ShopCartFragment cart = new ShopCartFragment();

        fragmentsList = new ArrayList<Fragment>();
        fragmentsList.add(home);
        fragmentsList.add(cart);
        fragmentsList.add(sort);
        fragmentsList.add(search);
        fragmentsList.add(me);


        viewPager.setAdapter
                (new MyAdapter(getSupportFragmentManager(), fragmentsList));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(viewPagerListener);

    }

    ViewPager.OnPageChangeListener viewPagerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            int current = viewPager.getCurrentItem();
            switch (current){
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

            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

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
        public Fragment getItem(int position) {
            return list.get(position);
        }
    }

}
