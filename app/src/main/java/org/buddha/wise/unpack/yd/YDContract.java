package org.buddha.wise.unpack.yd;

import java.util.List;

/**
 * Created by Yuan Jiwei on 17/3/25.
 */

public interface YDContract {
    interface View {

        void setTab(List<YDChannel> channels);
    }

    interface Presenter {
        void loginAsGuest();

        List<YDChannel> queryLocalChannel();

        void updateLocalChannel();

        void searchForNeededChannel();

        void subscribeChannel();

        void loginSuccess();

        void getDefaultChannels(List<YDChannel> channels);
    }
}
