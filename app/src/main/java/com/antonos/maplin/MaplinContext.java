package com.antonos.maplin;

import android.content.SharedPreferences;
import android.util.Pair;

import com.antonos.maplin.helper.Map;

import java.util.ArrayList;
import java.util.List;

public class MaplinContext {
    public MaplinContext(){
        // Loads info using default values
    }

    public MaplinContext(SharedPreferences sharedPrefs){
        // Loads icons and such using sharedPrefs, save for later
    }

    private final int pinpointIconCount = 6;
    private ArrayList<Pair<String, Integer>> pinpointNamedIcons; // Matches a name to an image resource
    private List<Pair<String, Map>> maps; // Matches a name to all saved maps
}
