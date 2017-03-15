package org.buddha.wise.media.widget;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Created by Yuan Jiwei on 17/3/15.
 */

public interface IMediaController {

    void setPlayer(IMediaPlayer player);

    void prepare();

    void start();

    void pause();

    void seekTo();

    void release();

    void reset();

    void stop();

    void attachView(AbstractControlView controlView);
}
