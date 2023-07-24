package org.faol;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FindAll {
    public void findAll(){

        Properties properties =  ConnectionsSetting.getConnectionInstance().databaseProperties();

        boolean hasStudents = false;

        try (
                Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
                //1)First, the statement is created
                Statement statement = connection.createStatement();
                //2)Then through ResultSet the SQL query is passed to it
                ResultSet resultSet = statement.executeQuery("select * from students")

        ) {

            System.out.println("All rows: ");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                boolean active = resultSet.getBoolean("is_active");

                int rowNumber = resultSet.getRow();
                System.out.println("Row " + rowNumber + " : ");
                System.out.println(
                        "Id: " + id + "\n" +
                        "Name: " + name + "\n" +
                                "Lastname: " + lastname + "\n" +
                                "Is_active: " + active + "\n" +
                                "-------------------------------------"
                );

                hasStudents = true;

            }

        } catch (SQLException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);

        }

        if (!hasStudents) {
            System.out.println("Empty list.Nothing to show.");
        }
    }
}
