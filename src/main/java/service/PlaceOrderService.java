package service;

import javafx.collections.ObservableList;
import model.Item;
import model.Orders;
import model.OrderDetails;
import model.TableOrderDetail;

public interface PlaceOrderService {
    public Item priceInitialize(String itemCode);
    public String nameInitialize(String customerId);
    public String getOrderId();
    public boolean placeOrderDetails(Orders orders, ObservableList<TableOrderDetail> tableOrderDetail);
    public boolean updateItemTable(OrderDetails orderDetails);
}
