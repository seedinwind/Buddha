package org.buddha.wise.unpack.yd;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaohujr.credit.sdk.net.entity.collections.ParamMapBuilder;
import com.xiaohujr.credit.sdk.net.net.RequestMethod;
import com.xiaohujr.credit.sdk.net.net.SydHttpError;
import com.xiaohujr.credit.sdk.net.net.handle.ResponseHandler;

import org.buddha.wise.MyRequest;
import org.buddha.wise.utils.CookieUtil;
import org.buddha.wise.utils.SharePreferencesUtil;

import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Yuan Jiwei on 17/3/25.
 */

class YDNetService {

    private final YDPresenter mPresenter;

    public YDNetService(YDPresenter ydPresenter) {
        mPresenter = ydPresenter;
    }

    public void loginAsGuest() {
        String name;
        if (!TextUtils.isEmpty(SharePreferencesUtil.getInstance().get("yd_user"))) {
            name = SharePreferencesUtil.getInstance().get("yd_user");
        } else {
            name = Util.createGuest();
            SharePreferencesUtil.getInstance().put("yd_user", name);
        }
        String password = Util.createCoded(name.toLowerCase(), name);
        String secret = Util.createCoded(name.toLowerCase(), "yidian");
        new MyRequest().url("http://a1.go2yd.com/Website/user/login-as-guest")
                .method(RequestMethod.GET)
                .postParams(new ParamMapBuilder()
                        .putValue("platform", "1")
                        .putValue("deviceId", "")
                        .putValue("cv", "")
                        .putValue("token", "")
                        .putValue("distribution", "")
                        .putValue("autoStartup", "")
                        .putValue("appid", "yidian")
                        .putValue("version", "020118")
                        .putValue("org/buddha/wise/net", "wifi")
                        .putValue("username", name)
                        .putValue("password", password)
                        .putValue("secret", secret)
                )
                .post(new ResponseHandler<JSONObject>() {
                    @Override
                    public void onSuccess(JSONObject jsonObject) {
                        Log.e("Json", jsonObject.toJSONString());
                        String cookie = jsonObject.getString("cookie");
                        CookieUtil.setCookie("a1.go2yd.com", cookie);
                        SharePreferencesUtil.getInstance().put("ydCookie", cookie);
                        mPresenter.loginSuccess();
                    }

                    @Override
                    public void onFail(SydHttpError sydHttpError) {

                    }
                });
    }

    public void searchForNeededChannel() {
//        String user = SharePreferencesUtil.getInstance().get("yd_user");
        new MyRequest().url("https://a1.go2yd.com/Website/channel/search-channel")
                .method(RequestMethod.GET)
                .headers(new ParamMapBuilder().putValue("Cookie", SharePreferencesUtil.getInstance().get("ydCookie")))
                .postParams(new ParamMapBuilder()
                                .putValue("platform", "1")
                                .putValue("deviceId", "")
                                .putValue("cv", "")
                                .putValue("token", "")
                                .putValue("distribution", "")
                                .putValue("autoStartup", "")
                                .putValue("appid", "yidian")
                                .putValue("version", "020118")
                                .putValue("org/buddha/wise/net", "wifi")
                                .putValue("group_id", "g181")
                                .putValue("group_fromid", "g181")
                                .putValue("word", URLEncoder.encode("佛教"))
//                        .putValue("username", user)
//                        .putValue("password", Util.createCoded(user.toLowerCase(), user))
//                        .putValue("secret", Util.createCoded(user.toLowerCase(), "yidian"))
                )

                .post(new ResponseHandler<JSONObject>() {
                    @Override
                    public void onSuccess(JSONObject jsonObject) {
                        List<YDChannel> channels= JSON.parseArray(jsonObject.getString("channels"),YDChannel.class);
                        SharePreferencesUtil.getInstance().put("yd_channel",JSON.toJSONString(channels));
                        mPresenter.getDefaultChannels(channels);
                    }

                    @Override
                    public void onFail(SydHttpError sydHttpError) {

                    }
                });

    }
}
