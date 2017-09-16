package com.afomic.servers.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afomic.servers.R;
import com.afomic.servers.model.Table;

import java.util.ArrayList;

/**
 * Created by rechael on 9/16/2017.
 */

public class TableListAdapter extends RecyclerView.Adapter<TableListAdapter.ViewHolder>{
    public interface TableItemListener{
        public void onClick(Table table);
    }
    private Context mContext;
    private TableItemListener mListener;
    private ArrayList<Table> mTables;
    public TableListAdapter(Context context, ArrayList<Table> tables){
        mContext=context;
        mTables=tables;
        mListener=(TableItemListener) context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.table_list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Table mTable=mTables.get(position);
        holder.tableNameTextView.setText(mTable.getName());
        if(mTable.getWaiterName()!=null){
            holder.waiterNameTextView.setVisibility(View.VISIBLE);
            holder.waiterNameTextView.setText(mTable.getWaiterName());
        }else {
            holder.waiterNameTextView.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return mTables.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView waiterNameTextView,tableNameTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            waiterNameTextView=(TextView) itemView.findViewById(R.id.tv_waiter_name);
            tableNameTextView=(TextView) itemView.findViewById(R.id.tv_table_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Table mItem=mTables.get(getAdapterPosition());
            mListener.onClick(mItem);
        }
    }
}
