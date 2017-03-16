package org.buddha.wise.media.widget;

import android.content.Context;
import android.net.Uri;

import java.util.Map;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Created by Yuan Jiwei on 17/3/15.
 */

public interface IMediaController {

    void initPlayer(Context context, Uri uri, Map<String, String> headers);

    void prepare();

    void start();

    void pause();

    void seekTo();

    void release();

    void reset();

    void stop();

    void attachView(AbstractControlView controlView);

    void release(boolean b);
}