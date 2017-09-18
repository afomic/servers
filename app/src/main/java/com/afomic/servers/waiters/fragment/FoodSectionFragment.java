package com.afomic.servers.waiters.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afomic.servers.model.Order;

import java.util.ArrayList;

/**
 * Created by rechael on 9/17/2017.
 *
 */

public class FoodSectionFragment extends Fragment {
    public static FoodSectionFragment newInstance(int section, ArrayList<Order> orders){
        throw new UnsupportedOperationException();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
