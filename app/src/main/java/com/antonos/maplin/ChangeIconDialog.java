package com.antonos.maplin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        EditText changeIconEditText = (EditText)view.findViewById(R.id.change_icon_edit_text);
        changeIconEditText.setText(mSelectGroup.name);

        ImageView changeIconImageView = (ImageView)view.findViewById(R.id.change_icon_image);
        // if(mSelectGroup.path == MaplinContext.emptyPath) changeIconImageView.setImageResource(mDefaultResId);
        // TODO: Implement path correctly

        return;
    }

    MaplinContext.MapSelectionGroup mSelectGroup;
}
