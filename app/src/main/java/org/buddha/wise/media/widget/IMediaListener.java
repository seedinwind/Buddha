package org.buddha.wise.media.widget;


import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Created by Yuan Jiwei on 17/3/16
 */

public abstract class IMediaListener implements IMediaPlayer.OnBufferingUpdateListener, IMediaPlayer.OnCompletionListener
        , IMediaPlayer.OnErrorListener, IMediaPlayer.OnInfoListener, IMediaPlayer.OnPreparedListener
        , IMediaPlayer.OnSeekCompleteListener, IMediaPlayer.OnVideoSizeChangedListener {
    @Override
    public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {

    }

    @Override
    public void onCompletion(IMediaPlayer iMediaPlayer) {

    }

    @Override
    public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {

    }

    @Override
    public void onSeekComplete(IMediaPlayer iMediaPlayer) {

    }

    @Override
    public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {

    }
}
