package org.buddha.wise.media.widget;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

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
    private int mSeekWhenPrepared;
    private Map mHeaders;

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
//        addView(mControllerView.getView());
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
        mSeekWhenPrepared = 0;
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

    public void start(){
        mMediaController.start();
    }

    public void playBackStop() {
        mMediaController.stop();
    }

    private IMediaListener mStatusListener = new IMediaListener() {
        @Override
        public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
            super.onBufferingUpdate(iMediaPlayer, i);
        }

        @Override
        public void onCompletion(IMediaPlayer iMediaPlayer) {
            super.onCompletion(iMediaPlayer);
        }

        @Override
        public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
            Toast.makeText(getContext(), "onError", Toast.LENGTH_SHORT).show();
            return super.onError(iMediaPlayer, i, i1);
        }

        @Override
        public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
            return super.onInfo(iMediaPlayer, i, i1);
        }

        @Override
        public void onPrepared(IMediaPlayer iMediaPlayer) {
            Toast.makeText(getContext(), "onPrepared", Toast.LENGTH_SHORT).show();
            super.onPrepared(iMediaPlayer);
        }

        @Override
        public void onSeekComplete(IMediaPlayer iMediaPlayer) {
            super.onSeekComplete(iMediaPlayer);
        }

        @Override
        public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {
            super.onVideoSizeChanged(iMediaPlayer, i, i1, i2, i3);
        }
    };

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mMediaController.bindRenderView(mRenderView);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
