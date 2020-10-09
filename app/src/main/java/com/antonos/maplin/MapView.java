package com.antonos.maplin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
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

    public void setBkImage(int resId){
        setImageResource(resId);
        // background = BitmapFactory.decodeResource(getResources(), resId); // Saved as bitmap in class
    }

    public void setPinpoints(List<Pinpoint> ps){ pinpointList = ps; }
    public void addPinpoint(Pinpoint p){
        pinpointList.add(p);
        Log.v("STATUS", "Pinpoint added!"); // TESTING
    }

    private Bitmap background;
    List<Pinpoint> pinpointList;
}