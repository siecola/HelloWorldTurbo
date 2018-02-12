package com.siecolasystems.helloworldturbo.models;

/**
 * Created by siecola on 5/14/16.
 */
public class OrderItem {
    public int Id;
    public int ProductId;
    public Product Product;
    public int OrderId;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public com.siecolasystems.helloworldturbo.models.Product getProduct() {
        return Product;
    }

    public void setProduct(com.siecolasystems.helloworldturbo.models.Product product) {
        Product = product;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }
}
