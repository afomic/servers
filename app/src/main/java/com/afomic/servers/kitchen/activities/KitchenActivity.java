package com.afomic.servers.kitchen.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.afomic.servers.R;
import com.afomic.servers.adapter.TableListAdapter;
import com.afomic.servers.model.Table;

import java.util.ArrayList;

public class KitchenActivity extends AppCompatActivity implements TableListAdapter.TableItemListener {
    RecyclerView TableList;
    RelativeLayout emptyViewLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        TableList=(RecyclerView) findViewById(R.id.rv_table_list);
        emptyViewLayout=(RelativeLayout) findViewById(R.id.empty_view) ;

        TableListAdapter mAdapter=new TableListAdapter(this,getDummyData());
        RecyclerView.LayoutManager mManager= new LinearLayoutManager(this);
        TableList.setLayoutManager(mManager);
        TableList.setAdapter(mAdapter);
    }

    @Override
    public void onClick(Table table) {

    }
    public ArrayList<Table> getDummyData(){
        ArrayList<Table> mTables=new ArrayList<>();
        for(int i=1;i<21;i++){
            String tableName="Table "+i;
            Table mTable=new Table(tableName);
            mTables.add(mTable);
        }
        return mTables;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.kitchen_main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_create_event:
                break;
            case R.id.menu_end_event:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
