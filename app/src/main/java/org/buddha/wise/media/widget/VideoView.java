package org.buddha.wise.media.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.buddha.wise.R;

import java.util.Map;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Created by Yuan Jiwei on 17/3/16.
 */

public class VideoView extends FrameLayout implements SurfaceHolder.Callback {

    private DefaultMediaController mMediaController = new DefaultMediaController();
    private IRenderView mRenderView;
    private AbstractControlView mControllerView;
    private Uri mUri;
    private Map mHeaders;

    private int mVideoWidth;
    private int mVideoHeight;
    private int mVideoSarNum;
    private int mVideoSarDen;
    private int mSurfaceWidth;
    private int mSurfaceHeight;
    private int mVideoRotationDegree;

    private static final int[] s_allAspectRatio = {
            org.buddha.wise.media.IRenderView.AR_ASPECT_FIT_PARENT,
            org.buddha.wise.media.IRenderView.AR_ASPECT_FILL_PARENT,
            org.buddha.wise.media.IRenderView.AR_ASPECT_WRAP_CONTENT,
            // IRenderView.AR_MATCH_PARENT,
            org.buddha.wise.media.IRenderView.AR_16_9_FIT_PARENT,
            org.buddha.wise.media.IRenderView.AR_4_3_FIT_PARENT};
    private int mCurrentAspectRatioIndex = 0;
    private int mCurrentAspectRatio = s_allAspectRatio[4];

    public VideoView(@NonNull Context context) {
        super(context);
        initView();
    }

    public VideoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public VideoView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mVideoWidth = 0;
        mVideoHeight = 0;
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        addRenderView();
        addDefaultControllerView();
    }

    private void addRenderView() {
        mRenderView = createRenderView();
        mRenderView.getSurfaceHolder().addCallback(this);
        addView(mRenderView.getView(), new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    private IRenderView createRenderView() {
        IRenderView renderView = new SurfaceRenderView(getContext());
        return renderView;
    }

    private void addDefaultControllerView() {
        ViewGroup v = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.default_control_view, null);
        mControllerView = new DefaultControllerView(v);
        addView(mControllerView.getView());
        mControllerView.attachMediaController(mMediaController);
        mControllerView.hide();
    }

    public void setUserController(AbstractControlView controllerView, FrameLayout.LayoutParams layoutParam) {
        mControllerView = controllerView;
        addView(mControllerView.getView(), layoutParam);
        mControllerView.attachMediaController(mMediaController);
        mControllerView.hide();
    }

    /**
     * Sets video path.
     *
     * @param path the path of the video.
     */
    public void setVideoPath(String path) {
        setVideoURI(Uri.parse(path));
    }

    /**
     * Sets video URI.
     *
     * @param uri the URI of the video.
     */
    public void setVideoURI(Uri uri) {
        setVideoURI(uri, null);
    }

    /**
     * Sets video URI using specific headers.
     *
     * @param uri     the URI of the video.
     * @param headers the headers for the URI request.
     *                Note that the cross domain redirection is allowed by default, but that can be
     *                changed with key/value pairs through the headers parameter with
     *                "android-allow-cross-domain-redirect" as the key and "0" or "1" as the value
     *                to disallow or allow cross domain redirection.
     */
    private void setVideoURI(Uri uri, Map<String, String> headers) {
        mUri = uri;
        mHeaders = headers;
        openVideo();
        requestLayout();
        invalidate();
    }

    private void openVideo() {
        if (mUri == null || mRenderView == null) {
            // not ready for playback just yet, will try again later
            return;
        }
        // we shouldn't clear the target state, because somebody might have
        // called start() previously
        mMediaController.release(false);
        mMediaController.setMediaListener(mStatusListener);
        mMediaController.initPlayer(getContext(), mUri, mHeaders);
        //TODO
//        mCurrentBufferPercentage = 0;
        mMediaController.prepare();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mMediaController.release(true);
    }

    public void playBackStop() {
        mMediaController.stop();
    }

    private IMediaListener mStatusListener = new IMediaListener() {
        @Override
        public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
        }

        @Override
        public void onCompletion(IMediaPlayer iMediaPlayer) {
            mControllerView.show();
        }

        @Override
        public boolean onError(IMediaPlayer iMediaPlayer, int framework_err, int impl_err) {
            new AlertDialog.Builder(getContext())
                    .setMessage("")
                    .setPositiveButton(R.string.VideoView_error_button,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                            /* If we get here, there is no onError listener, so
                                             * at least inform them that the video is over.
                                             */

                                }
                            })
                    .setCancelable(false)
                    .show();
            return true;
        }

        @Override
        public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
            return super.onInfo(iMediaPlayer, i, i1);
        }

        @Override
        public void onPrepared(IMediaPlayer iMediaPlayer) {
            mControllerView.show();
        }

        @Override
        public void onSeekComplete(IMediaPlayer iMediaPlayer) {

        }

        @Override
        public void onVideoSizeChanged(IMediaPlayer mp, int i, int i1, int i2, int i3) {
            mVideoWidth = mp.getVideoWidth();
            mVideoHeight = mp.getVideoHeight();
            mVideoSarNum = mp.getVideoSarNum();
            mVideoSarDen = mp.getVideoSarDen();
//            if (mVideoWidth != 0 && mVideoHeight != 0) {
//                if (mRenderView != null) {
//                    mRenderView.setVideoSize(mVideoWidth, mVideoHeight);
//                    mRenderView.setVideoSampleAspectRatio(mVideoSarNum, mVideoSarDen);
//                }
//                // REMOVED: getHolder().setFixedSize(mVideoWidth, mVideoHeight);
//                requestLayout();
//            }
        }
    };

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (mMediaController.hasPlayer()) {
            mMediaController.bindRenderView(mRenderView);
        } else {
            openVideo();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mSurfaceWidth = width;
        mSurfaceHeight = height;
        boolean isValidState = mMediaController.isPlaying();
        boolean hasValidSize = !mRenderView.shouldWaitForResize() || (mVideoWidth == width && mVideoHeight == height);
        if (mMediaController.hasPlayer() && isValidState && hasValidSize) {
//            if (mSeekWhenPrepared != 0) {
//                seekTo(mSeekWhenPrepared);
//            }
            mMediaController.start();

        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mMediaController.isInPlaybackState() ) {
            toggleMediaControlsVisiblity();
        }
        return false;
    }

    private void toggleMediaControlsVisiblity() {
        if (mControllerView.isShowing()) {
            mControllerView.hide();
        } else {
            mControllerView.show();
        }
    }
}
