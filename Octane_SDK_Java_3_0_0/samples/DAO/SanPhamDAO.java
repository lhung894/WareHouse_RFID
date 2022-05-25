/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.SanPhamDTO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Linh
 */
public class SanPhamDAO
{

    Connect conn;

    public SanPhamDAO()
    {
    }

    public ArrayList<SanPhamDTO> getList()
    {
        ArrayList<SanPhamDTO> sanPhamList = new ArrayList<SanPhamDTO>();
        conn = new Connect();
        conn.getConnection();
        String query = "select * from Product";
        try
        {
            conn.executeQuery(query);
            while (conn.rs.next())
            {
                SanPhamDTO sanPham = new SanPhamDTO();
                sanPham.setProductId(conn.rs.getString(1));
                sanPham.setProductName(conn.rs.getString(2));
                sanPham.setProductQuantity(conn.rs.getInt(3));
                sanPham.setProductDetail(conn.rs.getString(4));
                sanPhamList.add(sanPham);
            }
        } catch (SQLException e)
        {
            System.out.println("Lôi SanPhamDAO.getList" + e.getMessage());
        }
        try
        {
            conn.getConn().close();
        } catch (SQLException e)
        {
            System.out.println("Lôi SanPhamDAO.close." + e.getMessage());
        }
        return sanPhamList;
    }

    public boolean updateProductQuantity(SanPhamDTO sanPham)//Gan Tag
    {
        conn = new Connect();
        conn.getConnection();
        String sql = "UPDATE Product SET"
                + " product_quantity=" + sanPham.getProductQuantity()
                + " WHERE product_id='" + sanPham.getProductId() + "';";
        if (conn.executeUpdate(sql))
        {
            conn.close();
            System.out.println("Câp nhât SanPhamDAO ok!!!.");
            return true;
        }
        conn.close();
        System.out.println("Câp Nhât SanPhamDAO thât bai!!!!");
        return false;
    }

    public boolean updateProductsQuantity(ArrayList<SanPhamDTO> sanPhamList)//Tao Don
    {
        conn = new Connect();
        conn.getConnection();
        String sql = "";
        for (SanPhamDTO sanPham : sanPhamList)
        {
            sql += "UPDATE Product SET"
                    + " product_quantity=" + sanPham.getProductQuantity()
                    + " WHERE product_id='" + sanPham.getProductId() + "';";
        }
        if (conn.executeUpdate(sql))
        {
            conn.close();
            System.out.println("Câp nhât SanPhamDAO ok!!!.");
            return true;
        }
        conn.close();
        System.out.println("Câp nhât SanPhamDAO thât bai!!!.");
        return false;
    }
}
