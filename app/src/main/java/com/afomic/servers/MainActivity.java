package com.afomic.servers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.afomic.servers.kitchen.activities.KitchenActivity;
import com.afomic.servers.waiters.activities.WaiterActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button kitchenLoginButton=(Button) findViewById(R.id.btn_kitchen);
        kitchenLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(MainActivity.this, KitchenActivity.class);
                startActivity(mIntent);
            }
        });
        Button waiterLoginButton=(Button) findViewById(R.id.btn_waiter);

        waiterLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(MainActivity.this, WaiterActivity.class);
                startActivity(mIntent);
            }
        });
    }
}

