package com.antonos.maplin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ChangeIconDialog extends DialogFragment {
    ChangeIconDialog(MaplinContext.MapSelectionGroup targetSelectGroup){
        // TODO: Get targetSelectGroup properties and structure this class accordingly

        mSelectGroup = targetSelectGroup;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.change_icon_dialog, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        changeIconEditText = (EditText)view.findViewById(R.id.change_icon_edit_text);
        changeIconEditText.setText(mSelectGroup.name);

        final ImageView changeIconImageView = (ImageView)view.findViewById(R.id.change_icon_image);
        if(mSelectGroup.path == MaplinContext.emptyPath) changeIconImageView.setImageResource(mSelectGroup.defaultImgRes);

        Button loadPresetButton = (Button)view.findViewById(R.id.load_preset_button);
        loadPresetButton.setOnClickListener( new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rollback to the previous icon
                mSelectGroup.path = MaplinContext.emptyPath;
                changeIconImageView.setImageResource(mSelectGroup.defaultImgRes);

                // TODO: Possibly update the MaplinContext through a static call
            }
        });

        Button changeIconBrowseButton = (Button)view.findViewById(R.id.change_icon_browse_button);
        changeIconBrowseButton.setOnClickListener( new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Implement an intent to pick an image from the gallery

                // TODO: Possibly update the MaplinContext through a static call
            }
        });

        return;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);

        Log.v("STATUS", "Pinpoint dialog dismissed!");
    }

    EditText changeIconEditText; // Need a saved instance
    MaplinContext.MapSelectionGroup mSelectGroup;
}
