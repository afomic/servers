package com.afomic.servers.kitchen.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.afomic.servers.data.Constants;
import com.afomic.servers.model.Table;

/**
 * Created by rechael on 9/16/2017.
 *
 */

public class ConfirmTableServed extends DialogFragment {
    Table mTable;
    public static ConfirmTableServed getInstance(Table table){
        Bundle args=new Bundle();
        args.putParcelable(Constants.BUNDLE_TABLE,table);
        ConfirmTableServed dialog=new ConfirmTableServed();
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTable=getArguments().getParcelable(Constants.BUNDLE_TABLE);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(getContext());
        mBuilder.setTitle("Are you sure?");
        mBuilder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });
        mBuilder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });
        return mBuilder.create();
    }
}
