package com.siecolasystems.helloworldturbo.models;

import java.io.Serializable;

/**
 * Created by siecola on 5/14/16.
 */
public class Pedido implements Serializable {

    private int OrderId;
    private String dataPedido;

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    @Override
    public String toString() {
        return "Pedido: " + this.OrderId + " - Data: " +
                this.dataPedido.replace("T", " ");
    }
}
