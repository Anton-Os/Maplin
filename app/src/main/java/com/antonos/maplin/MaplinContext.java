package com.antonos.maplin;

import android.content.SharedPreferences;
import android.util.Log;
import android.util.Pair;

import com.antonos.maplin.helper.Map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaplinContext {

    public MaplinContext(SharedPreferences sharedPrefs){
        SharedPreferences.Editor sharedPrefsEditor = sharedPrefs.edit();

        final List<String> mapIconNames = Arrays.asList(
                "MapIconImage1",
                "MapIconImage2",
                "MapIconImage3",
                "MapIconImage4",
                "MapIconImage5",
                "MapIconImage6"
        );

        final String defaultNameValue = "";
        for(String keyName : mapIconNames){
            String targetNameValue = sharedPrefs.getString(keyName, defaultNameValue);
            if(targetNameValue == defaultNameValue) // Means that the value was not located
                Log.v("STATUS", "Name not found in shared prefs!"); // TODO: Load a default image path using editor
        }
    }

    public static final String sharedPrefsFile = "com.antonos.maplin.sharedprefs";
    private static final int pinpointIconCount = 6;
    private static ArrayList<Pair<String, Integer>> pinpointNamedIcons; // Matches a name to an image resource
    private static List<Pair<String, Map>> maps; // Matches a name to all saved maps
}
