package repository;

import model.Orders;

import java.sql.ResultSet;

public interface OrderRepository {
    public boolean addOrder(Orders orders);
    public boolean deleteOrder(String orderId);
    public ResultSet getAllOrders();
    ResultSet getOrderId();
}
