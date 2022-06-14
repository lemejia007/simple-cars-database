
// CarsCarTable
package simplecarsdatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarsCarTable {

  static  final  String    DATABASE_URL = "jdbc:derby://localhost:1527/Car";
  static  final  String    USERNAME     = "Car";
  static  final  String    PASSWORD     = "demo";
  private static ResultSet resultSet    = null;

  CarsCarTable() {
    try {
      Connection connection = DriverManager.getConnection(DATABASE_URL, 
                                                          USERNAME, 
                                                          PASSWORD);
      PreparedStatement selectAllCars = connection.prepareStatement("SELECT * "
                                                             + "FROM CAR.CarTable");
      resultSet = selectAllCars.executeQuery();
    }
    catch (SQLException sqlException) {
      sqlException.printStackTrace();
      System.exit(1); 
    }
  }
    
  public void addCar(String carVIN, String carMake, String carModel, 
                     String carColor, int carYear, int carMileage) {
    try {
      Connection connection = DriverManager.getConnection(DATABASE_URL, 
                                                          USERNAME, 
                                                          PASSWORD);
      PreparedStatement insertCar = connection.prepareStatement("INSERT INTO "
              + "CAR.CarTable (CARVIN,CARMAKE,CARMODEL,CARCOLOR,CARYEAR,CARMILEAGE) "
              + "VALUES (?,?,?,?,?,?)");
      insertCar.setString(1, carVIN);
      insertCar.setString(2, carMake);
      insertCar.setString(3, carModel);
      insertCar.setString(4, carColor);
      insertCar.setInt   (5, carYear);
      insertCar.setInt   (6, carMileage);
      insertCar.executeUpdate();            
    }
    catch(SQLException sqlException) {
      sqlException.printStackTrace();
      System.exit(2); 
    }
  }
    
  public void updateCar(String carVIN, String carMake, String carModel, 
                        String carColor, int carYear, int carMileage) {
    try {
      Connection connection = DriverManager.getConnection(DATABASE_URL, 
                                                          USERNAME, 
                                                          PASSWORD);
      PreparedStatement updateCar = connection.prepareStatement("UPDATE CAR.CarTable"
              + " SET CARVIN=?,CARMAKE=?,CARMODEL=?,CARCOLOR=?,CARYEAR=?,"
              + "CARMILEAGE=? WHERE CARVIN=?");
      updateCar.setString(1, carVIN);
      updateCar.setString(2, carMake);
      updateCar.setString(3, carModel);
      updateCar.setString(4, carColor);
      updateCar.setInt   (5, carYear);
      updateCar.setInt   (6, carMileage);
      updateCar.setString(7, carVIN);
      updateCar.executeUpdate();            
    }
    catch(SQLException sqlException) {
      sqlException.printStackTrace();
      System.exit(3);
    }
  }
    
  public void deleteCar(String carVIN) {
    try {
      Connection connection = DriverManager.getConnection(DATABASE_URL, 
                                                          USERNAME, 
                                                          PASSWORD);
      PreparedStatement deleteCar = connection.prepareStatement("DELETE FROM "
                                                 + "CAR.CarTable WHERE CARVIN=?");
      deleteCar.setString(1, carVIN);
      deleteCar.executeUpdate();            
    }
    catch(SQLException sqlException) {
      sqlException.printStackTrace();
      System.exit(4);
    }
  }
    
  public ResultSet getResults() {
    if (resultSet != null) return resultSet;
    return new CarsCarTable().resultSet;
  }
}
