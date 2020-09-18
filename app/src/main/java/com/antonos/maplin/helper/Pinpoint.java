package com.antonos.maplin.helper;

import android.graphics.drawable.Drawable;

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

    // TODO: Implement an enum to distinguish pinpoint types
}
