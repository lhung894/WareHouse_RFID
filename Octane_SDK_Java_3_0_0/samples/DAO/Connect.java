/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Linh
 */
public class Connect
{

    private static String dbURL = "jdbc:sqlserver://DESKTOP-QDVEDF4;databaseName=PRODUCT;user=sa;password=20112000;";
    // Linh: jdbc:sqlserver://LINHNGH:1433;databaseName=PRODUCT;user=linh;password=12345678;
    //Hung: DESKTOP-QDVEDF4
    private Connection conn;
    Statement st;
    ResultSet rs;

    public Connect()
    {
    }

    public void getConnection()
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e)
        {
            System.out.println(e);
        }
        try
        {
            conn = DriverManager.getConnection(dbURL);
            System.out.println("connect success");
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public boolean executeUpdate(String query)
    {
        try
        {
            conn.createStatement();
            st = conn.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException e)
        {
            System.out.println(e);
            return false;
        }
    }

    public void close()
    {
        try
        {
            conn.close();
        } catch (SQLException e)
        {
            System.out.println("Close error!");
        }
    }

    public Connection getConn()
    {
        return conn;
    }

    public void setConn(Connection conn)
    {
        this.conn = conn;
    }

    public ResultSet executeQuery(String query)
    {
        try
        {
            st = conn.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e)
        {
            System.out.println(e);
        }
        return rs;
    }

}
