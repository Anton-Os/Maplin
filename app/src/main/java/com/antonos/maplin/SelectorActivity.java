package com.antonos.maplin;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SelectorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selector_activity);

        ListView mapListView = (ListView)findViewById(R.id.map_list);
        // List<String> mapNamesStr = new ArrayList<String>();
        ArrayAdapter mapListAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.selector_activity);
        mapListView.setAdapter(mapListAdapter);
    }
}
