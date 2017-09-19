package com.afomic.servers.waiters.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.afomic.servers.R;
import com.afomic.servers.data.Constants;
import com.afomic.servers.model.Order;
import com.afomic.servers.model.Table;
import com.afomic.servers.waiters.fragment.FoodFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class FoodOrderActivity extends AppCompatActivity implements FoodFragment.OnFragmentInteractionListener {
    Toolbar toolbar;
    TabLayout mTabLayout;

    /**
     * Used Hashmap because user might want to edit their choice,
     * since we want to discard previous order on the same key(food)
     * Hashmap will just update the key.
     */
    HashMap<String, HashMap<String, Order>> masterMap = new HashMap<>();
    FloatingActionButton fabOrder;
    private Table mTable;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_order);
        mTable = getIntent().getParcelableExtra(Constants.BUNDLE_TABLE);
        setToolbarAndViewPager();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("events/tables/")
                .child(mTable.getKey())
                .child("orders");

        fabOrder = (FloatingActionButton) findViewById(R.id.fab_order);
        fabOrder.setOnClickListener(new View.OnClickListener() {
            public ProgressDialog pDialog;

            @Override
            public void onClick(View v) {
                //The following if check is necessary
                //We want to place the order immediately so that users can know for
                //sure that they have placed order , This will also avoid duplicate orders.
                //This is as opposed to firebase logic that queues pending write operations
                // till there is internet , but this is what we need.
                //
                if (isOnline()) {
                    pDialog = new ProgressDialog(FoodOrderActivity.this);
                    pDialog.setMessage("Placing Order, Please wait...");
                    pDialog.setIndeterminate(true);
                    //pDialog.setCancelable(false);

                    final AlertDialog.Builder builder = new AlertDialog.Builder(FoodOrderActivity.this);

                    builder.setMessage("Are you sure ?")
                            .setTitle("Place Order");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            Log.d(getClass().getSimpleName(), masterMap.toString());
                            pDialog.show();
                            for (HashMap<String, Order> tempMap : masterMap.values()) {
                                for (Order order : tempMap.values()) {

                                    String tempKey = mDatabaseReference.push().getKey();
                                    order.setKey(tempKey);
                                    mDatabaseReference.child(tempKey).setValue(order).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            pDialog.dismiss();
                                            updateTableStatus(Table.ORDER_TAKEN);
                                            Toast.makeText(FoodOrderActivity.this, "Order Successfully placed",
                                                    Toast.LENGTH_SHORT).show();

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            pDialog.dismiss();
                                            Toast.makeText(FoodOrderActivity.this, "Something went wrong ," +
                                                            "Please check your " +
                                                            "internet connection",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }
                            }
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
                    builder.create().show();

                } else {
                    Toast.makeText(FoodOrderActivity.this, "Pls enable your" +
                            " internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void setToolbarAndViewPager() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(mTable.getName());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        PagerAdapter pagerAdapter =
                new PagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(10);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(pagerAdapter.getTabView(i));
        }


    }

    @Override
    public void onFragmentInteraction(HashMap<String, Order> ordersMAp, String FragmentName) {
        Log.i(getClass().getSimpleName(), ordersMAp.toString());
        masterMap.put(FragmentName, ordersMAp);
    }

    //a method to update the status of the table object
    public void updateTableStatus(int status) {
            DatabaseReference mRef = FirebaseDatabase.
                    getInstance()
                    .getReference("events/tables/")
                    .child(mTable.getKey());
        mTable.setStatus(status);
        mRef.setValue("status", status);
    }

    private class PagerAdapter extends FragmentPagerAdapter {

        String tabTitles[] = new String[]{"Food", "Soup", "Drinks"};
        Context context;

        PagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return FoodFragment.newInstance(Constants.REALFOOD);
                case 1:
                    return FoodFragment.newInstance(Constants.SOUP);
                case 2:
                    return FoodFragment.newInstance(Constants.DRINKS);

            }

            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            return tabTitles[position];
        }

        View getTabView(int position) {
            View tab = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
            TextView tv = (TextView) tab.findViewById(R.id.custom_text);
            tv.setText(tabTitles[position]);
            return tab;
        }

    }
}
