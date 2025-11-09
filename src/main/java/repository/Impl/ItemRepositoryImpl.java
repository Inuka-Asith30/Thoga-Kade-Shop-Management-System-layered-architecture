package repository.Impl;

import controller.DB.DBConnection;
import model.Item;
import model.OrderDetails;
import repository.ItemRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRepositoryImpl implements ItemRepository {

    @Override
    public boolean addItemDetails(Item item) {
        String SQL="INSERT INTO item(ItemCode, Description, PackSize, UnitPrice, QtyOnHand) VALUES(?,?,?,?,?);";

        try {
            Connection connection= DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            preparedStatement.setObject(1,item.getItemCode());
            preparedStatement.setObject(2,item.getDescription());
            preparedStatement.setObject(3,item.getPackSize());
            preparedStatement.setObject(4,item.getUnitPrice());
            preparedStatement.setObject(5,item.getQtyOnHand());

            int isAdded=preparedStatement.executeUpdate();

            if(isAdded==1){
                return true;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return false;
    }

    @Override
    public boolean updateItemDetails(Item item) {
        String SQL="UPDATE item SET Description=?,PackSize=?,QtyOnHand=?,UnitPrice=? WHERE ItemCode=?";

        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);

            preparedStatement.setObject(1,item.getDescription());
            preparedStatement.setObject(2,item.getPackSize());
            preparedStatement.setObject(3,item.getQtyOnHand());
            preparedStatement.setObject(4,item.getUnitPrice());
            preparedStatement.setObject(5,item.getItemCode());

            int isUpdated=preparedStatement.executeUpdate();

            if(isUpdated==1){
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    @Override
    public boolean deleteItem(String itemCode) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM item where ItemCode=?");
            preparedStatement.setObject(1,itemCode);
            int isDeleted=preparedStatement.executeUpdate();

            if(isDeleted==1){
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public ResultSet getAllItems() {
        String SQL="select * from item;";
        try {
            Connection connection= DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            ResultSet resultSet=preparedStatement.executeQuery();

            return resultSet;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ResultSet searchItem(String itemCode) {
        Connection connection= null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select * from item where ItemCode=?");
            preparedStatement.setObject(1,itemCode);
            ResultSet resultSet=preparedStatement.executeQuery();

            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ResultSet getQtyOnHand(OrderDetails orderDetails) {
        Connection connection= null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select QtyOnHand from item where ItemCode=?");
            preparedStatement.setObject(1,orderDetails.getItemCode());
            ResultSet resultSet=preparedStatement.executeQuery();

            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int updateQtyOnHand(double qtyOnHand, OrderDetails orderDetails) {
        Connection connection= null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE item SET QtyOnHand=? WHERE ItemCode=?");
            preparedStatement.setObject(1,qtyOnHand);
            preparedStatement.setObject(2,orderDetails.getItemCode());
            int isUpdated=preparedStatement.executeUpdate();

            return isUpdated;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }




}
