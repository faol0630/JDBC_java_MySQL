package org.faol;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteRowByName {
    CommonMethodsImpl commonMethods = new CommonMethodsImpl();
    private final Properties properties =  ConnectionsSetting.getConnectionInstance().databaseProperties();
    public void deleteRowByName() {

        String delete = "delete from students where name = ? ";

        String findRow = "select * from students where name = ? ";

        try (
                Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
                PreparedStatement statementDeleteId = connection.prepareStatement(delete);
                //To verify that the table in the DB is not empty:
                Statement statement = connection.createStatement();
                ResultSet resultSetSelectAll = statement.executeQuery("select * from students");
                PreparedStatement statementIdVerification = connection.prepareStatement(findRow)

        ) {

            if (resultSetSelectAll.next()) { //Check that the list is not empty:

                String name = commonMethods.insertName();
                statementIdVerification.setString(1, name);

                try (ResultSet resultSetIdVerification = statementIdVerification.executeQuery()) {
                    if (resultSetIdVerification.next()) {
                        statementDeleteId.setString(1, name);
                        statementDeleteId.executeUpdate();
                        System.out.println("Student deleted");
                    } else {
                        System.out.println("Row not found.");
                    }
                }

            }else{
                System.out.println("Empty list.Nothing to delete");
            }

        } catch (SQLException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);

        }
    }
}
