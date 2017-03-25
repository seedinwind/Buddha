package org.buddha.wise.unpack.yd;


import android.text.TextUtils;

import com.alibaba.fastjson.JSON;

import org.buddha.wise.utils.SharePreferencesUtil;

import java.util.List;

/**
 * Created by Yuan Jiwei on 17/3/25.
 */

class YDPresenter implements YDContract.Presenter {
    private YDContract.View mViews;
    private YDNetService mNetService;
    private boolean mLogin;
    private boolean mSearchChannel;

    public YDPresenter(YDContract.View views) {
        mViews = views;
        mNetService = new YDNetService(this);
    }

    @Override
    public void loginAsGuest() {
        mNetService.loginAsGuest();
    }

    @Override
    public List<YDChannel> queryLocalChannel() {
        String channels = SharePreferencesUtil.getInstance().get("yd_channel");
        if (!TextUtils.isEmpty(channels)) {
            return JSON.parseArray(channels, YDChannel.class);
        } else {
            if (mLogin) {
                mNetService.searchForNeededChannel();
            } else {
                mSearchChannel = true;
            }
            return null;
        }
    }

    @Override
    public void updateLocalChannel() {

    }

    @Override
    public void searchForNeededChannel() {

    }

    @Override
    public void subscribeChannel() {

    }

    @Override
    public void loginSuccess() {
        mLogin = true;
        if (mSearchChannel) {
            mNetService.searchForNeededChannel();
        }
    }

    @Override
    public void getDefaultChannels(List<YDChannel> channels) {
        mViews.setTab(channels);
    }

}
