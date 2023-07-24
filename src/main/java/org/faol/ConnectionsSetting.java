package org.faol;

import java.util.Properties;

public class ConnectionsSetting {
    //singleton
    private ConnectionsSetting() {
    }
    private static ConnectionsSetting connectionsSetting;

    public synchronized static ConnectionsSetting getConnectionInstance(){
        if (connectionsSetting == null){
            connectionsSetting = new ConnectionsSetting();
        }
        return connectionsSetting;
    }
    public Properties databaseProperties() {
        java.util.Properties properties = new java.util.Properties();
        properties.setProperty("url", "jdbc:mysql://localhost:3306/jdbc1");
        properties.setProperty("user", "root");
        properties.setProperty("password", "");
        return properties;
    }

}

