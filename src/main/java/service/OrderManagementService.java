package service;

import javafx.collections.ObservableList;
import model.Orders;

public interface OrderManagementService {
    boolean addOrder(Orders orders);
    boolean updateOrder(Orders orders);
    boolean deleteOrder(String orderId);
    ObservableList<Orders> getAllOrders();
}
