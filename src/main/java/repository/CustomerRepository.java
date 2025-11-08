package repository;

import model.Customer;

import java.sql.ResultSet;

public interface CustomerRepository {
    public ResultSet getAll();
    public int deleteDetails(String customerID);
    public int addDetails(Customer customer);
    public boolean updateDetails(Customer customer);
}
