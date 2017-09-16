package com.afomic.servers.kitchen.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by rechael on 9/16/2017.
 */

public class CreateEventDialog extends DialogFragment {
    public static CreateEventDialog getInstance(){
        return new CreateEventDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(getContext());
        return mBuilder.create();
    }
}
