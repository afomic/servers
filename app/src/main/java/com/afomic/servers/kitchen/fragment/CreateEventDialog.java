package com.afomic.servers.kitchen.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.afomic.servers.R;

/**
 * Created by rechael on 9/16/2017.
 *

 */

public class CreateEventDialog extends DialogFragment {
    EditText mEventNameEditText,mTableNumberEditText;
    public static CreateEventDialog getInstance(){
        return new CreateEventDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(getContext());
        View v= LayoutInflater.from(getContext()).inflate(R.layout.create_event_layout,null);
        mEventNameEditText=(EditText) v.findViewById(R.id.edt_event_name);
        mTableNumberEditText=(EditText) v.findViewById(R.id.edt_number_of_table);
        mBuilder.setTitle("Create Event");
        mBuilder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });
        mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });
        mBuilder.setView(v);
        return mBuilder.create();
    }
}
