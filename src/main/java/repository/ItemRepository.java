package repository;

import model.Item;
import model.OrderDetails;

import java.sql.ResultSet;

public interface ItemRepository {
    public boolean addItemDetails(Item item);
    public boolean updateItemDetails(Item item);
    public boolean deleteItem(String itemCode);
    public ResultSet getAllItems();
    public ResultSet searchItem(String itemCode);
    ResultSet getQtyOnHand(OrderDetails orderDetails);
    int updateQtyOnHand(double qtyOnHand,OrderDetails orderDetails);

}
