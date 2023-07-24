package org.faol;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FindRowById {
    CommonMethodsImpl commonMethods = new CommonMethodsImpl();
    private final Properties properties = ConnectionsSetting.getConnectionInstance().databaseProperties();
    public void findRowById() {

        String findRow = "select * from students where id = ? ";

        try (
                Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
                Statement statement = connection.createStatement();
                ResultSet resultSetSelectAll = statement.executeQuery("select * from students");
                PreparedStatement statementIdVerification = connection.prepareStatement(findRow)

        ) {
            if (resultSetSelectAll.next()) { //Check that the list is not empty:

                int id = commonMethods.insertId();

                statementIdVerification.setInt(1, id);


                try (ResultSet resultSetIdVerification = statementIdVerification.executeQuery()) { //To verify that the id exists:
                    if (resultSetIdVerification.next()) {

                        id = resultSetIdVerification.getInt("id");
                        String name = resultSetIdVerification.getString("name");
                        String lastname = resultSetIdVerification.getString("lastname");
                        boolean active = resultSetIdVerification.getBoolean("is_active");

                        System.out.println("Row found");

                        System.out.println(
                                "Id: " + id + "\n" +
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
