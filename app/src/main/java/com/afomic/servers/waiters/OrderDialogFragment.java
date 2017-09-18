package com.afomic.servers.waiters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.afomic.servers.R;
import com.afomic.servers.waiters.fragment.FoodFragment;

/**
 * Created by r4sh33d on 9/18/17.
 *
 */

public class OrderDialogFragment extends DialogFragment {
    private static final String FOOD_ITEM_UNITNAME_KEY = "unitname";
    private static final String FOOD_ITEM_NAME_KEY = "fooditemname";
    private static final String FOOD_ITEM_KEY = "fooditem";
    EditText editText;

    public static OrderDialogFragment newInstance(FoodModel foodItem) {

        Bundle args = new Bundle();
     /*   args.putString(FOOD_ITEM_NAME_KEY,foodItemName);
        args.putString(FOOD_ITEM_UNITNAME_KEY ,unitname );*/
        args.putParcelable(FOOD_ITEM_KEY, foodItem);
        OrderDialogFragment fragment = new OrderDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialogfrag_order, null);
        editText = (EditText) v.findViewById(R.id.edt_event_name);

        // Bundle args = getArguments();
        //String foodTitle = args.getString(FOOD_ITEM_NAME_KEY);
        final FoodModel foodModel = getArguments().getParcelable(FOOD_ITEM_KEY);
        mBuilder.setTitle(foodModel.name);
        editText.setHint("Enter number of " + foodModel.unitName);


        mBuilder.setPositiveButton("Order", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int quauntity = Integer.parseInt(editText.getText().toString());
                foodModel.setQauntity(quauntity);
                ((FoodFragment) getTargetFragment()).onDialogPositiveResult(foodModel);
                dismiss();
            }
        });
        mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });
        mBuilder.setView(v);
        return mBuilder.create();

    }
}
