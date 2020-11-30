package com.antonos.maplin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class ChangeIconDialog extends DialogFragment {
    ChangeIconDialog(MaplinContext.MapSelectionGroup targetSelectGroup){
        // TODO: Get targetSelectGroup properties and structure this class accordingly
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // TODO: Set builder properties here

        return builder.create();
    }
}
