package org.faol;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InsertRow {

    private final Properties properties = ConnectionsSetting.getConnectionInstance().databaseProperties();

    private String name;
    private String lastname;
    private boolean is_active;

    public void insertVariables() {

        try {

            Scanner scanner = new Scanner(System.in);

            System.out.println("Insert new row.");

            System.out.println("Student name: ");
            name = scanner.nextLine();

            System.out.println("Student lastname: ");
            lastname = scanner.nextLine();

            System.out.println("Student is_active?: ");
            is_active = scanner.nextBoolean();

        } catch (Exception e) {

            System.out.println(e.getMessage());
            System.out.println("Invalid entry.Try again");
            insertVariables();

        }
    }
    public void insertRow() {

        insertVariables();

        String insert = "insert into students (name, lastname, is_active) values (?, ?, ?)";

        try (
                Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
                //PreparedStatement is used when the SQL query has values to be defined:
                PreparedStatement preparedStatement = connection.prepareStatement(insert)
        ) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastname);
            preparedStatement.setBoolean(3, is_active);
            preparedStatement.executeUpdate();
            System.out.println("New student added");

        } catch (SQLException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);

        }
    }
}
