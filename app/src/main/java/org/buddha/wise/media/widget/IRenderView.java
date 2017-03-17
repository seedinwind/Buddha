package org.buddha.wise.media.widget;

import android.view.SurfaceHolder;
import android.view.View;

/**
 * Created by Yuan Jiwei on 17/3/15.
 */

public interface IRenderView {

    int AR_ASPECT_FIT_PARENT = 0; // without clip
    int AR_ASPECT_FILL_PARENT = 1; // may clip
    int AR_ASPECT_WRAP_CONTENT = 2;
    int AR_MATCH_PARENT = 3;
    int AR_16_9_FIT_PARENT = 4;
    int AR_4_3_FIT_PARENT = 5;

    View getView();

    boolean shouldWaitForResize();

    void setVideoSize(int videoWidth, int videoHeight);

    void setVideoSampleAspectRatio(int videoSarNum, int videoSarDen);

    void setVideoRotation(int degree);

    void setAspectRatio(int aspectRatio);

    SurfaceHolder getSurfaceHolder();

    void setOnTouchListener(SurfaceRenderView.RenderTouchListener listener);


    interface RenderTouchListener {
        void onTouch();
    }
}
