package service.Impl;

import controller.DB.DBConnection;
import repository.CustomerRepository;
import repository.Impl.CustomerRepositoryImpl;
import repository.Impl.ItemRepositoryImpl;
import repository.ItemRepository;
import service.CustomerManagementService;
import service.OrderDetailManagementService;
import service.OrderManagementService;
import javafx.collections.ObservableList;
import model.Item;
import model.Orders;
import model.OrderDetails;
import model.TableOrderDetail;
import service.PlaceOrderService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaceOrderServiceImpl implements PlaceOrderService {

    OrderManagementService orderManagementService=new OrderManagementServiceImpl();
    ItemRepository itemRepository=new ItemRepositoryImpl();
    CustomerRepository customerRepository=new CustomerRepositoryImpl();
    OrderDetailManagementService orderDetailManagementService=new OrderDetailManagementServiceImpl();

    @Override
    public Item priceInitialize(String itemCode) {

        try {

            ResultSet resultSet=itemRepository.searchItem(itemCode);

            Item item = null;
            while(resultSet.next()){
                item=new Item(
                        resultSet.getString("ItemCode"),
                        resultSet.getString("Description"),
                        resultSet.getString("PackSize"),
                        resultSet.getDouble("UnitPrice"),
                        resultSet.getInt("QtyOnHand")
                );
            }
            return item;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String nameInitialize(String customerId) {

        try {
            ResultSet resultSet=customerRepository.searchCustomer(customerId);
            String custName=null;
            while(resultSet.next()){
                custName= resultSet.getString("CustName");
            }
            return custName;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String getOrderId() {

        String lastOrderId=orderManagementService.getLastId();
        String newOrderId=null;

        if(lastOrderId!=null){
            lastOrderId=lastOrderId.split("[A-Z]")[1];//D060-->060
            newOrderId=String.format("D%03d",(Integer.parseInt(lastOrderId)+1));

            return newOrderId;
        }
        else{
            return "D001";
        }

    }

    @Override
    public boolean placeOrderDetails(Orders orders, ObservableList<TableOrderDetail> tableOrderDetails) {

        boolean isAddedOrderTable=orderManagementService.addOrder(orders);
        boolean isAddedOrderdetailsTable=true;
        OrderDetails setOrderTable=null;

        for(TableOrderDetail tableOrderDetail:tableOrderDetails){

            setOrderTable=new OrderDetails(orders.getOrderId(),
                    tableOrderDetail.getItemCode(),
                    tableOrderDetail.getOrderQty(),
                    tableOrderDetail.getDiscount()
            );



            if(addOrderDetail(setOrderTable)!=true || updateItemTable(setOrderTable)!=true){
                isAddedOrderdetailsTable=false;
                break;
            }

        }



        return isAddedOrderdetailsTable && isAddedOrderTable;
    }

    private boolean addOrderDetail(OrderDetails orderDetails){
        boolean isAdded=orderDetailManagementService.addOrderDetail(orderDetails);
        return isAdded;
    }

    public boolean updateItemTable(OrderDetails orderDetails){
        try {
            ResultSet resultSet=itemRepository.getQtyOnHand(orderDetails);

            Double qtyOnHand =0.0;

            while (resultSet.next()){
                qtyOnHand =resultSet.getDouble("qtyOnHand");
            }
            qtyOnHand= qtyOnHand-orderDetails.getOrderQty();

            int isUpdated=itemRepository.updateQtyOnHand(qtyOnHand,orderDetails);

            if(isUpdated==1){
                return true;
            }
            return false;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
