package com.antonos.maplin;

import android.annotation.SuppressLint;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.antonos.maplin.helper.Pinpoint;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ScaleGestureDetectorCompat;

import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class EditorActivity extends AppCompatActivity {
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_activity);

        // Setup Operations TODO: Include code to load saved states
        String channeIdStr = "maplin_channel_N1";
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, channeIdStr);
        notifyBuilder.setContentTitle("Maplin").setContentText("Welcome to Maplin!");
        notifyBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        // TODO: Set small icon above! Use intent perhaps!
        // NotificationManagerCompat notifyMan = (NotificationManagerCompat)getSystemService(NOTIFICATION_SERVICE);
        // NotificationManagerCompat.from(this).notify(1, notifyBuilder.build());

        // View and UI Component Operations
        mapView = findViewById(R.id.map_view);
        mapView.setBkImage(R.drawable.larger_beans);
        /* mapView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                if(event.getAction() == DragEvent.ACTION_DRAG_ENTERED) Log.v("STATUS", "Drag entered!");
                else if(event.getAction() == DragEvent.ACTION_DRAG_EXITED) Log.v("STATUS", "Drag exited!");
                return false;
            }
        }); */

        final Bitmap defaultPinpointImg = BitmapFactory.decodeResource(getResources(), R.drawable.plus);
        mapView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, @SuppressLint("ClickableViewAccessibility") MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Pinpoint pinpoint = new Pinpoint(defaultPinpointImg, event.getX(), event.getY());
                        mapView.addPinpoint(pinpoint);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.v("STATUS", "Dragging movement detected!");
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        // Floating Action Button and Menu Operations
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

    public MapView mapView; // Use this to display target map
    public GLRenderView renderView; // Experimental overlay for graphics
    // Intent selectorActivity_intent = new Intent(EditorActivity.this, SelectorActivity.class);
    // public Drawable defaultDrawable;
}