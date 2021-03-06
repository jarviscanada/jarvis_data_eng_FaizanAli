package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCExecutor {
  private static final Logger logger = LoggerFactory.getLogger(JDBCExecutor.class);

  public static void main(String[] args) {
    logger.info("Starting JDBCExecutor");
    DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost","hplussport","postgres","password");
    try{
      Connection connection = dcm.getConnection();
      CustomerDAO customerDAO = new CustomerDAO(connection);
      Customer customer = new Customer();
      customer.setFirstName("Johndoe");
      customer.setLastName("Adams");
      customer.setEmail("jadams.wh.gov");
      customer.setAddress("1234 Main St");
      customer.setCity("Arlington");
      customer.setState("VA");
      customer.setPhone("(555) 555-9845");
      customer.setZipCode("01234");

      Customer dbCustomer = customerDAO.create(customer);
      System.out.println(customer);
      dbCustomer = customerDAO.findById(dbCustomer.getId());
      System.out.println(dbCustomer);
      dbCustomer.setEmail("john.adams@wh.gov");
      dbCustomer = customerDAO.update(dbCustomer);
      System.out.println(dbCustomer);
      customerDAO.delete(dbCustomer.getId());
    }catch (SQLException e){
      logger.error("SQL exception in main",e);
    }
  }

}
