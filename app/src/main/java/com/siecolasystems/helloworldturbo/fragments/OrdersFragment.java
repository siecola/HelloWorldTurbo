package com.siecolasystems.helloworldturbo.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.siecolasystems.helloworldturbo.R;
import com.siecolasystems.helloworldturbo.adapters.OrderAdapter;
import com.siecolasystems.helloworldturbo.models.Order;
import com.siecolasystems.helloworldturbo.tasks.OrderEvents;
import com.siecolasystems.helloworldturbo.tasks.OrderTasks;
import com.siecolasystems.helloworldturbo.util.CheckNetworkConnection;
import com.siecolasystems.helloworldturbo.webservice.WebServiceResponse;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by siecola on 5/28/16.
 */
public class OrdersFragment extends Fragment implements OrderEvents {

    private static final String TAG = "OrdersFragment";

    private ListView listViewOrders;
    private ArrayList<Order> orders;

    public OrdersFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_orders_list,
                container, false);

        getActivity().setTitle("Orders");

        listViewOrders = (ListView) rootView.
                findViewById(R.id.orders_list);

        if (CheckNetworkConnection.isNetworkConnected(getActivity())) {
            Log.i(TAG, "Consultando pedidos...");
            OrderTasks orderTasks = new OrderTasks(getActivity(), this);
            orderTasks.getOrders();
        }

        listViewOrders.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view, int position, long id) {
                        Order orderSelected = (Order)
                                listViewOrders.getItemAtPosition(position);
                        startOrderDetail(orderSelected.getId()/*, orderSelected*/);
                    }
                });

        return rootView;
    }

    private void startOrderDetail (int orderId/*, Order orderSelected*/) {
        Class fragmentClass;
        Fragment fragment = null;
        fragmentClass = OrderDetailFragment.class;

        try {
            fragment = (Fragment) fragmentClass.newInstance();

            if (orderId >= 0) {
                Bundle args = new Bundle();
                //args.putSerializable("order", orderSelected);
                args.putInt("order_id", orderId);
                fragment.setArguments(args);
            }

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction =
                    fragmentManager.beginTransaction();

            transaction.replace(R.id.container, fragment,
                    OrderDetailFragment.class.getCanonicalName());
            transaction.addToBackStack(
                    OrderDetailFragment.class.getCanonicalName());

            transaction.commit();
        } catch (Exception e) {
            try {
                Toast.makeText(getActivity(),
                        "Erro ao tentar abrir detalhes do pedido",
                        Toast.LENGTH_SHORT).show();
            } catch (Exception e1) {}
        }
    }

    @Override
    public void getOrdersFinished(List<Order> orders) {
        this.orders = (ArrayList<Order>) orders;
        OrderAdapter orderAdapter = new OrderAdapter(
                getActivity(), orders);

        listViewOrders.setAdapter(orderAdapter);
    }

    @Override
    public void getOrdersFailed(WebServiceResponse webServiceResponse) {
        Toast.makeText(getActivity(), "Falha na consulta da lista de pedidos" +
                webServiceResponse.getResultMessage() + " - Código do erro: " +
                webServiceResponse.getResponseCode(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getOrderByIdFinished(Order order) {

    }

    @Override
    public void getOrderByIdFailed(WebServiceResponse webServiceResponse) {

    }
}
