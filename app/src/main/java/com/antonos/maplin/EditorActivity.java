package com.antonos.maplin;

import android.annotation.SuppressLint;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ScaleGestureDetectorCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

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
        startupNotify();

        // -------------- Main UI Operations -------------- //
        frameLayout = findViewById(R.id.map_frame_layout);
        mapView = findViewById(R.id.map_view);
        mapView.setBkImage(R.drawable.larger_beans);

        final Bitmap defaultPinImg = BitmapFactory.decodeResource(getResources(), R.drawable.plus);
        mapView.setOnTouchListener(new View.OnTouchListener() { // On Touch Listener specific to MapView
            @RequiresApi(api = Build.VERSION_CODES.N) // TODO: Update to Android Version 24
            @Override
            public boolean onTouch(View v, @SuppressLint("ClickableViewAccessibility") MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.v("STATUS", "Down Action!");
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                        v.startDragAndDrop(null, shadowBuilder, v, 0);
                        return true;
                    case MotionEvent.ACTION_UP: // TODO: Check how long button pressed
                        Pinpoint pinpoint = new Pinpoint(getDrawable(R.drawable.baseline_pin_drop_black_18dp), event.getX(), event.getY());

                        Log.v("STATUS", "Up Action!");
                        ImageView pinpointIconView = new ImageView(getApplicationContext());
                        pinpointIconView.setLayoutParams(new ViewGroup.LayoutParams(pinpointIconWidth, pinpointIconHeight));
                        pinpointIconView.setX(event.getX());
                        pinpointIconView.setY(event.getY());
                        pinpointIconView.setBackgroundColor(0x1100FFFF);
                        pinpointIconView.setImageResource(R.drawable.baseline_pin_drop_black_18dp);

                        frameLayout.addView(pinpointIconView);
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        Log.v("STATUS", "Move action!");
                        /* mapView.setX(event.getX());
                        mapView.setY(event.getY()); */ // Blocky movement, replace
                        return true;
                    default:
                        return false;
                }
            }
        });
        mapView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                Log.v("STATUS","Drag Success!");
                return false;
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
                    // TODO: Implement snackbar later
                    /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show(); */
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