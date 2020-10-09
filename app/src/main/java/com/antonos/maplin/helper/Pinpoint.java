package com.antonos.maplin.helper;

import android.graphics.Bitmap;

public class Pinpoint {
    public Pinpoint(Bitmap i, double x, double y){ // Generic pinpoint constructor
        icon = i;
        xCoord = x;
        yCoord = y;
    }
    public Pinpoint(Bitmap i, String n, double x, double y){ // Named pinpoint constructor
        icon = i;
        name = n;
        xCoord = x;
        yCoord = y;
    }
    private Bitmap icon;
    private String name;
    private double xCoord;
    private double yCoord;

    // TODO: Implement an enum to distinguish pinpoint types
}
