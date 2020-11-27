package com.antonos.maplin.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Map {
    public Map(String n, int rs){
        name = n;
        basemapImageRes = rs;
    }

    public static class MapView extends androidx.appcompat.widget.AppCompatImageView {
        public MapView(Context context) { super(context); }

        public MapView(Context context, AttributeSet attrs){ super(context, attrs); }

        public void setBkImage(int resId) {
            setImageResource(resId);

            // TODO: Scale the image appropriately
            // setMinimumWidth(((BitmapDrawable)getBackground()).getBitmap().getWidth());
            // setMinimumHeight(((BitmapDrawable)getBackground()).getBitmap().getHeight());
        }
    }

    public void addPinpoint(Pinpoint pinpoint){ pinpointList.add(pinpoint); }
    public int getBasemapImageRes(){ return basemapImageRes; }

    private String name;
    private List<Pinpoint> pinpointList = new ArrayList<Pinpoint>();
    private int basemapImageRes = 0;
    // TODO: Include resources for layered or "stacked" image resources
}
