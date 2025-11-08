package repository.Impl;

import controller.DB.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Orders;
import repository.OrderRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRepositoryImpl implements OrderRepository {

    @Override
    public boolean addOrder(Orders orders) {
        try {
            Connection connection= DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO orders(OrderID, OrderDate, CustID) VALUES(?,?,?)");
            preparedStatement.setObject(1,orders.getOrderId());
            preparedStatement.setObject(2,orders.getOrderDate());
            preparedStatement.setObject(3,orders.getCustomerId());

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
    public boolean deleteOrder(String orderId) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from orders  where OrderId=?");
            preparedStatement.setObject(1,orderId);
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
    public ResultSet getAllOrders() {


        try {
            Connection connection= DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select * from orders");
            ResultSet resultSet=preparedStatement.executeQuery();



            return resultSet;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
