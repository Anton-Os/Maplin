package com.antonos.maplin;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLRenderView extends GLSurfaceView {

    public GLRenderView(Context context) {
        super(context);

        setEGLContextClientVersion(3);
        GLRenderer renderer = new GLRenderer();
        setRenderer(renderer);
    }

    public class GLRenderer implements GLSurfaceView.Renderer {

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            GLES30.glClearColor(0.4f, 0.4f, 0.9f, 1.0f);
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            GLES30.glViewport(0, 0, width, height);
        }

        @Override
        public void onDrawFrame(GL10 gl) { // Draw loop
            GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);
        }
    }
}
