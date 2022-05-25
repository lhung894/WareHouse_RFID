/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.DonHangDTO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Linh
 */
public class DonHangDAO
{

    Connect conn;

    public DonHangDAO()
    {
    }

    public ArrayList<DonHangDTO> getList()
    {
        ArrayList<DonHangDTO> donHangList = new ArrayList<DonHangDTO>();
        conn = new Connect();
        conn.getConnection();
        String query = "select * from Order_Product";
        try
        {
            conn.executeQuery(query);
            while (conn.rs.next())
            {
                DonHangDTO donHang = new DonHangDTO();
                donHang.setOrderId(conn.rs.getString(1));
                donHang.setOrderDate(conn.rs.getString(2));
                donHang.setStatus(conn.rs.getInt(3));
                donHangList.add(donHang);
            }
        } catch (SQLException e)
        {
            System.out.println("L?i DonHangDAO getList " + e.getMessage());
        }
        try
        {
            conn.getConn().close();
        } catch (SQLException e)
        {
            System.out.println("L?i DonHangDAO close " + e.getMessage());
        }
        return donHangList;
    }

    public boolean insertOrder(DonHangDTO donHang)
    {
        conn = new Connect();
        conn.getConnection();
        String query = "INSERT INTO Order_Product"
                + " VALUES ('" + donHang.getOrderId() + "'"
                + ",'" + donHang.getOrderDate() + "'"
                + "," + donHang.getStatus() + ")";
        if (conn.executeUpdate(query))
        {
            conn.close();
            System.out.println("Thêm DonHangDAO ok!!!");
            return true;
        }
        conn.close();
        System.out.println("Thêm DonHangDAO thât bai!!!");
        return false;
    }

    public boolean updateOrderCompleted(String orderId)
    {
        conn = new Connect();
        conn.getConnection();
        String query = "UPDATE Order_Product SET"
                + " Status=3"
                + " WHERE order_id='" + orderId + "';";
        if (conn.executeUpdate(query))
        {
            conn.close();
            System.out.println("Câp nhât DonHangDAO ok!!!");
            return true;
        }
        conn.close();
        System.out.println("Câp nhât DonHangDAO thât bai!!!");
        return false;
    }
}
