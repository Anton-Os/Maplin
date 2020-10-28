package com.antonos.maplin;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.util.Log;
import android.util.Pair;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.antonos.maplin.helper.Pinpoint;
import com.antonos.maplin.helper.Map;
import com.google.android.material.snackbar.Snackbar;

public class EditorActivity extends AppCompatActivity {
// implements View.OnTouchListener, View.OnDragListener {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_activity);

        // -------------- Setup and Loader Operations -------------- // TODO: Include code to load saved states
        startupNotify();

        // -------------- Main UI Operations -------------- //
        frameLayout = findViewById(R.id.map_frame_layout);
        mapView = findViewById(R.id.map_view);
        mapView.setBkImage(R.drawable.larger_beans);

        final Bitmap defaultPinImg = BitmapFactory.decodeResource(getResources(), R.drawable.plus);
        mapView.setOnTouchListener(new View.OnTouchListener() { // On Touch Listener specific to MapView
            @RequiresApi(api = Build.VERSION_CODES.N) // TODO: Update to Android Version 24
            @Override
            public boolean onTouch(View view, @SuppressLint("ClickableViewAccessibility") MotionEvent event) {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    Log.v("STATUS", "Down Action!");
                    return true;
                case MotionEvent.ACTION_UP: // TODO: Check how long button pressed
                    Pinpoint pinpoint = new Pinpoint(getDrawable(R.drawable.baseline_pin_drop_black_18dp), event.getX(), event.getY());

                    Log.v("STATUS", "Up Action!");
                    ImageView pinpointIconView = new ImageView(getApplicationContext());
                    pinpointIconView.setLayoutParams(new ViewGroup.LayoutParams(pinpointIconWidth, pinpointIconHeight));
                    pinpointIconView.setX(event.getX() + mapView.getTranslationX() - (float)pinpointIconWidth / 2.0f);
                    pinpointIconView.setY(event.getY() + mapView.getTranslationY() - (float)pinpointIconHeight);
                    // pinpointIconView.setBackgroundColor(0x1100FFFF);
                    pinpointIconView.setImageResource(R.drawable.baseline_pin_drop_black_18dp);

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
                switch(event.getAction()){
                    case DragEvent.ACTION_DRAG_STARTED:
                        Log.v("STATUS", "Drag Started");
                        view.invalidate();
                        return true;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        // TODO: Fix the blocky movement that happens here
                        mapView.setX(event.getX());
                        mapView.setY(event.getY());
                        view.invalidate();
                        return true;
                    case DragEvent.ACTION_DRAG_ENDED:
                        Log.v("STATUS", "Drag Ended");
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
                    Snackbar.make(view, "Switched to pinpoint mode", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        );
    }

    private void startupNotify(){ // Notification experiment
        String channeIdStr = "maplin_channel_N1";
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, channeIdStr);
        notifyBuilder.setContentTitle("Maplin").setContentText("Welcome to Maplin!");
        notifyBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        // TODO: Set small icon above! Use intent perhaps!
        // NotificationManagerCompat notifyMan = (NotificationManagerCompat)getSystemService(NOTIFICATION_SERVICE);
        // NotificationManagerCompat.from(this).notify(1, notifyBuilder.build());
    }

    public FrameLayout frameLayout;
    public Map map; // New map wrapper class
    public Map.MapView mapView; // Use this to display target map // TODO: Replace with com.antonos.maplin.Map
    public GLRenderView renderView; // Experimental overlay for graphics

    private final int pinpointIconWidth = 70;
    private final int pinpointIconHeight = 70;
    // Intent selectorActivity_intent = new Intent(EditorActivity.this, SelectorActivity.class);
}