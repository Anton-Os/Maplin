package com.antonos.maplin;

import android.content.SharedPreferences;
import android.util.Log;
import android.util.Pair;

import com.antonos.maplin.helper.Map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaplinContext { // TODO: Make into a singleton object

    public MaplinContext(SharedPreferences sharedPrefs){
        final List<String> mapPinpointNames = Arrays.asList(
                "MapPinpointImage1",
                "MapPinpointImage2",
                "MapPinpointImage3",
                "MapPinpointImage4",
                "MapPinpointImage5",
                "MapPinpointImage6"
        );

        final String defaultNameValue = emptyPath;
        for(String keyName : mapPinpointNames){
            String targetNameValue = sharedPrefs.getString(keyName, defaultNameValue);
            if(targetNameValue == defaultNameValue) // Means that the value was not located
                Log.v("STATUS", "Name not found in shared prefs!"); // TODO: Load a default image path using editor

            mNamedPinpoints.add(new Pair<String, String>(keyName, targetNameValue)); // Create a pair
        }
    }

    public static class MapSelectionGroup { // Group of values that identify a map element and its relevant properties
        MapSelectionGroup(String p, int rs, int rc){
            path = p; resourceId = rs; requestCode = rc;
        }
        public String path; // Maplin specific
        public int resourceId; // Specific to XML layout file
        public int requestCode; // Specific to calling Activity
    }

    public String getPinpointPath(int index){
        assert(index < pinpointIconCount);
        return mNamedPinpoints.get(index).second;
    }

    // Only updates paths for pinpoint icons
    public void updatePinpointPaths(SharedPreferences sharedPrefs, List<String> newPaths){
        SharedPreferences.Editor sharedPrefsEditor = sharedPrefs.edit();

        // TODO: All update code should go here

        return;
    }

    public static final String emptyPath = "";
    public static final String sharedPrefsFile = "com.antonos.maplin.sharedprefs";
    private static final int pinpointIconCount = 6;
    private static ArrayList<Pair<String, String>> mNamedPinpoints = new ArrayList<Pair<String, String>>(); // Matches a name to an image resource

    // TODO: Implement this functionality to load up maps
    private static List<Pair<String, Map>> mMaps; // Matches a name to all saved maps
}
