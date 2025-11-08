package service;

import controller.DB.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;
import repository.ItemRepository;
import repository.ItemRepositoryImpl;
import service.Service.ItemManagementService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemManagementServiceImpl implements ItemManagementService {

    ObservableList<Item> itemDetailList= FXCollections.observableArrayList();
    ItemRepository itemRepository=new ItemRepositoryImpl();

    @Override
    public boolean addItemDetails(Item item) {

        boolean isAdded=itemRepository.addItemDetails(item);
        return isAdded;
    }

    @Override
    public boolean updateItemDetails(Item item) {
        return itemRepository.updateItemDetails(item);
    }

    @Override
    public boolean deleteItemDetails(String itemCode) {
        return itemRepository.deleteItem(itemCode);

    }

    @Override
    public ObservableList<Item> getAllItemDetails() {

        try {

            ResultSet resultSet=itemRepository.getAllItems();

            while(resultSet.next()){
                itemDetailList.add(
                        new Item(
                                resultSet.getString("ItemCode"),
                                resultSet.getString("Description"),
                                resultSet.getString("PackSize"),
                                resultSet.getDouble("UnitPrice"),
                                resultSet.getInt("QtyOnHand")
                        )
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemDetailList;
    }
}
