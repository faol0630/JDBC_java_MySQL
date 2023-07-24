package org.faol;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteRowById {
    CommonMethodsImpl commonMethods = new CommonMethodsImpl();
    private final Properties properties =  ConnectionsSetting.getConnectionInstance().databaseProperties();
    public void deleteRowById() {

        String deleteRow = "delete from students where id = ? ";

        String findRow = "select * from students where id = ? ";

        try (
                Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
                Statement statement = connection.createStatement();
                //To verify that the table in the DB is not empty:
                ResultSet resultSetSelectAll = statement.executeQuery("select * from students");
                PreparedStatement statementDeleteId = connection.prepareStatement(deleteRow);
                //To verify that the id exists:
                PreparedStatement statementIdVerification = connection.prepareStatement(findRow)

        ) {

            if (resultSetSelectAll.next()) {//Check that the list is not empty:

                int id = commonMethods.insertId();

                statementIdVerification.setInt(1, id);

                try (ResultSet resultSetIdVerification = statementIdVerification.executeQuery()) { //Check if id exists:
                    if (resultSetIdVerification.next()) {
                        statementDeleteId.setInt(1, id);
                        statementDeleteId.executeUpdate();
                        System.out.println("Student deleted");
                    }else{
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
