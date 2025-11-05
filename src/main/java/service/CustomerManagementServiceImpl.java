package service;

import controller.DB.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import repository.CustomerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerManagementServiceImpl implements CustomerManagementService {

    CustomerRepository customerRepository =new CustomerRepository();

    @Override
    public boolean addCustomerDetails(Customer customer) {

        int isAdded=customerRepository.addDetails(customer);

        if(isAdded==1){
            return true;
        }

        return false;
    }

    @Override
    public boolean updateCustomerDetails(Customer customer) {

        boolean isUpdated=customerRepository.updateDetails(customer);

        return isUpdated;
    }

    @Override
    public boolean deleteCustomerDetails(String customerID) {

        if(customerRepository.deleteDetails(customerID)==1){
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public ObservableList<Customer> getAllCustomerDetails() {

        ObservableList<Customer> customerDetailsList = FXCollections.observableArrayList();
        ResultSet resultSet=customerRepository.getAll();
        try {
            while (resultSet.next()) {
                customerDetailsList.add(
                        new Customer(
                                resultSet.getString("CustID"),
                                resultSet.getString("CustName"),
                                resultSet.getString("CustTitle"),
                                resultSet.getDate("DOB").toLocalDate(),
                                resultSet.getDouble("salary"),
                                resultSet.getString("CustAddress"),
                                resultSet.getString("City"),
                                resultSet.getString("Province"),
                                resultSet.getString("PostalCode")
                        )
                );
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return customerDetailsList;
    }


}
