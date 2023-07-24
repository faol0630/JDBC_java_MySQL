package org.faol;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateRow {
    CommonMethodsImpl commonMethods = new CommonMethodsImpl();
    private final Properties properties =  ConnectionsSetting.getConnectionInstance().databaseProperties();
    private String lastname = "";
    private boolean is_active = false;

    public void insertNewVariables() {

        try{

            Scanner scanner = new Scanner(System.in);

            System.out.println("Updating row... ");

            System.out.println("Student lastname: ");
            lastname = scanner.nextLine();

            System.out.println("Student is_active?: ");
            is_active = scanner.nextBoolean();

        }catch(Exception e){

            System.out.println(e.getMessage());
            System.out.println("Invalid entry.Try again");
            insertNewVariables();
        }

    }
    public void updateRow() {

        String update = "update students set lastname = ?, is_active = ? where name = ?";

        String findRow = "select * from students where name = ? ";

        try (
                Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);

                //To verify that the table in the DB is not empty:
                Statement statement = connection.createStatement();
                //Statement is used with SQL queries where there are no values to be defined:
                ResultSet resultSet = statement.executeQuery("select * from students");
                //PreparedStatement is used when the SQL query has values to be defined:
                PreparedStatement preparedStatement = connection.prepareStatement(update);
                //To verify that the id exists
                //PreparedStatement is used when the SQL query has values to be defined:
                PreparedStatement preparedStatement1 = connection.prepareStatement(findRow)

        ) {

            if (resultSet.next()) { //Check that the list is not empty:


                String name = commonMethods.insertName();

                preparedStatement1.setString(1, name);

                try (ResultSet resultSet1 = preparedStatement1.executeQuery()) {
                    if (resultSet1.next()) {//To verify that the student exists

                        insertNewVariables();

                        preparedStatement.setString(1, lastname);
                        preparedStatement.setBoolean(2, is_active);
                        preparedStatement.setString(3, name);
                        preparedStatement.executeUpdate();
                        System.out.println("Updated student");

                    } else {
                        System.out.println("Row not found.");
                    }
                }

            } else {
                System.out.println("Empty list.Nothing to update");
            }

        } catch (SQLException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);

        }
    }
}
