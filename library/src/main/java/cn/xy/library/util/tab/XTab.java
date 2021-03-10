package cn.xy.library.util.tab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.design.widget.TabLayout;
import java.util.List;

public class XTab {
    public static void addTab(TabLayout tabLayout,
                              ViewPager viewPager,
                              final List<Fragment> fragment,
                              final List<String> title,
                              FragmentManager fragmentManager,
                              int offscreenPageLimit,
                              onPageSelected onPageSelected){
        /**
         * 预加载
         */
        viewPager.setOffscreenPageLimit(offscreenPageLimit);
        viewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return fragment.get(position);
            }
            @Override
            public int getCount() {
                return fragment.size();
            }
            @Override
            public CharSequence getPageTitle(int position) {
                return title.get(position);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {}

            @Override
            public void onPageSelected(int i) {
                onPageSelected.onPageSelected(i);}

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        // TabLayout关联ViewPager
        tabLayout.setupWithViewPager(viewPager);
    }
    public interface onPageSelected{
        void onPageSelected(int position);
    }
}
