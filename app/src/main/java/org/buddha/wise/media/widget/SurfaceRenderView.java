package org.buddha.wise.media.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by Yuan Jiwei on 17/3/16.
 */

public class SurfaceRenderView extends SurfaceView implements IRenderView {
    public SurfaceRenderView(Context context) {
        super(context);
    }

    public SurfaceRenderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SurfaceRenderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public boolean shouldWaitForResize() {
        return false;
    }

    @Override
    public void setVideoSize(int videoWidth, int videoHeight) {

    }

    @Override
    public void setVideoSampleAspectRatio(int videoSarNum, int videoSarDen) {

    }

    @Override
    public void setVideoRotation(int degree) {

    }

    @Override
    public void setAspectRatio(int aspectRatio) {

    }

    @Override
    public SurfaceHolder getSurfaceHolder() {
        return getHolder();
    }
}
