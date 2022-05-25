/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.SQLException;

/**
 *
 * @author Linh
 */
public class LastIdDAO
{

    private String productId;
    private String orderId;
    private String orderDetailId;
    Connect conn;

    public LastIdDAO()
    {
        getLastId();
        System.out.println("LastId: " + this.toString());
    }

    public LastIdDAO(String productId, String orderId, String orderDetailId)
    {
        this.productId = productId;
        this.orderId = orderId;
        this.orderDetailId = orderDetailId;
    }

    public void getLastId()
    {
        conn = new Connect();
        conn.getConnection();
        String query = "select * from LastId";
        try
        {
            conn.executeQuery(query);
            while (conn.rs.next())
            {
                setProductId(conn.rs.getString(1));
                setOrderId(conn.rs.getString(2));
                setOrderDetailId(conn.rs.getString(3));
            }
        } catch (SQLException e)
        {
            System.out.println("L?i LastIdDAO " +e.getMessage());
        }
        try
        {
            conn.getConn().close();
        } catch (SQLException e)
        {
            System.out.println("L?i LastIdDAO close " +e.getMessage());
        }
    }

    public boolean updateOrderId(String newOrderId)
    {
        conn = new Connect();
        conn.getConnection();
        String query = "UPDATE LastId SET"
                + " order_id='" + newOrderId + "'"
                + " WHERE order_id='" + getOrderId() + "'";
        if (conn.executeUpdate(query))
        {
            System.out.println("Câp nhât LastId_Order ok!!!");
            setOrderId(newOrderId);
            conn.close();
            return true;
        }
        System.out.println("Câp nhât LastId_Order thât bai!!!");
        conn.close();
        return false;
    }

    public boolean updateOrderDetailId(String newOrderDetailId)
    {
        conn = new Connect();
        conn.getConnection();
        String query = "UPDATE LastId SET"
                + " order_detail_id='" + newOrderDetailId + "'"
                + " WHERE order_detail_id='" + getOrderDetailId() + "'";
        if (conn.executeUpdate(query))
        {
            System.out.println("Câp nhât LastId_OrderDetail ok!!!");
            setOrderDetailId(newOrderDetailId);
            conn.close();
            return true;
        }
        System.out.println("Câp nhât LastId_OrderDetail thât bai!!!");
        conn.close();
        return false;
    }

    public String getProductId()
    {
        return productId;
    }

    public void setProductId(String productId)
    {
        this.productId = productId;
    }

    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getOrderDetailId()
    {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId)
    {
        this.orderDetailId = orderDetailId;
    }

    @Override
    public String toString()
    {
        return "LastIdDAO{" + "productId=" + productId + ", orderId=" + orderId + ", orderDetailId=" + orderDetailId + "}\n";
    }
}
