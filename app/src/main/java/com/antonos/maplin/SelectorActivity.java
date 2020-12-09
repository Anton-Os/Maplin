package com.antonos.maplin;

import android.content.Context;
import android.content.DialogInterface;
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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectorActivity extends MaplinActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selector_activity);

        // -------------- Setup and Loader Operations -------------- //


        for(final MaplinContext.MapSelectionGroup group : mPinpointGroups){
            CardView cardView = findViewById(group.resourceId);
            Log.v("STATUS", "Card view resource " + group.resourceId + " found!");

            // cardView.setBackground(getDrawable(group.resourceId)); // TODO: Fix to set background

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("STATUS", "Card view selection!");
                    mFragmentTrans = getSupportFragmentManager().beginTransaction();

                    // Remove old instance to prevent any errors
                    Fragment prevFragTrans = getSupportFragmentManager().findFragmentByTag("Change Pinpoint Dialog");
                    if(prevFragTrans != null)
                        mFragmentTrans.remove(prevFragTrans);

                    mPinpointDialog = new ChangeIconDialog(group);
                    mPinpointDialog.show(mFragmentTrans, "Change Pinpoint Dialog");

                }
            });
        }

        // -------------- Main UI Operations -------------- //
        ListView mapListView = (ListView)findViewById(R.id.map_list);

        // TODO: Code below is dependent on MaplinContext configurations
        final ArrayList<String> mapList = new ArrayList<String>();
        for(int mapTargetIndex = 0; mapTargetIndex < mMaplinContext.getMapCount(); mapTargetIndex++)
            mapList.add(mMaplinContext.getMapName(mapTargetIndex));

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

    private FragmentTransaction mFragmentTrans;
    private ChangeIconDialog mPinpointDialog;
}
