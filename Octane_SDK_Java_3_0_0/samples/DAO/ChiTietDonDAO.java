/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietDonHangDTO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Linh
 */
public class ChiTietDonDAO
{

    Connect conn;

    public ChiTietDonDAO()
    {
    }

    public ArrayList<ChiTietDonHangDTO> getList()
    {
        ArrayList<ChiTietDonHangDTO> chiTietDonList = new ArrayList<ChiTietDonHangDTO>();
        conn = new Connect();
        conn.getConnection();
        String query = "select * from Order_Detail";
        try
        {
            conn.executeQuery(query);
            while (conn.rs.next())
            {
                ChiTietDonHangDTO chiTietDon = new ChiTietDonHangDTO();
                chiTietDon.setOrderDetailId(conn.rs.getString(1));
                chiTietDon.setOrderId(conn.rs.getString(2));
                chiTietDon.setProductId(conn.rs.getString(3));
                chiTietDon.setOrderQuantity(conn.rs.getInt(4));
                chiTietDonList.add(chiTietDon);
            }
        } catch (SQLException e)
        {
            System.out.println("L?i ChiTietDonDAO getList " + e.getMessage());
        }
        try
        {
            conn.getConn().close();
        } catch (SQLException e)
        {
            System.out.println("L?i ChiTietDonDAO close " + e.getMessage());
        }
        return chiTietDonList;
    }

    public boolean insertOrderDetail(ArrayList<ChiTietDonHangDTO> chiTietDonList)
    {
        conn = new Connect();
        conn.getConnection();
        String query = "INSERT INTO Order_Detail"
                + " VALUES ";
        int size = chiTietDonList.size(), count = 1;
        for (ChiTietDonHangDTO chiTietDon : chiTietDonList)
        {
            query += "('" + chiTietDon.getOrderDetailId() + "'"
                    + ",'" + chiTietDon.getOrderId() + "'"
                    + ",'" + chiTietDon.getProductId() + "'"
                    + "," + chiTietDon.getOrderQuantity() + ")";
            if (count < size)
            {
                query += ", ";
                count++;
            } else if (count == size)
            {
                query += ";";
            }
        }
//        System.out.println("query: " + query);       
        if (conn.executeUpdate(query))
        {
            conn.close();
            System.out.println("Thêm ChiTietDonDAO ok!!!.");
            return true;
        }
        conn.close();
        System.out.println("Thêm ChiTietDonDAO thât bai!!!.");
        return false;
    }
}
