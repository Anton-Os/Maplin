package com.antonos.maplin;

import android.content.Context;

import org.junit.Test;

import java.io.File;

public class FileIO {
    @Test
    void readMapFile(Context context){
        File mapFile = new File(context.getFilesDir(), "Map1.map");
    }
}
