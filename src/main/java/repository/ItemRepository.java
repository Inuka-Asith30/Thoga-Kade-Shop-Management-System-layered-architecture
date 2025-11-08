package repository;

import model.Item;

import java.sql.ResultSet;

public interface ItemRepository {
    public boolean addItemDetails(Item item);
    public boolean updateItemDetails(Item item);
    public boolean deleteItem(String itemCode);
    public ResultSet getAllItems();
}
