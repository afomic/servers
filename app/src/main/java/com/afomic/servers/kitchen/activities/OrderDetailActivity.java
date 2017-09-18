package com.afomic.servers.kitchen.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.afomic.servers.R;
import com.afomic.servers.adapter.OrderListAdapter;
import com.afomic.servers.data.Constants;
import com.afomic.servers.kitchen.fragment.ConfirmTableServed;
import com.afomic.servers.model.Order;
import com.afomic.servers.model.Table;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity implements OrderListAdapter.OrderCheckboxListener {
    RecyclerView orderList;
    Table mTable;
    FloatingActionButton mOrderServed;
    DatabaseReference mDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        //get table from intent
        mTable = getIntent().getParcelableExtra(Constants.BUNDLE_TABLE);

        //Your previous implementation
  /*      mDatabaseReference= FirebaseDatabase.getInstance()
                .getReference("events")
                .child("tables")
                .child(mTable.getKey());*/

        //proposed solution
        //check you were trying to cast all children of a particular table to Order.
        //instead i entered a particular order here
        mDatabaseReference= FirebaseDatabase.getInstance()
                .getReference("events")
                .child("tables")
                .child(mTable.getKey())
                .child("orders");

        TextView orderTitle=(TextView) findViewById(R.id.tv_order_title);
        orderTitle.setText(mTable.getName() + " Orders");
        orderList = (RecyclerView) findViewById(R.id.rv_order_list);

        RecyclerView.LayoutManager mManager=new LinearLayoutManager(this);
        orderList.setLayoutManager(mManager);
        final ArrayList<Order> mOrders=new ArrayList<>();
        final OrderListAdapter mAdapter=new OrderListAdapter(this,mOrders);
        orderList.setAdapter(mAdapter);

        mOrderServed=(FloatingActionButton) findViewById(R.id.fab_table_served);
        mOrderServed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmTableServed dialog= ConfirmTableServed.getInstance(mTable);
                dialog.show(getSupportFragmentManager(),null);
            }
        });
        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot.hasChildren()){
                    Order mOrder = dataSnapshot.getValue(Order.class);
                    mOrders.add(mOrder);
                    mAdapter.notifyDataSetChanged();
                }
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
    public void onOrderChecked(Order order, boolean isChecked) {

    }
}
