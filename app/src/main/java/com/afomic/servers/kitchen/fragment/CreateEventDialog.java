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
import com.afomic.servers.model.Table;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by rechael on 9/16/2017.
 */

public class CreateEventDialog extends DialogFragment {
    EditText mEventNameEditText, mTableNumberEditText;
    DatabaseReference mDatabaseReference;

    public static CreateEventDialog getInstance() {
        return new CreateEventDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
        View v = LayoutInflater.from(getContext()).inflate(R.layout.create_event_layout, null);
        mEventNameEditText = (EditText) v.findViewById(R.id.edt_event_name);
        mTableNumberEditText = (EditText) v.findViewById(R.id.edt_number_of_table);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("events");
        mBuilder.setTitle("Create Event");
        mBuilder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String eventName = mEventNameEditText.getText().toString();
                String tableNumber = mTableNumberEditText.getText().toString();
                int num = Integer.parseInt(tableNumber);
                //createTables(num);
                createTablesRash(num);
                mDatabaseReference.child("name").setValue(eventName);
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

    public void createTables(int num){
        DatabaseReference tablesDatabaseRef=mDatabaseReference.child("tables");
        for(int i=1;i<=num;i++){
            String key=tablesDatabaseRef.push().getKey();
            String tableName="Table "+i;
            Table mTable=new Table(key,tableName);
            tablesDatabaseRef.child(key).setValue(mTable);
        }
    }

    //Proposed implementation
    public void createTablesRash(int num) {

        DatabaseReference tablesDatabaseRef = mDatabaseReference.child("tables");
        tablesDatabaseRef.setValue(null);
        for (int i = 1; i <= num; i++) {
            String tableName = "Table " + i;
            Table mTable = new Table("N/A", tableName);
            tablesDatabaseRef.child(tableName).setValue(mTable);
        }

    }
}
