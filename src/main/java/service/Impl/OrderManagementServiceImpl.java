package service.Impl;

import service.OrderManagementService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Orders;
import repository.Impl.OrderRepositoryImpl;
import repository.OrderRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderManagementServiceImpl implements OrderManagementService {

    OrderRepository orderRepository=new OrderRepositoryImpl();

    @Override
    public boolean addOrder(Orders orders) {
        return orderRepository.addOrder(orders);
    }

    @Override
    public boolean updateOrder(Orders orders) {
        return false;
    }

    @Override
    public boolean deleteOrder(String orderId) {
        return orderRepository.deleteOrder(orderId);
    }

    @Override
    public ObservableList<Orders> getAllOrders() {

        ResultSet resultSet=orderRepository.getAllOrders();
        ObservableList<Orders> ordersList= FXCollections.observableArrayList();

        try {
            while(resultSet.next()){

                ordersList.add(
                        new Orders(
                                resultSet.getString("OrderId"),
                                resultSet.getDate("orderDate").toLocalDate(),
                                resultSet.getString("CustID"))
                );

            }

            return ordersList;

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public String getLastId() {

        ResultSet resultSet=orderRepository.getOrderId();

        try {

            String orderId=null;

            while(resultSet.next()){
                orderId=resultSet.getString("OrderID");
            }

            return orderId;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
