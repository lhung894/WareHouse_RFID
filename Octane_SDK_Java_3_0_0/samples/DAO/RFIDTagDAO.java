/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.RFIDTagDTO;
import java.beans.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Linh
 */
public class RFIDTagDAO
{

    Connect conn;

    public RFIDTagDAO()
    {
    }

    public ArrayList<RFIDTagDTO> getList()
    {
        ArrayList<RFIDTagDTO> tagList = new ArrayList<RFIDTagDTO>();
        conn = new Connect();
        conn.getConnection();
        String query = "select * from Tag";
        try
        {
            conn.executeQuery(query);
            while (conn.rs.next())
            {
                RFIDTagDTO tag = new RFIDTagDTO();
                tag.setTagId(conn.rs.getString(1));
                tag.setProductId(conn.rs.getString(2));
                tag.setTagGateIn(conn.rs.getString(3));
                tag.setTagDateIn(conn.rs.getString(4));
                tag.setTagGateOut(conn.rs.getString(5));
                tag.setTagDateOut(conn.rs.getString(6));
                tag.setOrderId(conn.rs.getString(7));
                tagList.add(tag);
            }
        } catch (SQLException e)
        {
            System.out.println(e);
            System.out.println("Lôi TagDAO.getList" + e.getMessage());
        }
        try
        {
            conn.getConn().close();
        } catch (SQLException e)
        {
            System.out.println("Lôi TagDAO.close" + e.getMessage());
        }
        return tagList;
    }

    public boolean insertTag(RFIDTagDTO tag)
    {
        conn = new Connect();
        conn.getConnection();
        //(tag_id,product_id,tag_gate_in,tag_date_in,tag_gate_out,tag_date_out)
        String query = "INSERT INTO Tag (tag_id,product_id,tag_gate_in,tag_date_in,tag_gate_out,tag_date_out,order_id)"
                + " VALUES ('" + tag.getTagId()
                + "','" + tag.getProductId() + "'"
                + ",'" + tag.getTagGateIn() + "'"
                + ",'" + tag.getTagDateIn() + "'"
                + ",'" + tag.getTagGateOut() + "'"
                + "," + tag.getTagDateOut()
                + ",'" + tag.getOrderId() + "')";
        System.out.println("query: " + query);
        if (conn.executeUpdate(query))
        {
            conn.close();
            System.out.println("Thêm RFIDTagDAO ok1!!!.");
            return true;
        }
        conn.close();
        System.out.println("Thêm RFIDTagDAO thât bai!!!!");
        return false;
    }

    public boolean updateTagsIn(RFIDTagDTO tag, boolean check)
    {
        conn = new Connect();
        conn.getConnection();
        String sql = "UPDATE Tag SET"
                + " tag_gate_in='" + tag.getTagGateIn() + "'"
                + ", " + "tag_date_in='" + tag.getTagDateIn() + "'"
                + " WHERE tag_id='" + tag.getTagId() + "';";
        if (conn.executeUpdate(sql))
        {
            conn.close();
            System.out.println("Câp nhât RFIDTagDAO ok11!!.");
            return true;
        }
        conn.close();
        System.out.println("Câp nhât RFIDTagDAO thât bai!!.");
        return false;
    }

    public boolean updateTagsOut(ArrayList<RFIDTagDTO> tagList)
    {
        conn = new Connect();
        conn.getConnection();
        String sql = "";
        for (RFIDTagDTO tag : tagList)
        {
            sql += "UPDATE Tag SET"
                    + " tag_gate_out='" + tag.getTagGateOut() + "'"
                    + ", " + "tag_date_out='" + tag.getTagDateOut() + "'"
                    + ", " + "order_id='" + tag.getOrderId() + "'"
                    + " WHERE tag_id='" + tag.getTagId() + "';";
        }
        if (conn.executeUpdate(sql))
        {
            conn.close();
            System.out.println("Câp nhât RFIDTagDAO ok1!!.");
            return true;
        }
        conn.close();
        System.out.println("Câp nhât RFIDTagDAO!!!");
        return false;
    }

    public int countProductId(String product_id)
    {
        conn = new Connect();
        conn.getConnection();
        String query = "select count(*) from Tag where product_id='" + product_id + "'";
        int value = 0;
        try
        {
            conn.executeQuery(query);
            while (conn.rs.next())
            {
                value = conn.rs.getInt(1);
            }
        } catch (SQLException e)
        {
            System.out.println("Lôi TagDAO.countSanPham" + e.getMessage());
        }
        try
        {
            conn.getConn().close();
        } catch (SQLException e)
        {
            System.out.println("Lôi TagDAO.close" + e.getMessage());
        }
        return value;
    }

    public static void main(String[] args)
    {
        RFIDTagDAO rFIDTagDAO = new RFIDTagDAO();
        System.out.println("Có t?ng công là : " + rFIDTagDAO.countProductId("PRO_3"));
    }
}
