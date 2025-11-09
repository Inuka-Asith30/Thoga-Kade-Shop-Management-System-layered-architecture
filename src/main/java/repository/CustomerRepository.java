package repository;

import model.Customer;

import java.sql.ResultSet;

public interface CustomerRepository {
    ResultSet getAll();
    int deleteDetails(String customerID);
    int addDetails(Customer customer);
    boolean updateDetails(Customer customer);
    ResultSet searchCustomer(String customerID);
}
