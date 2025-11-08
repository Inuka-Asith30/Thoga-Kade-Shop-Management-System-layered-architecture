package repository;

import controller.DB.DBConnection;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRepositoryImpl implements CustomerRepository{

    public ResultSet getAll(){

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from customer;");
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public int deleteDetails(String customerID){

        int isDeleted=0;
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM customer where CustID=?");
            preparedStatement.setObject(1,customerID);
            isDeleted=preparedStatement.executeUpdate();

            return isDeleted;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int addDetails(Customer customer){
        String SQL="INSERT INTO customer(CustID, CustTitle, CustName, DOB, salary,CustAddress,City,Province,PostalCode) VALUES(?,?,?,?,?,?,?,?,?);";

        Connection connection= null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);

            preparedStatement.setObject(1,customer.getCustomerID());
            preparedStatement.setObject(2,customer.getTitle());
            preparedStatement.setObject(3,customer.getName());
            preparedStatement.setObject(4,customer.getDob());
            preparedStatement.setObject(5,customer.getSalary());
            preparedStatement.setObject(6,customer.getAddress());
            preparedStatement.setObject(7,customer.getCity());
            preparedStatement.setObject(8,customer.getProvince());
            preparedStatement.setObject(9,customer.getPostalCode());

            int isAdded=preparedStatement.executeUpdate();

            return isAdded;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public boolean updateDetails(Customer customer){
        String SQL="UPDATE customer SET CustTitle=?,CustName=?,DOB=?,salary=?,CustAddress=?,City=?,Province=?,PostalCode=? WHERE CustID=?";

        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);


            preparedStatement.setObject(1,customer.getTitle());
            preparedStatement.setObject(2,customer.getName());
            preparedStatement.setObject(3,customer.getDob());
            preparedStatement.setObject(4,customer.getSalary());
            preparedStatement.setObject(5,customer.getAddress());
            preparedStatement.setObject(6,customer.getCity());
            preparedStatement.setObject(7,customer.getProvince());
            preparedStatement.setObject(8,customer.getPostalCode());
            preparedStatement.setObject(9,customer.getCustomerID());

            int isUpdated=preparedStatement.executeUpdate();

            if(isUpdated==1){
                return true;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return false;
    }


}
