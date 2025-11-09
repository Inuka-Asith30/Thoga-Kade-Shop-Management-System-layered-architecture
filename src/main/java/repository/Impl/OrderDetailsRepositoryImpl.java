package repository.Impl;

import controller.DB.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.OrderDetails;
import repository.OrderDetailsRepository;
import service.Impl.PlaceOrderServiceImpl;
import service.PlaceOrderService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailsRepositoryImpl implements OrderDetailsRepository {
    @Override
    public boolean addOrderDetails(OrderDetails orderDetails) {
        try {
            Connection connection= DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO orderdetail(OrderID, ItemCode, OrderQTY, Discount) VALUES(?,?,?,?)");
            preparedStatement.setObject(1,orderDetails.getOrderId());
            preparedStatement.setObject(2,orderDetails.getItemCode());
            preparedStatement.setObject(3,orderDetails.getOrderQty());
            preparedStatement.setObject(4,orderDetails.getDiscount());

            int isAdded=preparedStatement.executeUpdate();

            if(isAdded==1){
                return true;
            }
            return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateOrderDetails(OrderDetails orderDetails) {

        String SQL="UPDATE orderdetail SET OrderQTY=?,Discount=? WHERE ItemCode=? and OrderID=?";

        try {

            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);

            preparedStatement.setObject(1,orderDetails.getOrderQty());
            preparedStatement.setObject(2,orderDetails.getDiscount());
            preparedStatement.setObject(3,orderDetails.getItemCode());
            preparedStatement.setObject(4,orderDetails.getOrderId());

            int isUpdated=preparedStatement.executeUpdate();
            PlaceOrderService placeOrderService=new PlaceOrderServiceImpl();
            boolean isUpdatedItemTable=placeOrderService.updateItemTable(orderDetails);

            if(isUpdated==1 && isUpdatedItemTable){
                return true;
            }
            return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int deleteOrderDetails(String itemCode, String orderId) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("delete from orderdetail where ItemCode=? and OrderId=?");
            preparedStatement.setObject(1,itemCode);
            preparedStatement.setObject(2,orderId);
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet getAllOrderDetails() {



        try {
            Connection  connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select * from orderdetail");
            ResultSet resultSet=preparedStatement.executeQuery();

            return resultSet;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
