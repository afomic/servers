package com.afomic.servers.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.afomic.servers.R;
import com.afomic.servers.model.Order;

import java.util.ArrayList;

/**
 * Created by rechael on 9/16/2017.
 *
 */

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Order> mTableOrders;
    private OrderCheckboxListener mListener;

    public interface OrderCheckboxListener{
        public void onOrderChecked(Order order,boolean isChecked);
    }
    public OrderListAdapter(Context context,ArrayList<Order> orders){
        mContext=context;
        mTableOrders=orders;
        mListener=(OrderCheckboxListener) context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.order_list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Order mOrder=mTableOrders.get(position);
        holder.orderQuantityTextView.setText(mOrder.getQuantityString());
        holder.orderNameTextView.setText(mOrder.getName());
        if(mOrder.getStatus()==Order.NEW){
            holder.orderServedCheckBox.setChecked(false);
        }else {
            holder.orderServedCheckBox.setChecked(true);
        }


    }

    @Override
    public int getItemCount() {
        return mTableOrders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView orderNameTextView,orderQuantityTextView;
        CheckBox orderServedCheckBox;
        public ViewHolder(View itemView) {
            super(itemView);
            orderNameTextView=(TextView) itemView.findViewById(R.id.tv_order_name);
            orderQuantityTextView=(TextView) itemView.findViewById(R.id.tv_order_quantity);
            orderServedCheckBox=(CheckBox) itemView.findViewById(R.id.cb_order_served);
            orderServedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mListener.onOrderChecked(mTableOrders.get(getAdapterPosition()),isChecked);
                }
            });
        }
    }
}
