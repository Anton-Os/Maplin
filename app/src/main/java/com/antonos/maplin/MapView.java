package com.antonos.maplin;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import com.antonos.maplin.helper.Pinpoint;

public class MapView
extends androidx.appcompat.widget.AppCompatImageView {

    // TODO: Implement a default contructor
    public MapView(Context context, Drawable drawable){
        super(context);

        background = drawable;
    }

    private Drawable background;
    List<Pinpoint> pinpointList;
}
