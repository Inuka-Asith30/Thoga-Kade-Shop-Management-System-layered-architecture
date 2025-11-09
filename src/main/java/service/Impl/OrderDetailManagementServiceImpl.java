package service.Impl;

import controller.DB.DBConnection;
import service.OrderDetailManagementService;
import service.PlaceOrderService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.OrderDetails;
import repository.Impl.OrderDetailsRepositoryImpl;
import repository.OrderDetailsRepository;

import java.sql.*;

public class OrderDetailManagementServiceImpl implements OrderDetailManagementService {

    OrderDetailsRepository orderDetailsRepository=new OrderDetailsRepositoryImpl();

    @Override
    public boolean addOrderDetail(OrderDetails orderDetails) {
        return orderDetailsRepository.addOrderDetails(orderDetails);

    }

    @Override
    public boolean updateOrderDetail(OrderDetails orderDetails) {

        return orderDetailsRepository.updateOrderDetails(orderDetails);

    }

    @Override
    public int deleteOrderDetail(String itemCode,String orderId) {
        return orderDetailsRepository.deleteOrderDetails(itemCode, orderId);
    }

    @Override
    public ObservableList<OrderDetails> getAllOrderDetail() {

        ResultSet resultSet=orderDetailsRepository.getAllOrderDetails();

        ObservableList<OrderDetails> orderDetailList= FXCollections.observableArrayList();

        try{
            while(resultSet.next()) {
                orderDetailList.add(
                        new OrderDetails(
                                resultSet.getString("OrderID"),
                                resultSet.getString("ItemCode"),
                                resultSet.getInt("OrderQTY"),
                                resultSet.getInt("Discount")
                        )
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orderDetailList;
    }
}
