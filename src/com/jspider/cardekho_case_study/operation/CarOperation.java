package com.jspider.cardekho_case_study.operation;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.ArrayList;

import java.util.Scanner;

import com.jspider.cardekho_case_study.entity.Car;



public class CarOperation {
     private static Connection connection;
     private static Statement statement;
     private static ResultSet resultSet;
     private String url="jdbc:mysql://localhost:3306/weja2";
     private String user="root";
     private String password="root";
     private static int result;
     private static PreparedStatement preparedStatement;
     
    
	 public  CarOperation() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection(url ,user,password);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	 }
	

	public void addCarDetails() {
		try {
		System.out.println("How many car details you want to add? ");
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
		for (int i = 1; i <= choice; i++) {
			Car car = new Car();
			System.out.print("Enter car id : ");
			car.setCar_id(scanner.nextInt());
			System.out.print("Enter car name : ");
			car.setName(scanner.next());
			System.out.print("Enter car brand : ");
			car.setBrand(scanner.next());
			System.out.print("Enter car fuel type : ");
			car.setFuel_type(scanner.next());
			System.out.print("Enter car price : ");
			car.setPrice(scanner.nextDouble());
			insertCarDetails(car);
			System.out.println("\n\n" + car.getName() + " added..!! ");
		}
		getAllCarDetails();
	}catch(SQLException e) {
		e.printStackTrace();
	}
	}

	public void removeCarDetails() {
		try {
            getAllCarDetails();
            if (!carsExist()) {
                System.out.println("No car details found..!!");
                return;
            }

            System.out.print("\nEnter car id to remove : ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            // Delete the car from the database
            if (deleteCarDetails(choice)) {
                System.out.println("Car with ID " + choice + " removed..!!");
            } else {
                System.out.println("Invalid choice. Try again..!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public void getAllCarDetails() {
		try {
            String sql = "SELECT * FROM car_table";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (!resultSet.isBeforeFirst()) {
                System.out.println("No car details found..!!");
            } else {
                System.out.println("Total car details : ");
                System.out.println("ID\t" + "Name\t" + "Brand\t" + "Fuel Type\t" + "Price");
                System.out.println("=======================================================");
                while (resultSet.next()) {
                	  int car_id = resultSet.getInt("car_id");
                      String name = resultSet.getString("name");
                      String brand = resultSet.getString("brand");
                      String fuel_type = resultSet.getString("fuel_type");
                      double price = resultSet.getDouble("price");

                      System.out.println(car_id + "\t" + name + "\t" + brand + "\t" + fuel_type + "\t\t" + price);
                  }
              }
          } catch (SQLException e) {
              e.printStackTrace();
          }
  	}
  	 public boolean carsExist() throws SQLException {
  	        String sql = "SELECT COUNT(*) FROM car_table";
  	        Statement statement = connection.createStatement();
  	        resultSet = statement.executeQuery(sql);
  	        resultSet.next();
  	        return resultSet.getInt(1) > 0;
  	    }

  	    public boolean insertCarDetails(Car car) throws SQLException {
  	    	try {
  	          String sql = "INSERT INTO car_table (car_id, name, brand, fuel_type, price) VALUES (?, ?, ?, ?, ?)";
  	          PreparedStatement statement = connection.prepareStatement(sql);
  	          statement.setInt(1, car.getCar_id());
  	          statement.setString(2, car.getName());
  	          statement.setString(3, car.getBrand());
  	          statement.setString(4, car.getFuel_type());
  	          statement.setDouble(5, car.getPrice());

  	          int rowsInserted = statement.executeUpdate();
  	          if (rowsInserted > 0) {
  	              System.out.println(car.getName() + " added to the database..!!");
  	          } else {
  	              System.out.println("Failed to insert " + car.getName() + " to the database.");
  	          }
  	      } catch (SQLException e) {
  	          e.printStackTrace();
  	      }
			return false;
  	    }

  	    public boolean deleteCarDetails(int carId) throws SQLException {
  	        String sql = "DELETE FROM car_table WHERE car_id=?";
  	        PreparedStatement statement = connection.prepareStatement(sql);
  	        statement.setInt(1, carId);

  	        return statement.executeUpdate() > 0;
  	    }

  	    public void closeConnection() {
  	        try {
  	            if (connection != null) {
  	                connection.close();
  	            }
  	        } catch (SQLException e) {
  	            e.printStackTrace();
  	        }
  	    }

  	public void searchByName() {
  		try {
  	        if (!carsExist()) {
  	            System.out.println("No car details found..!!");
  	            return;
  	        }

  	        System.out.print("Enter car name : ");
  	        Scanner scanner = new Scanner(System.in);
  	        String name = scanner.next();

  	        String sql = "SELECT * FROM car_table WHERE name=?";
  	        PreparedStatement statement = connection.prepareStatement(sql);
  	        statement.setString(1, name);
  	        ResultSet resultSet = statement.executeQuery();

  	        if (!resultSet.isBeforeFirst()) {
  	            System.out.println("No cars found with the name: " + name);
  	        } else {
  	            System.out.println("Car details for name '" + name + "':");
  	            System.out.println("ID\t" + "Name\t" + "Brand\t" + "Fuel Type\t" + "Price");
  	            System.out.println("=======================================================");
  	            while (resultSet.next()) {
  	                int car_id = resultSet.getInt("car_id");
  	                String brand = resultSet.getString("brand");
  	                String fuel_type = resultSet.getString("fuel_type");
  	                double price = resultSet.getDouble("price");

  	                System.out.println(car_id + "\t" + name + "\t" + brand + "\t" + fuel_type + "\t\t" + price);
  	            }
  	        }
  	    } catch (SQLException e) {
  	        e.printStackTrace();
  	    }
  	}

  	public void searchByBrand() {
  		 try {
  		        if (!carsExist()) {
  		            System.out.println("No car details found..!!");
  		            return;
  		        }

  		        System.out.print("Enter car brand : ");
  		        Scanner scanner = new Scanner(System.in);
  		        String brand = scanner.next();

  		        String sql = "SELECT * FROM car_table WHERE brand=?";
  		        PreparedStatement statement = connection.prepareStatement(sql);
  		        statement.setString(1, brand);
  		        ResultSet resultSet = statement.executeQuery();

  		        if (!resultSet.isBeforeFirst()) {
  		            System.out.println("No cars found with the brand: " + brand);
  		        } else {
  		            System.out.println("Car details for brand '" + brand + "':");
  		            System.out.println("ID\t" + "Name\t" + "Brand\t" + "Fuel Type\t" + "Price");
  		            System.out.println("=======================================================");
  		            while (resultSet.next()) {
  		                int car_id = resultSet.getInt("car_id");
  		                String name = resultSet.getString("name");
  		                String fuel_type = resultSet.getString("fuel_type");
  		                double price = resultSet.getDouble("price");

  		                System.out.println(car_id + "\t" + name + "\t" + brand + "\t" + fuel_type + "\t\t" + price);
  		            }
  		        }
  		    } catch (SQLException e) {
  		        e.printStackTrace();
  		    }
  	}

  	public void searchByFuelType() {
  		 try {
  		        if (!carsExist()) {
  		            System.out.println("No car details found..!!");
  		            return;
  		        }

  		        System.out.print("Enter car fuel type : ");
  		        Scanner scanner = new Scanner(System.in);
  		        String fuel_type = scanner.next();

  		        String sql = "SELECT * FROM car_table WHERE fuel_type=?";
  		        PreparedStatement statement = connection.prepareStatement(sql);
  		        statement.setString(1, fuel_type);
  		        ResultSet resultSet = statement.executeQuery();

  		        if (!resultSet.isBeforeFirst()) {
  		            System.out.println("No cars found with the fuel type: " + fuel_type);
  		        } else {
  		            System.out.println("Car details for fuel type '" + fuel_type + "':");
  		            System.out.println("ID\t" + "Name\t" + "Brand\t" + "Fuel Type\t" + "Price");
  		            System.out.println("=======================================================");
  		            while (resultSet.next()) {
  		                int car_id = resultSet.getInt("car_id");
  		                String name = resultSet.getString("name");
  		                String brand = resultSet.getString("brand");
  		                double price = resultSet.getDouble("price");

  		                System.out.println(car_id + "\t" + name + "\t" + brand + "\t" + fuel_type + "\t\t" + price);
  		            }
  		        }
  		    } catch (SQLException e) {
  		        e.printStackTrace();
  		    }
  	}

  	public void editCarDetails() {
  		 try {
  		        if (!carsExist()) {
  		            System.out.println("No car details found..!!");
  		            return;
  		        }

  		        System.out.print("Enter car id to update : ");
  		        Scanner scanner = new Scanner(System.in);
  		        int car_id = scanner.nextInt();

  		        String sql = "SELECT * FROM car_table WHERE car_id=?";
  		        PreparedStatement selectStatement = connection.prepareStatement(sql);
  		        selectStatement.setInt(1, car_id);
  		        ResultSet resultSet = selectStatement.executeQuery();

  		        if (!resultSet.isBeforeFirst()) {
  		            System.out.println("No car found with ID: " + car_id);
  		        } else {
  		           
  		            System.out.print("Enter new car name : ");
  		            String name = scanner.next();
  		            System.out.print("Enter new car brand : ");
  		            String brand = scanner.next();
  		            System.out.print("Enter new car fuel type : ");
  		            String fuel_type = scanner.next();
  		            System.out.print("Enter new car price : ");
  		            double price = scanner.nextDouble();

  		            
  		            sql = "UPDATE car_table SET name=?, brand=?, fuel_type=?, price=? WHERE car_id=?";
  		            PreparedStatement updateStatement = connection.prepareStatement(sql);
  		            updateStatement.setString(1, name);
  		            updateStatement.setString(2, brand);
  		            updateStatement.setString(3, fuel_type);
  		            updateStatement.setDouble(4, price);
  		            updateStatement.setInt(5, car_id);

  		            int rowsUpdated = updateStatement.executeUpdate();
  		            if (rowsUpdated > 0) {
  		                System.out.println("Car details updated..!!");
  		            } else {
  		                System.out.println("Failed to update car details with ID: " + car_id);
  		            }
  		        }
  		    } catch (SQLException e) {
  		        e.printStackTrace();
  		    }
  	}
  }


 
  