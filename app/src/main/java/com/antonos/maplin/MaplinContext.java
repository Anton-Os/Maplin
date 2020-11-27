package com.antonos.maplin;

import android.content.SharedPreferences;
import android.util.Log;
import android.util.Pair;

import com.antonos.maplin.helper.Map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class MaplinContext { // TODO: Make into a singleton object

    public MaplinContext(SharedPreferences sharedPrefs){
        // TODO: Address the issue noted below using a String Set
        // TODO: Make sure that Pin, Person, Terrain, etc... are modifiable, right now they are fixed which is a problem
        final List<String> mapPinpointNames = (sharedPrefs.contains(pinpointNamesListKey)) ?
            genPinpointNames(sharedPrefs) : Arrays.asList("Pin", "Person", "Terrain", "Grass", "City", "Port"); // City and Port are to be implemented

        for(String keyName : mapPinpointNames){
            String targetNameValue = sharedPrefs.getString(pinpointNamePrefix + '_' + keyName, emptyPath);
            if(targetNameValue == emptyPath) // Means that the value was not located
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

    // -------------- Context reader functions -------------- //

    public String getPinpointPath(int index){
        assert(index < pinpointIconCount);
        return mNamedPinpoints.get(index).second;
    }

    List<String> genPinpointNames(SharedPreferences sharedPrefs){
        Set<String> namesSet = sharedPrefs.getStringSet(pinpointNamesListKey, Collections.<String>emptySet());

        // TODO: Body needs to be implemented

        return null;
    }

    // -------------- Context update functions -------------- //

    // Only updates paths for pinpoint icons
    public void updatePinpointPaths(SharedPreferences sharedPrefs, ArrayList<Pair<String, String>> newPaths){
        assert(newPaths.size() == pinpointIconCount);
        mNamedPinpoints = newPaths; // Replaces old with the new

        updateSharedPrefs(sharedPrefs.edit());
        return;
    }

    // For adding a new map to mNamedMaps list
    public void saveNewMap(SharedPreferences sharedPrefs, Pair<String, Map> newMap){
        mNamedMaps.add(newMap);

        updateSharedPrefs(sharedPrefs.edit());
        return;
    }

    // For deleting a map from mNamedMaps list
    public void deleteMap(SharedPreferences sharedPrefs, String mapNameTarget){
        Boolean targetMapFound = false;
        for(Pair<String, Map> namedMap : mNamedMaps)
            if(namedMap.first == mapNameTarget){
                targetMapFound = true;
                mNamedMaps.remove(namedMap); // Interesting java feature!
            }
        if(!targetMapFound) return; // Nothing should occur further if map is not found

        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.remove(mapNameTarget);

        return;
    }

    private void updateSharedPrefs(SharedPreferences.Editor editor){
        // TODO: Make sure to update the pinpointNamedListKey within sharedPreferences

        for(Pair<String, String> namedPinpoint : mNamedPinpoints)
            editor.putString(pinpointNamePrefix + '_' + namedPinpoint.first, namedPinpoint.second);

        for(Pair<String, Map> namedMap : mNamedMaps){
            // TODO: Implement a json file, Maps are more complex objects
            String defaultFilePath = "cutom_filepath_here.json";
            editor.putString(namedMap.first, defaultFilePath); // TODO: Fix this!
        }

        editor.commit(); // Saves changes to shared preferences
        return;
    }

    public static final String emptyPath = "";
    public static final String sharedPrefsFile = "com.antonos.maplin.sharedprefs";
    public static final int pinpointIconCount = 6;
    private static final String pinpointNamePrefix = "Pin";
    private static final String pinpointNamesListKey = '_' + pinpointNamePrefix + '_' + "Keys";
    private static int userVisitsCount; // Implement later

    private static ArrayList<Pair<String, String>> mNamedPinpoints = new ArrayList<Pair<String, String>>(); // Matches a name to an image path
    // TODO: Implement this functionality to load up maps
    private static List<Pair<String, Map>> mNamedMaps; // Matches a name to all saved maps
}
