package com.afomic.servers.waiters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afomic.servers.R;

import java.util.ArrayList;

/**
 * Created by r4sh33d on 9/17/17.
 */

public class FoodSpeciesAdapter extends RecyclerView.Adapter<FoodSpeciesAdapter.MyHolder> {
    private final AdapterListener mListener;
    ArrayList<FoodModel> arrayList;
    Context context;

    public FoodSpeciesAdapter(ArrayList<FoodModel> arrayList, Fragment context, String mFoodType) {
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
        holder.textView.setText(arrayList.get(position).name);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public interface AdapterListener {
        public void onClick(int position);
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;

        public MyHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(getAdapterPosition());

        }
    }
}
