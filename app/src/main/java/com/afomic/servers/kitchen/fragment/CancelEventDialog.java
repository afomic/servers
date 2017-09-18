package com.afomic.servers.kitchen.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.afomic.servers.kitchen.activities.EventSummaryActivity;

/**
 * Created by rechael on 9/18/2017.
 */

public class CancelEventDialog extends DialogFragment {
    public static CancelEventDialog newInstance(){
        CancelEventDialog mDialog=new CancelEventDialog();
        return mDialog;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(getContext());
        mBuilder.setTitle("Are you Sure?");
        mBuilder.setPositiveButton("Exit Event", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent mIntent=new Intent(getActivity(), EventSummaryActivity.class);
                getActivity().startActivity(mIntent);
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
