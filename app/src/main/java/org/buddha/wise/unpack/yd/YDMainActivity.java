package org.buddha.wise.unpack.yd;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;

import org.buddha.wise.R;

import java.util.List;

/**
 * Created by Yuan Jiwei on 17/3/25.
 */

public class YDMainActivity extends Activity implements YDContract.View {
    TabLayout mTab;
    YDPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_yd);
        mTab = (TabLayout) findViewById(R.id.tab);
        mTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        mPresenter = new YDPresenter(this);
        mPresenter.loginAsGuest();
        initTab();
    }

    private void initTab() {
        setTab(mPresenter.queryLocalChannel());
    }

    @Override
    public void setTab(List<YDChannel> channels) {
        if (channels == null) {
            return;
        }
        for (YDChannel c : channels) {
            mTab.addTab(mTab.newTab().setText(c.getName()));
        }
    }
}
