package com.antonos.maplin;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaplinActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSharedPrefs = getApplicationContext().getSharedPreferences(MaplinContext.sharedPrefsFile, getApplicationContext().MODE_PRIVATE);
        mMaplinContext = new MaplinContext(mSharedPrefs);

        mPixelDensity = getApplicationContext().getResources().getDisplayMetrics().density;
        mPinpointGroups = genMapPinpointGroups();
        // Define other aplication specific things here
    }

    private List<MaplinContext.MapSelectionGroup> genMapPinpointGroups() { // This is just for pinpoints!
        List<MaplinContext.MapSelectionGroup> mapSelectionGroups = new ArrayList<>();

        final List<String> mapPinpointNames = Arrays.asList(
                mMaplinContext.getPinpointName(0),
                mMaplinContext.getPinpointName(1),
                mMaplinContext.getPinpointName(2),
                mMaplinContext.getPinpointName(3),
                mMaplinContext.getPinpointName(4),
                mMaplinContext.getPinpointName(5)
        );

        final List<String> mapPinpointPaths = Arrays.asList(
                mMaplinContext.getPinpointImagePath(0),
                mMaplinContext.getPinpointImagePath(1),
                mMaplinContext.getPinpointImagePath(2),
                mMaplinContext.getPinpointImagePath(3),
                mMaplinContext.getPinpointImagePath(4),
                mMaplinContext.getPinpointImagePath(5)
        );

        final List<Integer> mapPinpointRes = Arrays.asList(
                R.id.map_pinpoint_selection1,
                R.id.map_pinpoint_selection2,
                R.id.map_pinpoint_selection3,
                R.id.map_pinpoint_selection4,
                R.id.map_pinpoint_selection5,
                R.id.map_pinpoint_selection6
        );
        // TODO: These should be set dynamically based on MaplinContext
        // TODO: The mapPinpointRes[] should be changed where the cooresponding path is not ""

        final List<Integer> mapPinpointRQCodes = Arrays.asList(
                1, 2, 3, 4, 5, 6
        ); // These are specific for pinpoints!

        for(int g = 0; g < 6; g++)
            mapSelectionGroups.add(new MaplinContext.MapSelectionGroup(
                    mapPinpointNames.get(g),
                    mapPinpointPaths.get(g),
                    mapPinpointRes.get(g),
                    mapPinpointRQCodes.get(g)
            ));

        return mapSelectionGroups;
    }

    protected MaplinContext mMaplinContext; // Each activity needs a context instance
    protected SharedPreferences mSharedPrefs;

    protected float mPixelDensity;
    protected List<MaplinContext.MapSelectionGroup> mPinpointGroups;
}
