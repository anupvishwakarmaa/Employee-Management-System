package org.example;

import java.sql.*;

public class EmployeManagement {
    private static final String JDBC_URL="jdbc:mysql://localhost:3306/spark";
    private static final String USERNAME ="admin";
    private static final String PASSWORD="Admin123@";
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD);
//            createEmployeeTable();
//            insertIntoEmployee("hari",21,"hari@gmail.com");
//            fetchEmployee();
//            updateEmployee();
            deleteEmployee();
            fetchEmployee();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void deleteEmployee() throws SQLException {
        String query ="Delete from employee where id=3";
        preparedStatement=connection.prepareStatement(query);
        int n=preparedStatement.executeUpdate();
        if(n>0){
            System.out.println("Deleted");
        }else{
            System.out.println("Deletion failed");
        }
    }

    private static void updateEmployee() throws SQLException {
      String query="UPDATE employee set name='Hariom' where id=3";
      preparedStatement=connection.prepareStatement(query);
      int n=preparedStatement.executeUpdate();
      if(n>0) {
          System.out.println("Executed succesfully");
      }else{
          System.out.println("Execution failed");
      }

    }

    private static void fetchEmployee() throws SQLException {
        String query="Select * from employee";
        preparedStatement=connection.prepareStatement(query);
        ResultSet resultSet= preparedStatement.executeQuery();
        while(resultSet.next()){
            System.out.println(resultSet.getInt("id"));
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getInt("age"));
            System.out.println(resultSet.getString("email"));
        }

    }

    private static void insertIntoEmployee(String name, int age, String email) throws SQLException {
        String query="Insert into employee(name,age,email) values(?,?,?)";
        preparedStatement=connection.prepareStatement(query);
        preparedStatement.setString(1,name);
        preparedStatement.setInt(2,age);
        preparedStatement.setString(3,email);
        preparedStatement.executeUpdate();

    }

    private static void createEmployeeTable() throws SQLException {
        String createTable="CREATE TABLE IF NOT EXISTS employee(id INT AUTO_INCREMENT PRIMARY KEY,"+
                "name varchar(100),"
               +"age INT,"
                +"email varchar(30) "
                +")";
        preparedStatement=connection.prepareStatement(createTable);
        preparedStatement.execute();
        System.out.println("Table created ......");
    }
}
