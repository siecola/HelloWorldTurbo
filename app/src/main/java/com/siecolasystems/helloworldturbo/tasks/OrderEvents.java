package com.siecolasystems.helloworldturbo.tasks;

import com.siecolasystems.helloworldturbo.models.Order;
import com.siecolasystems.helloworldturbo.webservice.WebServiceResponse;

import java.util.List;

/**
 * Created by siecola on 5/14/16.
 */
public interface OrderEvents {
    void getOrdersFinished(List<Order> orders);
    void getOrdersFailed(WebServiceResponse webServiceResponse);

    void getOrderByIdFinished(Order order);
    void getOrderByIdFailed(WebServiceResponse webServiceResponse);
}
