package com.antonos.maplin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.core.widget.ImageViewCompat;

import java.util.ArrayList;
import java.util.List;

import com.antonos.maplin.helper.Pinpoint;


public class MapView extends androidx.appcompat.widget.AppCompatImageView
implements View.OnDragListener {
    public MapView(Context context) { super(context); }

    public MapView(Context context, AttributeSet attrs){ super(context, attrs); }

    public void setBkImage(int resId){
        setImageResource(resId);
        background = BitmapFactory.decodeResource(getResources(), resId);
    }

    /* public void setPinpoints(List<Pinpoint> ps){ pinpointList = ps; }

    public void addPinpoint(Pinpoint p){
        pinpointList.add(p);
        Log.v("STATUS", "Pinpoint added!"); // TESTING

        ImageView pinpointIconView = new ImageView(this.getContext());
        pinpointIconView.setPadding(100, 100, 100, 100);
        pinpointIconView.setImageBitmap(p.getIcon());
    } */

    @Override
    public boolean onDrag(View v, DragEvent event) {
        // TODO: Add a drag-like effect here
        return false;
    }

    private Bitmap background;
    // List<Pinpoint> pinpointList = new ArrayList<Pinpoint>();
}