package com.example.admin.designtask.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.designtask.view.fragment.CommentFragment;
import com.example.admin.designtask.view.fragment.LikeFragment;
import com.example.admin.designtask.view.fragment.LocationFragment;
import com.example.admin.designtask.view.fragment.ProfileFragment;
import com.example.admin.designtask.R;
import com.example.admin.designtask.view.fragment.FeedFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    private int[] imageResId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        imageResId = new int[] {
                R.drawable.ic_profile,
                R.drawable.ic_feed,
                R.drawable.ic_locations,
                R.drawable.ic_likes,
                R.drawable.ic_comments
        };

        viewPager = (ViewPager) findViewById(R.id.tab_viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_menu);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setIcon(imageResId[i]);
        }

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new ProfileFragment(), R.drawable.ic_profile);
        adapter.addFrag(new FeedFragment(), R.drawable.ic_feed);
        adapter.addFrag(new LocationFragment(), R.drawable.ic_locations);
        adapter.addFrag(new LikeFragment(), R.drawable.ic_likes);
        adapter.addFrag(new CommentFragment(), R.drawable.ic_comments);
        viewPager.setAdapter(adapter);
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<Integer> mFragmentIconList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, int image) {
            mFragmentList.add(fragment);
            mFragmentIconList.add(image);
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.menu_items, menu);
//        return true;
//    }
//
//    /**
//     * Event Handling for Individual menu item selected
//     * Identify single menu item by it's id
//     */
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch (item.getItemId()) {
//            case R.id.menu_item_profile:
//                // Single menu item is selected do something
//                // Ex: launching new activity/screen or show alert message
//                Toast.makeText(this, "Bookmark is Selected", Toast.LENGTH_SHORT).show();
//                return true;
//
//            case R.id.menu_item_feed:
//                Toast.makeText(this, "Save is Selected", Toast.LENGTH_SHORT).show();
//                return true;
//
//            case R.id.menu_item_location:
//                Toast.makeText(this, "Search is Selected", Toast.LENGTH_SHORT).show();
//                return true;
//
//            case R.id.menu_item_like:
//                Toast.makeText(this, "Share is Selected", Toast.LENGTH_SHORT).show();
//                return true;
//
//            case R.id.menu_item_comment:
//                Toast.makeText(this, "Delete is Selected", Toast.LENGTH_SHORT).show();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
