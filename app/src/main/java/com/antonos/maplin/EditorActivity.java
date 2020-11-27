package com.antonos.maplin;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ScaleGestureDetectorCompat;

import android.util.Log;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.antonos.maplin.helper.Pinpoint;
import com.antonos.maplin.helper.Map;

public class EditorActivity extends AppCompatActivity {
// implements View.OnTouchListener, View.OnDragListener {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_activity);

        // -------------- Setup and Loader Operations -------------- // TODO: Include code to load saved states
        sharedPrefs = getApplicationContext().getSharedPreferences(MaplinContext.sharedPrefsFile, getApplicationContext().MODE_PRIVATE);
        maplinContext = new MaplinContext(sharedPrefs);
        // startupNotify();

        map = new Map("Starter Map", R.drawable.us_map_3); // Named Map object with a base image

        // -------------- Main UI Operations -------------- //
        frameLayout = findViewById(R.id.map_frame_layout);
        mapView = findViewById(R.id.map_view);
        mapView.setBkImage(map.getBasemapImageRes());

        pinpointResId = R.drawable.baseline_pin_drop_black_18dp; // Sets the default value
        // TODO: Implement a custom scale gesture listener
        final ScaleGestureDetector scaleGestureDetector = new ScaleGestureDetector(getApplicationContext(), new ScaleGestureDetector.SimpleOnScaleGestureListener());
        mapView.setOnTouchListener(new View.OnTouchListener() { // On Touch Listener specific to MapView
            @RequiresApi(api = Build.VERSION_CODES.N) // TODO: Update to Android Version 24
            @Override
            public boolean onTouch(View view, @SuppressLint("ClickableViewAccessibility") MotionEvent event) {
                scaleGestureDetector.onTouchEvent(event);

                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.v("STATUS", "Down Action!");
                        lastEventX = event.getX();
                        lastEventY = event.getY();
                        return true;
                    case MotionEvent.ACTION_UP: // TODO: Check how long button pressed
                        Pinpoint pinpoint = new Pinpoint(getDrawable(pinpointResId), event.getX(), event.getY());
                        map.addPinpoint(pinpoint);

                        Log.v("STATUS", "Up Action!");
                        ImageView pinpointIconView = new ImageView(getApplicationContext());
                        pinpointIconView.setLayoutParams(new ViewGroup.LayoutParams(pinpointIconWidth, pinpointIconHeight));
                        pinpointIconView.setX(event.getX() + mapView.getTranslationX() - (float)pinpointIconWidth / 2.0f);
                        pinpointIconView.setY(event.getY() + mapView.getTranslationY() - (float)pinpointIconHeight);
                        // pinpointIconView.setBackgroundColor(0x1100FFFF); // Optional background color
                        pinpointIconView.setImageDrawable(pinpoint.getIcon());

                        frameLayout.addView(pinpointIconView);
                        // map.pinpointImagePairs.add(new Pair(pinpoint, pinpointIconView));
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        Log.v("STATUS", "Move action!");
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder();
                        view.startDragAndDrop(null, shadowBuilder, view, 0);
                        return true;

                    default:
                        return false;
                }
            }
        });
        mapView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent event) {
                float translationX = -1 * (lastEventX - event.getX()) / dragDamping;
                float translationY = -1 * (lastEventY - event.getY()) / dragDamping;

                switch(event.getAction()){
                    case DragEvent.ACTION_DRAG_STARTED:
                        Log.v("STATUS", "Drag Started");
                        view.invalidate();
                        return true;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        mapView.setTranslationX(translationX);
                        mapView.setTranslationY(translationY);

                        view.invalidate();
                        return true;
                    case DragEvent.ACTION_DRAG_ENDED:
                        Log.v("STATUS", "Drag Ended");

                        int pinpointCount = frameLayout.getChildCount();
                        // Start offset at 1 so the map view is unaffected
                        for(int viewIndex = 1; viewIndex < frameLayout.getChildCount(); viewIndex++) {
                            View currentView = frameLayout.getChildAt(viewIndex);
                            currentView.setTranslationX(currentView.getX() + translationX);
                            currentView.setTranslationY(currentView.getY() + translationY);
                        }

                        lastEventX = event.getX();
                        lastEventY = event.getY();

                        view.invalidate();
                        return true;
                    default:
                        return false;
                }
            }
        }); // TODO: Attempt to add the drag listener within this activity

        // -------------- Floating Action Button and Menu Operations -------------- //
        FloatingActionButton settingsFAB = (FloatingActionButton) findViewById(R.id.FAB_settings);
        settingsFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent selectorActivity_intent = new Intent(EditorActivity.this, SelectorActivity.class);
            startActivity(selectorActivity_intent); // Switch screens to the selector activity
            }
        });

        FloatingActionButton addFAB = (FloatingActionButton) findViewById(R.id.FAB_add);
        addFAB.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    // TODO: Change the type of map feature type
                    // TODO: Change menu items
                }
            }
        );

        BottomAppBar bottomAppBar = (BottomAppBar) findViewById(R.id.bottom_app_bar);
        bottomAppBar.setOnMenuItemClickListener(new BottomAppBar.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.map_edit_slot1:
                        pinpointResId = R.drawable.baseline_pin_drop_black_18dp;
                        Log.v("STATUS", "Work!");
                        Toast.makeText(getApplicationContext(), "Switched to pin drop!", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.map_edit_slot2:
                        pinpointResId = R.drawable.baseline_person_pin_circle_black_18dp;
                        Toast.makeText(getApplicationContext(), "Switched to person drop!", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.map_edit_slot3:
                        pinpointResId = R.drawable.baseline_terrain_black_18dp;
                        Toast.makeText(getApplicationContext(), "Switched to terrain drop!", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.map_edit_slot4:
                        pinpointResId = R.drawable.baseline_grass_black_18dp;
                        Toast.makeText(getApplicationContext(), "Switched to grass drop!", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return true;
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        // TODO: Modify the shared preferences file
    }

    // Experimental method for notifications
    private void startupNotify(){ // Notification experiment
        String channeIdStr = "maplin_channel_N1";
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, channeIdStr);
        notifyBuilder.setContentTitle("Maplin").setContentText("Welcome to Maplin!");
        notifyBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        // TODO: Set small icon above! Use intent perhaps!
        // NotificationManagerCompat notifyMan = (NotificationManagerCompat)getSystemService(NOTIFICATION_SERVICE);
        // NotificationManagerCompat.from(this).notify(1, notifyBuilder.build());
    }

    public MaplinContext maplinContext; // Each activity needs a context instance
    public SharedPreferences sharedPrefs;

    public FrameLayout frameLayout;
    public Map map; // New map wrapper class
    public Map.MapView mapView; // Use this to display target map // TODO: Replace with com.antonos.maplin.Map
    public GLRenderView renderView; // Experimental overlay for graphics
    // public ScaleGestureDetectorCompat scaleGestureDetector;

    private int pinpointResId;
    private float lastEventX, lastEventY; // Used for motion detection
    private final float dragDropThresh = 100.0f; // Threshold value to resist movement
    private final float dragDamping = 3.0f;
    private final int pinpointIconWidth = 70; // TODO: Make sure this parameter is scalable
    private final int pinpointIconHeight = 70; // TODO: Make sure this parameter is scalable
}