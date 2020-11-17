package com.antonos.maplin;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SelectorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selector_activity);

        // RecyclerView rcyclMapList = (RecyclerView)findViewById(R.id.rcycl_map_list);
        ListView mapListView = (ListView)findViewById(R.id.map_list);

        final ArrayList<String> mapList = new ArrayList<String>();
        mapList.add("Global Map");
        mapList.add("US Map");
        mapList.add("Map 3");

        // TODO: Instance of adapter
        MapList_Adapter mapList_adapter = new MapList_Adapter(this, R.layout.map_list_item, R.id.map_item_text, mapList);
        mapListView.setAdapter(mapList_adapter);
    }

    private class MapList_Adapter extends ArrayAdapter<String> { // Stable unchanging adapter for options
        public MapList_Adapter(@NonNull Context context, int resourceId, int textViewResourceId, @NonNull List<String> objects) {
            super(context, resourceId, textViewResourceId, objects);
        }

        @Override
        public boolean hasStableIds() { return true; }

        private List<ImageButton> deleteButtons; // Attempting to query all
    }
}
