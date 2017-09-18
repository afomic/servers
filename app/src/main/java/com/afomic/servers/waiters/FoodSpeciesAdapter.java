package com.afomic.servers.waiters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afomic.servers.R;
import com.afomic.servers.model.Order;

import java.util.ArrayList;

/**
 * Created by r4sh33d on 9/17/17.
 */

public class FoodSpeciesAdapter extends RecyclerView.Adapter<FoodSpeciesAdapter.MyHolder> {
    private final AdapterListener mListener;
    ArrayList<Order> arrayList;
    Context context;

    public FoodSpeciesAdapter(ArrayList<Order> arrayList, Fragment context, String mFoodType) {
        this.arrayList = arrayList;
        this.context = context.getActivity();
        mListener = (AdapterListener) context;


    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_foodtype, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
 /*       FoodModel tempFood = arrayList.get(position);
        holder.foodNameTv.setText(tempFood.name);
        holder.quantityTv.setText(tempFood.qauntity + " " + tempFood.unitName);*/
        Order tempOrder = arrayList.get(position);
        holder.foodNameTv.setText(tempOrder.getName());
        holder.quantityTv.setText(tempOrder.getQuantity() + " " + tempOrder.getUnitName());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public interface AdapterListener {
        void onClick(int position);
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView foodNameTv, quantityTv;

        public MyHolder(View itemView) {
            super(itemView);
            foodNameTv = (TextView) itemView.findViewById(R.id.tv_food_name);
            quantityTv = (TextView) itemView.findViewById(R.id.tv_food_quantity);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(getAdapterPosition());

        }
    }
}
