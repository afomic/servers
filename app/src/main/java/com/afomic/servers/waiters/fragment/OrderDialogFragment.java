package com.afomic.servers.waiters.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afomic.servers.R;
import com.afomic.servers.model.Order;

/**
 * Created by r4sh33d on 9/18/17.
 */

public class OrderDialogFragment extends DialogFragment {
    private static final String FOOD_ITEM_POSITION_KEY = "unitname";
    private static final String FOOD_ITEM_NAME_KEY = "fooditemname";
    private static final String FOOD_ITEM_KEY = "fooditem";
    EditText editText;
    private TextView headerText;
    private TextView titleText;

    public static OrderDialogFragment newInstance(Order order, int position) {

        Bundle args = new Bundle();
     /*   args.putString(FOOD_ITEM_NAME_KEY,foodItemName);*/
        args.putInt(FOOD_ITEM_POSITION_KEY, position);
        args.putParcelable(FOOD_ITEM_KEY, order);
        OrderDialogFragment fragment = new OrderDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialogfrag_order, null);
        editText = (EditText) v.findViewById(R.id.edt_event_name);
        headerText = (TextView) v.findViewById(R.id.tv_header);
        titleText = (TextView) v.findViewById(R.id.tv_title);

        // Bundle args = getArguments();
        //String foodTitle = args.getString(FOOD_ITEM_NAME_KEY);
        final Order order = getArguments().getParcelable(FOOD_ITEM_KEY);
        //mBuilder.setTitle(foodModel.name);
        titleText.setText(order.getName());
        headerText.setText("Enter quantity of " + order.getUnitName());
        editText.setHint(String.valueOf(order.getQuantity()));


        mBuilder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String quantityString = editText.getText().toString();
                if (!TextUtils.isEmpty(quantityString)) {
                    int quauntity = Integer.parseInt(quantityString);
                    order.setQuantity(quauntity);
                    ((FoodFragment) getTargetFragment()).onDialogPositiveResult(order,
                            getArguments().getInt(FOOD_ITEM_POSITION_KEY));
                    dismiss();
                } else {
                    Toast.makeText(getActivity(), "Please Enter a valid number", Toast.LENGTH_SHORT).show();
                }

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
