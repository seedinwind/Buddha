package org.buddha.wise.media.widget;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;

import org.buddha.wise.media.FileMediaDataSource;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.misc.IMediaDataSource;

/**
 * Created by Yuan Jiwei on 17/3/16.
 */

public class DefaultMediaController implements IMediaController {
    private IMediaPlayer mMediaPlayer = null;
    private IMediaListener mMediaListener;
    private Context mContext;
    private Status mStatus = new Status();

    public void setMediaListener(IMediaListener mediaListener) {
        mMediaListener = mediaListener;
        attachStatusListener();
    }

    @Override
    public void initPlayer(Context context, Uri uri, Map<String, String> headers) {
        if (uri == null) {
            return;
        }
        mContext = context;
        AudioManager am = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        am.requestAudioFocus(null, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        try {
            mMediaPlayer = createPlayer(0);
            attachStatusListener();
            setDataSource(context, uri, headers);
        } catch (IOException e) {
            mStatus.setTargetState(Status.STATE_ERROR);
            mStatus.setCurrentState(Status.STATE_ERROR);
            if (mMediaListener != null)
                mMediaListener.onError(mMediaPlayer, MediaPlayer.MEDIA_ERROR_UNKNOWN, 0);
        } catch (IllegalArgumentException ex) {
            mStatus.setTargetState(Status.STATE_ERROR);
            mStatus.setCurrentState(Status.STATE_ERROR);
            if (mMediaListener != null)
                mMediaListener.onError(mMediaPlayer, MediaPlayer.MEDIA_ERROR_UNKNOWN, 0);
        } finally {
            // REMOVED: mPendingSubtitleTracks.clear();
        }
    }

    private void setDataSource(Context context, Uri uri, Map<String, String> headers) throws IOException {
        String scheme = uri.getScheme();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                (TextUtils.isEmpty(scheme) || scheme.equalsIgnoreCase("file"))) {
            IMediaDataSource dataSource = new FileMediaDataSource(new File(uri.toString()));
            mMediaPlayer.setDataSource(dataSource);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            mMediaPlayer.setDataSource(context, uri, headers);
        } else {
            mMediaPlayer.setDataSource(uri.toString());
        }
//        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        mMediaPlayer.setScreenOnWhilePlaying(true);
    }

    public void bindRenderView(IRenderView renderView) {
        mMediaPlayer.setDisplay(renderView.getSurfaceHolder());
    }

    private void attachStatusListener() {
        if (mMediaListener != null && mMediaPlayer != null) {
            mMediaPlayer.setOnPreparedListener(mMediaListener);
            mMediaPlayer.setOnVideoSizeChangedListener(mMediaListener);
            mMediaPlayer.setOnCompletionListener(mMediaListener);
            mMediaPlayer.setOnErrorListener(mMediaListener);
            mMediaPlayer.setOnInfoListener(mMediaListener);
            mMediaPlayer.setOnBufferingUpdateListener(mMediaListener);
            mMediaPlayer.setOnSeekCompleteListener(mMediaListener);
//            mMediaPlayer.setOnTimedTextListener(mMediaListener);
        }
    }


    @Override
    public void prepare() {
        mMediaPlayer.prepareAsync();
        mStatus.setCurrentState(Status.STATE_PREPARING);
    }

    @Override
    public void start() {
        mMediaPlayer.start();
    }

    @Override
    public void pause() {

    }

    @Override
    public void seekTo() {

    }

    @Override
    public void release() {

    }

    public void release(boolean clearTargetStatus) {
        if (mMediaPlayer != null) {
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
            // REMOVED: mPendingSubtitleTracks.clear();
            mStatus.setCurrentState(Status.STATE_IDLE);
            if (clearTargetStatus) {
                mStatus.setTargetState(Status.STATE_IDLE);
            }
            AudioManager am = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
            am.abandonAudioFocus(null);
        }
    }


    @Override
    public void reset() {

    }

    @Override
    public void stop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
            mStatus.setCurrentState(Status.STATE_IDLE);
            mStatus.setTargetState(Status.STATE_IDLE);
            AudioManager am = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
            am.abandonAudioFocus(null);
        }
    }

    @Override
    public void attachView(AbstractControlView controlView) {

    }

    public IMediaPlayer createPlayer(int playerType) {
        IMediaPlayer mediaPlayer = null;

        switch (playerType) {
//            case Settings.PV_PLAYER__IjkExoMediaPlayer: {
//                IjkExoMediaPlayer IjkExoMediaPlayer = new IjkExoMediaPlayer(mAppContext);
//                mediaPlayer = IjkExoMediaPlayer;
//            }
//            break;
//            case Settings.PV_PLAYER__AndroidMediaPlayer: {
//                AndroidMediaPlayer androidMediaPlayer = new AndroidMediaPlayer();
//                mediaPlayer = androidMediaPlayer;
//            }
//            break;
//            case Settings.PV_PLAYER__IjkMediaPlayer:
            default: {
                IjkMediaPlayer ijkMediaPlayer = new IjkMediaPlayer();
                ijkMediaPlayer.native_setLogLevel(IjkMediaPlayer.IJK_LOG_DEBUG);

//                    if (mSettings.getUsingMediaCodec()) {
//                        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 1);
//                        if (mSettings.getUsingMediaCodecAutoRotate()) {
//                            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-auto-rotate", 1);
//                        } else {
//                            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-auto-rotate", 0);
//                        }
//                        if (mSettings.getMediaCodecHandleResolutionChange()) {
//                            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-handle-resolution-change", 1);
//                        } else {
//                            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-handle-resolution-change", 0);
//                        }
//                    } else {
                ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 0);
//                    }

//                    if (mSettings.getUsingOpenSLES()) {
                ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "opensles", 1);
//                    } else {
//                        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "opensles", 0);
//                    }

//                    String pixelFormat = mSettings.getPixelFormat();
//                    if (TextUtils.isEmpty(pixelFormat)) {
                ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "overlay-format", IjkMediaPlayer.SDL_FCC_RV32);
//                    } else {
//                        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "overlay-format", pixelFormat);
//                    }
                ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 1);
                ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "start-on-prepared", 0);

                ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "http-detect-range-support", 0);

                ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_CODEC, "skip_loop_filter", 48);
                mediaPlayer = ijkMediaPlayer;
            }
            break;
        }

//        if (mSettings.getEnableDetachedSurfaceTextureView()) {
//            mediaPlayer = new TextureMediaPlayer(mediaPlayer);
//        }

        return mediaPlayer;
    }


    class Status {

        // all possible internal states
        public static final int STATE_ERROR = -1;
        public static final int STATE_IDLE = 0;
        public static final int STATE_PREPARING = 1;
        public static final int STATE_PREPARED = 2;
        public static final int STATE_PLAYING = 3;
        public static final int STATE_PAUSED = 4;
        public static final int STATE_PLAYBACK_COMPLETED = 5;

        private int mCurrentState = STATE_IDLE;
        private int mTargetState = STATE_IDLE;

        public int getCurrentState() {
            return mCurrentState;
        }

        public void setCurrentState(int currentState) {
            mCurrentState = currentState;
        }

        public int getTargetState() {
            return mTargetState;
        }

        public void setTargetState(int targetState) {
            mTargetState = targetState;
        }
    }
}
