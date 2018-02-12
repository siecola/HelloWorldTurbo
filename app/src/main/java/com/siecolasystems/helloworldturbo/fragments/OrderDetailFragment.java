package com.siecolasystems.helloworldturbo.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.siecolasystems.helloworldturbo.R;
import com.siecolasystems.helloworldturbo.models.Order;

/**
 * Created by siecola on 5/28/16.
 */
public class OrderDetailFragment extends Fragment {

    private static final String TAG = "OrderDetailFragment";

    private TextView txtOrderId;

    public OrderDetailFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_order_detail,
                container, false);

        txtOrderId = (TextView) rootView.findViewById(R.id.txtOrderId);

        int orderId;
        Bundle bundle = this.getArguments();
        if ((bundle != null) && (bundle.containsKey("order_id"))) {
            orderId = bundle.getInt("order_id");
            Log.i(TAG, "Order ID: " + orderId);
        }

        ///...
        Order order = new Order();


        txtOrderId.setText(String.valueOf(order.getId()));


        return rootView;
    }
}










