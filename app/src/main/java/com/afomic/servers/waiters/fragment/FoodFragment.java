package com.afomic.servers.waiters.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afomic.servers.R;
import com.afomic.servers.model.Order;
import com.afomic.servers.waiters.FoodConstants;
import com.afomic.servers.waiters.FoodSpeciesAdapter;
import com.afomic.servers.waiters.OrderDialogFragment;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FoodFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FoodFragment extends Fragment implements FoodSpeciesAdapter.AdapterListener {
    private static final String FOOD_TYPE_KEY = "foodkey";
    HashMap<String, Order> orderHashMap = new HashMap<>();
    RecyclerView recyclerView;
    // ArrayList<FoodModel> arrayList = new ArrayList<>();
    ArrayList<Order> arrayList = new ArrayList<>();
    //int mFoodType;
    FoodSpeciesAdapter adapter;
    private OnFragmentInteractionListener mListener;

    public FoodFragment() {
        // Required empty public constructor
    }

    public static FoodFragment newInstance(int id) {
        //  mFoodType = id;
        Bundle args = new Bundle();

        FoodFragment fragment = new FoodFragment();
        args.putInt(FOOD_TYPE_KEY, id);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new FoodSpeciesAdapter(arrayList, this, getUnitName(getArguments().getInt(FOOD_TYPE_KEY)));
        recyclerView.setAdapter(adapter);
        setUpRecyclerView(getArguments().getInt(FOOD_TYPE_KEY));


        return view;
    }

    public void setUpRecyclerView(int type) {
        arrayList.clear();
        String[] arrays = new String[0];
        switch (type) {
            case FoodConstants.REALFOOD:
                arrays = new String[]{"Mixed Rice", "Jolof Rice", "Amala"};
                break;
            case FoodConstants.DRINKS:
                arrays = new String[]{"Cocacola", "pepsi", "Mongo"};
                break;
            case FoodConstants.SOUP:
                arrays = new String[]{"Egusi", "Chineese Soup", "italian Soup"};
                break;
            default:
                //

        }


        for (String str : arrays) {
            arrayList.add(new Order("N/A", str, 0, getUnitName(getArguments().getInt(FOOD_TYPE_KEY))));

        }
        adapter.notifyDataSetChanged();


    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " You must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public String getUnitName(int foodType) {
        switch (foodType) {
            case FoodConstants.REALFOOD:
                return "plates";
            case FoodConstants.DRINKS:
                return "bottles";

            case FoodConstants.SOUP:
                return "plates";
            default:
                return "";
        }

    }

    @Override
    public void onClick(int position) {
        OrderDialogFragment dialog = OrderDialogFragment.newInstance(arrayList.get(position), position);
        dialog.setTargetFragment(this, 123);
        dialog.show(getFragmentManager().beginTransaction(), null);
    }

    public void onDialogPositiveResult(Order order, int changedPosition) {
        //Todo follow design pattern for this method by creating an interface to enforce dialogFragment to call this method
        //TODO  update itemview of food species adapter to indicate quantity of food choosen


        switch (order.getName()) {
            case "Mixed Rice":
                orderHashMap.put(FoodConstants.MAP_KEY_MIXED_RICE, order);
                break;
            case "Jolof Rice":
                orderHashMap.put(FoodConstants.MAP_KEY_JOLOF, order);
                break;

            case "Amala":
                orderHashMap.put(FoodConstants.MAP_KEY_AMALA, order);
                break;
            case "Cocacola":
                orderHashMap.put(FoodConstants.MAP_KEY_COCACOLA, order);
                break;
            case "pepsi":
                orderHashMap.put(FoodConstants.MAP_KEY_PEPSI, order);
                break;
            case "Egusi":
                orderHashMap.put(FoodConstants.MAP_KEY_EGUSI, order);
                break;
            case "Chineese Soup":
                orderHashMap.put(FoodConstants.MAP_KEY_CHINEESESOUP, order);
                break;
            case "italian Soup":
                orderHashMap.put(FoodConstants.MAP_KEY_ITALIANSOUP, order);
                break;
            default:
                //come back and handle this

        }
        adapter.notifyItemChanged(changedPosition);

        mListener.onFragmentInteraction(orderHashMap, getClass().getSimpleName() +
                getArguments().getInt(FOOD_TYPE_KEY));

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(HashMap<String, Order> ordersMAp, String FragmentName);
    }


}
