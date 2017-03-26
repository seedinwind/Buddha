package org.buddha.wise.unpack.yd;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.xiaohujr.credit.sdk.net.entity.collections.ParamMapBuilder;
import com.xiaohujr.credit.sdk.net.net.RequestMethod;
import com.xiaohujr.credit.sdk.net.net.SydHttpError;
import com.xiaohujr.credit.sdk.net.net.handle.ResponseHandler;

import org.buddha.wise.MyRequest;
import org.buddha.wise.utils.CookieUtil;
import org.buddha.wise.utils.SharePreferencesUtil;

/**
 * Created by seedinwind on 17/3/26.
 */

class ContentPresenter {
    String URL = "https://a1.go2yd.com/Website/channel/news-list-for-channel?" +
            "platform=1&cv=3.9.2.0" +
            "&fields=docid&fields=date&fields=image&fields=image_urls&fields=like&fields=source" +
            "&fields=title&fields=url&fields=comment_count&fields=up&fields=down" +
            "&cend=50&infinite=true&distribution=vivo1&refresh=1&appid=yidian" +
            "  &channel_id=11410718704&cstart=0&group_fromid=g181&version=020118&ad_version=010935&net=wifi";

    public void getContentList(String id) {
        String URL = "https://a1.go2yd.com/Website/channel/news-list-for-channel?" +
                "platform=1&cv=3.9.2.0" +
                "&fields=docid&fields=date&fields=image&fields=image_urls&fields=like&fields=source" +
                "&fields=title&fields=url&fields=comment_count&fields=up&fields=down" +
                "&cend=50&infinite=true&distribution=vivo1&refresh=1&appid=yidian" +
                "&cstart=0&group_fromid=g181&version=020118&ad_version=010935&net=wifi" + "&channel_id=" + id;
        new MyRequest().url(URL)
                .method(RequestMethod.GET)
                .headers(new ParamMapBuilder().putValue("Cookie", SharePreferencesUtil.getInstance().get("ydCookie")))
                .post(new ResponseHandler<JSONObject>() {
                    @Override
                    public void onSuccess(JSONObject jsonObject) {
                        Log.e("Json", jsonObject.toJSONString());
                    }

                    @Override
                    public void onFail(SydHttpError sydHttpError) {

                    }
                });
    }
}
