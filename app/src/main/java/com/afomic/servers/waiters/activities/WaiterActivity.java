package com.afomic.servers.waiters.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.afomic.servers.R;
import com.afomic.servers.adapter.TableListAdapter;
import com.afomic.servers.data.Constants;
import com.afomic.servers.model.Table;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class WaiterActivity extends AppCompatActivity  implements TableListAdapter.TableItemListener {
    RecyclerView TableList;
    RelativeLayout emptyViewLayout;
    DatabaseReference mDatabase;
    TableListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_waiter);
        mDatabase= FirebaseDatabase.getInstance().getReference("events").child("tables");

        TableList=(RecyclerView) findViewById(R.id.rv_table_list);
        emptyViewLayout=(RelativeLayout) findViewById(R.id.empty_view) ;

        final ArrayList<Table> mTables=new ArrayList<>();

        mAdapter=new TableListAdapter(this,mTables);

        RecyclerView.LayoutManager mManager= new LinearLayoutManager(this);
        TableList.setLayoutManager(mManager);
        TableList.setAdapter(mAdapter);

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Table mTable=dataSnapshot.getValue(Table.class);
                mTables.add(mTable);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(Table table) {
        Intent intent = new Intent(WaiterActivity.this, FoodOrderActivity.class);
        intent.putExtra(Constants.BUNDLE_TABLE, table);
        startActivity(intent);
    }
}
