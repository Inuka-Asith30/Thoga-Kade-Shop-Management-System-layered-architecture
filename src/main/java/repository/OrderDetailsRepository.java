package repository;

import model.OrderDetails;

import java.sql.ResultSet;

public interface OrderDetailsRepository {
    public boolean addOrderDetails(OrderDetails orderDetails);
    public boolean updateOrderDetails(OrderDetails orderDetails);
    public int deleteOrderDetails(String itemCode,String orderId);
    public ResultSet getAllOrderDetails();
}
