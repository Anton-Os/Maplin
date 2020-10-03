package com.antonos.maplin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.core.widget.ImageViewCompat;

import java.util.List;

import com.antonos.maplin.helper.Pinpoint;


public class MapView extends androidx.appcompat.widget.AppCompatImageView {
// public class MapView extends View {

    public MapView(Context context) {
        super(context);
    }

    public MapView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas){
        // TODO: set background Bitmap

        canvas.drawRect(0, 0, 100, 100, new Paint());
    }

    private Bitmap background;
    List<Pinpoint> pinpointList;
}