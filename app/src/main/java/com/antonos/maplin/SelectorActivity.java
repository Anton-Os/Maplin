package com.antonos.maplin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selector_activity);

        // -------------- Setup and Loader Operations -------------- //
        sharedPrefs = getApplicationContext().getSharedPreferences(MaplinContext.sharedPrefsFile, getApplicationContext().MODE_PRIVATE);
        maplinContext = new MaplinContext(sharedPrefs);

        mMapPinpointGroup = genMapPinpointGroup();
        for(MaplinContext.MapSelectionGroup group : mMapPinpointGroup){
            CardView cardView = findViewById(group.resourceId);
            Log.v("STATUS", "Card view resource " + group.resourceId + " found!");

            // cardView.setBackground(getDrawable(group.resourceId)); // TODO: Fix to set background

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("STATUS", "Card view selection!");

                    // TODO: Include ChangeIconDialog class!
                }
            });
        }

        // -------------- Main UI Operations -------------- //
        ListView mapListView = (ListView)findViewById(R.id.map_list);

        // TODO: Code below is dependent on MaplinContext configurations
        final ArrayList<String> mapList = new ArrayList<String>();
        mapList.add("Global Map");
        mapList.add("US Map");
        mapList.add("Map 3");

        MapList_Adapter mapList_adapter = new MapList_Adapter(this, R.layout.map_list_item, R.id.map_item_text, mapList);
        mapListView.setAdapter(mapList_adapter);

        Button createButton = (Button)findViewById(R.id.create_map_button);
        createButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create new map screen
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        // TODO: Modify the shared preferences file
    }

    // Adapter for the the list mapListView instance
    private class MapList_Adapter extends ArrayAdapter<String> { // Stable unchanging adapter for options
        public MapList_Adapter(@NonNull Context context, int resourceId, int textViewResourceId, @NonNull List<String> objects) {
            super(context, resourceId, textViewResourceId, objects);
        }

        @Override
        public boolean hasStableIds() { return true; }
    }

    // Generates a list of pinpoints specific to the SelectorActivity and selector_activity.xml layout
    private List<MaplinContext.MapSelectionGroup> genMapPinpointGroup() { // This is just for pinpoints!
        List<MaplinContext.MapSelectionGroup> mapSelectionGroups = new ArrayList<>();

        final List<String> mapPinpointPaths = Arrays.asList(
            maplinContext.getPinpointImagePath(0),
            maplinContext.getPinpointImagePath(1),
            maplinContext.getPinpointImagePath(2),
            maplinContext.getPinpointImagePath(3),
            maplinContext.getPinpointImagePath(4),
            maplinContext.getPinpointImagePath(5)
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
                    mapPinpointPaths.get(g),
                    mapPinpointRes.get(g),
                    mapPinpointRQCodes.get(g)
            ));
        
        return mapSelectionGroups;
    }
    
    public MaplinContext maplinContext; // Each activity needs a context instance
    public SharedPreferences sharedPrefs;

    public List<MaplinContext.MapSelectionGroup> mMapPinpointGroup;
}
