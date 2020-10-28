package com.antonos.maplin.helper;

import androidx.core.widget.ImageViewCompat;
import android.content.Context;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private String name;
    private List<Pinpoint> pinpointList = new ArrayList<Pinpoint>();

    public static class MapView extends androidx.appcompat.widget.AppCompatImageView {
        public MapView(Context context) { super(context); }

        public MapView(Context context, AttributeSet attrs){ super(context, attrs); }

        public void setBkImage(int resId) {
            setImageResource(resId);
        }
    }
    // public MapView mapView;
}
