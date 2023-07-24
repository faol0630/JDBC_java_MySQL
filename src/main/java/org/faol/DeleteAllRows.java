package org.faol;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteAllRows {
    private final Properties properties = ConnectionsSetting.getConnectionInstance().databaseProperties();
    public void deleteAllRows() {

        String delete = "delete from students ";

        try (
                Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
                PreparedStatement preparedStatement = connection.prepareStatement(delete)

        ) {
            //delete all rows action, is executed anyway:
            int result = preparedStatement.executeUpdate();

            if (result == 0){
                System.out.println("Empty list.Nothing to delete");
            }else{
                System.out.println("All Students deleted");
            }

        } catch (SQLException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);

        }
    }

}
