package com.antonos.maplin.helper;

        import android.content.Context;
        import android.util.AttributeSet;
        import android.util.Pair;

        import java.util.ArrayList;
        import java.util.List;

public class Map {
    private String name;
    private List<Pinpoint> pinpointList = new ArrayList<Pinpoint>();

    public static class MapView extends androidx.appcompat.widget.AppCompatImageView {
        public MapView(Context context) { super(context); }

        public MapView(Context context, AttributeSet attrs){ super(context, attrs); }

        public void setBkImage(int resId) {
            setImageResource(resId);
        }
    }

    // public List<Pair> pinpointImagePairs; // TODO: Switch to ImageViewCompat
}
