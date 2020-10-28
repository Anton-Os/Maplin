package com.antonos.maplin.helper;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

public class Pinpoint {
    public Pinpoint(Drawable i, double x, double y){ // Generic pinpoint constructor
        icon = i;
        xCoord = x;
        yCoord = y;
    }
    public Pinpoint(Drawable i, String n, double x, double y){ // Named pinpoint constructor
        icon = i;
        name = n;
        xCoord = x;
        yCoord = y;
    }

    private Drawable icon;
    private String name;
    private double xCoord;
    private double yCoord;

    public Drawable getIcon(){ return icon; }
    public String getName(){ return name; }
    public double getXCoord(){ return xCoord; }
    public double getYCoord(){ return yCoord; }
}
