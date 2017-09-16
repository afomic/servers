package com.afomic.servers.kitchen.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.afomic.servers.R;

public class OrderDetailActivity extends AppCompatActivity {
    RecyclerView orderList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        //get table from intent

        TextView orderTitle=(TextView) findViewById(R.id.tv_order_title);
        orderList=(RecyclerView) findViewById(R.id.rv_order_list);

        RecyclerView.LayoutManager mManager=new LinearLayoutManager(this);
        orderList.setLayoutManager(mManager);


    }
}
