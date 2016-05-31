package clothesup.turningpoint.clothesup.main;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import clothesup.turningpoint.clothesup.map.MapFragment;
import clothesup.turningpoint.clothesup.R;
import clothesup.turningpoint.clothesup.store.StoreFragment;
import clothesup.turningpoint.clothesup.more.moreFragment;
import clothesup.turningpoint.clothesup.mypage.myPageFragment;

public class MainTabLayout extends AppCompatActivity {
    TabLayout tabLayout;    //menu
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab_layout);

        viewPager = (ViewPager) findViewById(R.id.tab_layout_view_pager);
        viewPager.setAdapter(new CustomAdapterForPager(getSupportFragmentManager(), getApplicationContext()));
        tabLayout = (TabLayout) findViewById(R.id.tab_layout_tab);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.tab_map);
        tabLayout.getTabAt(1).setIcon(R.drawable.tab_store);
        tabLayout.getTabAt(2).setIcon(R.drawable.tab_mypage);
        tabLayout.getTabAt(3).setIcon(R.drawable.tab_more);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });
    }

    private class CustomAdapterForPager extends FragmentPagerAdapter {
        private String fragments [] = {"map", "store", "mypage", "more.."};

        public CustomAdapterForPager(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0 :    return new MapFragment();
                case 1 :    return new StoreFragment();
                case 2 :    return new myPageFragment();
                case 3 :    return new moreFragment();
                default :   return null;
            }
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        public CharSequence getPageTitle(int position){
            return fragments[position];
        }
    }
}
