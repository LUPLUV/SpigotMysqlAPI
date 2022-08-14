package de.lupu.smapi.mysql;

import de.lupu.smapi.MysqlAPI;
import de.lupu.smapi.utils.Strings;

import java.sql.*;

public class MySQL {

    public MySQL(String host, String port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    String host;
    String port;
    String database;
    String username;
    String password;
    Connection con;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public void connect() {
        if(!isConnected()){
            MysqlAPI.getPlugin().sendConsoleMessage(Strings.prefix + "Connecting to the Database.");
            MysqlAPI.getPlugin().sendConsoleMessage(Strings.prefix + "Host: '" + host + "'");
            MysqlAPI.getPlugin().sendConsoleMessage(Strings.prefix + "Port: '" + port + "'");
            MysqlAPI.getPlugin().sendConsoleMessage(Strings.prefix + "Database: '" + database + "'");
            MysqlAPI.getPlugin().sendConsoleMessage(Strings.prefix + "Username: '" + username + "'");
            MysqlAPI.getPlugin().sendConsoleMessage(Strings.prefix + "Password: '" + password + "'");
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true&characterEncoding=utf8&useUnicode=true", username, password);
                MysqlAPI.getPlugin().sendConsoleMessage(Strings.prefix + "Successfully connected to the Database. §4❤");
            } catch (SQLException e) {
                MysqlAPI.getPlugin().sendConsoleMessage(Strings.prefix + "An error occurred while closing the Database Connection.");
                e.printStackTrace();
            }
        }
    }

    public void disconnect() {
        if(isConnected()){
            MysqlAPI.getPlugin().sendConsoleMessage(Strings.prefix + "Closing the Database Connection.");
            try {
                con.close();
                MysqlAPI.getPlugin().sendConsoleMessage(Strings.prefix + "Successfully closed Database Connection. §4✘");
            } catch (SQLException e) {
                MysqlAPI.getPlugin().sendConsoleMessage(Strings.prefix + "An error occurred while closing the Database Connection.");
                e.printStackTrace();
            }

        }
    }

    public boolean isConnected(){
        return (con == null ? false : true);
    }

    public void update(String qry){
        try {
            PreparedStatement ps = con.prepareStatement(qry);
            ps.executeUpdate();
        } catch (SQLException e) {
            MysqlAPI.getPlugin().sendConsoleMessage(Strings.prefix + "An error occurred while updating MySQL table.");
            e.printStackTrace();
        }
    }

    public ResultSet getResult(String qry) {
        try {
            PreparedStatement ps = con.prepareStatement(qry);
            return ps.executeQuery();
        } catch (SQLException e) {
            MysqlAPI.getPlugin().sendConsoleMessage(Strings.prefix + "An error occurred while reading MySQL table.");
            e.printStackTrace();
        }
        return null;
    }

}
