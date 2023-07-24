package org.faol;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FindRowByName {
    CommonMethodsImpl commonMethods = new CommonMethodsImpl();
    private final Properties properties =  ConnectionsSetting.getConnectionInstance().databaseProperties();
    public void findRowByName() {

        String findRow = "select * from students where name = ? ";

        try (
                Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
                Statement statement = connection.createStatement();
                ResultSet resultSetSelectAll = statement.executeQuery("select * from students");
                //PreparedStatement is used when the SQL query has values to be defined:
                PreparedStatement statementIdVerification = connection.prepareStatement(findRow)

        ) {
            if (resultSetSelectAll.next()) { //Check that the list is not empty:

                String name = commonMethods.insertName();

                statementIdVerification.setString(1, name);


                try (ResultSet resultSetIdVerification = statementIdVerification.executeQuery()) {
                    if (resultSetIdVerification.next()) {

                        name = resultSetIdVerification.getString("name");
                        String lastname = resultSetIdVerification.getString("lastname");
                        boolean active = resultSetIdVerification.getBoolean("is_active");

                        System.out.println("Row found");

                        System.out.println(
                                "Name: " + name + "\n" +
                                        "Lastname: " + lastname + "\n" +
                                        "Is_active: " + active + "\n"
                        );

                    } else {
                        System.out.println("Row not found.");
                    }
                }
            } else {
                System.out.println("Empty list.Nothing to find");
            }

        } catch (SQLException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);

        }
    }
}
