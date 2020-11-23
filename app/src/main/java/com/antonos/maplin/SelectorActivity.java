package com.antonos.maplin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

        sharedPrefs = getApplicationContext().getSharedPreferences(MaplinContext.sharedPrefsFile, getApplicationContext().MODE_PRIVATE);
        maplinContext = new MaplinContext(sharedPrefs);

        final List<Integer> mapIconRes = Arrays.asList(
            R.id.map_icon_selection1,
            R.id.map_icon_selection2,
            R.id.map_icon_selection3,
            R.id.map_icon_selection4,
            R.id.map_icon_selection5,
            R.id.map_icon_selection6
        );
        for(Integer resource : mapIconRes){
            CardView cardView = findViewById(resource);
            Log.v("STATUS", "Card view found!");
            // TODO: Set the appropriate resource for each card view
        }

        // RecyclerView rcyclMapList = (RecyclerView)findViewById(R.id.rcycl_map_list);
        ListView mapListView = (ListView)findViewById(R.id.map_list);

        // TODO: Code below is dependent on MaplinContext configurations
        final ArrayList<String> mapList = new ArrayList<String>();
        mapList.add("Global Map");
        mapList.add("US Map");
        mapList.add("Map 3");

        MapList_Adapter mapList_adapter = new MapList_Adapter(this, R.layout.map_list_item, R.id.map_item_text, mapList);
        mapListView.setAdapter(mapList_adapter);
    }

    private class MapList_Adapter extends ArrayAdapter<String> { // Stable unchanging adapter for options
        public MapList_Adapter(@NonNull Context context, int resourceId, int textViewResourceId, @NonNull List<String> objects) {
            super(context, resourceId, textViewResourceId, objects);
        }

        /* @Override
        public View getView(final int position, View convertView, ViewGroup parent){
            convertView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    // See what happens
                    Log.v("STATUS", String.valueOf(position));
                    return;
                }
            });
            return convertView;
        } */

        @Override
        public boolean hasStableIds() { return true; }
    }

    public MaplinContext maplinContext; // Each activity needs a context instance
    public SharedPreferences sharedPrefs;
}
