package com.afomic.servers.kitchen.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class OrderDetailActivity extends AppCompatActivity implements OrderListAdapter.OrderCheckboxListener {
    RecyclerView orderList;
    Table mTable;
    FloatingActionButton mOrderServed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        //get table from intent
        mTable =getIntent().getParcelableExtra(Constants.BUNDLE_TABLE);

        TextView orderTitle=(TextView) findViewById(R.id.tv_order_title);
        orderTitle.setText(mTable.getName()+" Orders");
        orderList=(RecyclerView) findViewById(R.id.rv_order_list);

        RecyclerView.LayoutManager mManager=new LinearLayoutManager(this);
        orderList.setLayoutManager(mManager);
        OrderListAdapter mAdapter=new OrderListAdapter(this,mTable.getTableOrders());
        orderList.setAdapter(mAdapter);

        mOrderServed=(FloatingActionButton) findViewById(R.id.fab_table_served);
        mOrderServed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmTableServed dialog= ConfirmTableServed.getInstance(mTable);
                dialog.show(getSupportFragmentManager(),null);
            }
        });

    }

    @Override
    public void onOrderChecked(Order order, boolean isChecked) {

    }
}
