package com.antonos.maplin;

import android.annotation.SuppressLint;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ScaleGestureDetectorCompat;

import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
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
        // defaultPinImg.setHeight(maxImgHeight); // Mutable Bitmap?
        // defaultPinImg.setWidth(maxImgWidth); // Mutable Bitmap?
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
                        Pinpoint pinpoint = new Pinpoint(defaultPinImg, event.getX(), event.getY());

                        Log.v("STATUS", "Up Action!");
                        ImageView pinpointIconView = new ImageView(getApplicationContext());
                        pinpointIconView.setBackgroundColor(0xFF00FFFF);
                        // pinpointIconView.setPadding(100, 100, 100, 100);
                        pinpointIconView.setX(event.getX() - (float)defaultPinImg.getWidth());
                        pinpointIconView.setY(event.getY() - (float)defaultPinImg.getHeight());
                        pinpointIconView.setImageBitmap(pinpoint.getIcon());

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
        //mapView.setOnDragListener(new MapView.MapDragListener()); // TODO: Attempt to add the drag listener within this activity

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) { return true; }
        /* if (id == R.id.action_add) { return true;} */
        return super.onOptionsItemSelected(item);
    }

    private void startupNotify(){
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
    public MapView mapView; // Use this to display target map // TODO: Replace with com.antonos.maplin.Map
    public GLRenderView renderView; // Experimental overlay for graphics

    private final int maxImgWidth = 10;
    private final int maxImgHeight = 10;
    // Intent selectorActivity_intent = new Intent(EditorActivity.this, SelectorActivity.class);
    // public Drawable defaultDrawable;
}