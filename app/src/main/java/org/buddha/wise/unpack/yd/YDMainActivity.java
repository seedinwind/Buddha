package org.buddha.wise.unpack.yd;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import org.buddha.wise.R;
import org.buddha.wise.utils.SharePreferencesUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuan Jiwei on 17/3/25.
 */

public class YDMainActivity extends FragmentActivity implements YDContract.View {
    TabLayout mTab;
    YDPresenter mPresenter;
    private ViewPager mViewPager;
    private List<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_yd);
        mTab = (TabLayout) findViewById(R.id.tab);
        mTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mTab.setupWithViewPager(mViewPager);
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ContentFragment.sCurrentSelectTab = tab.getPosition();

//                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mPresenter = new YDPresenter(this);
        mPresenter.loginAsGuest();
        initTab();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharePreferencesUtil.getInstance().remove(new String[]{"ydCookie"});
    }

    private void initTab() {
        setTab(mPresenter.queryLocalChannel());
    }

    @Override
    public void setTab(List<YDChannel> channels) {
        if (channels == null) {
            return;
        }
        for (int i = 0; i < channels.size(); i++) {
            YDChannel channel = mPresenter.getTabChannel(i);
            Bundle b = new Bundle();
            b.putSerializable("channel", channel);
            ContentFragment fragment = ContentFragment.newFragment(b);
            fragments.add(fragment);
        }
        mViewPager.setAdapter(mAdapter);
    }

    private FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(YDMainActivity.this.getSupportFragmentManager()) {

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mPresenter.getTabChannel(position).getName();
        }
    };
}
