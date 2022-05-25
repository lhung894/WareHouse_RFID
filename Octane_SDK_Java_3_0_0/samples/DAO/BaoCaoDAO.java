/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.BaoCaoDTO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Linh
 */
public class BaoCaoDAO
{

    Connect conn;

    public BaoCaoDAO()
    {
    }

    public ArrayList<BaoCaoDTO> getBaoCaosByOrderId(String orderId)
    {
        ArrayList<BaoCaoDTO> baoCaoList = new ArrayList<BaoCaoDTO>();
        conn = new Connect();
        conn.getConnection();
        String query = "select op.order_id,op.order_date,op.status,op.product_id,op.product_name,op.order_quantity,Tag.tag_id,Tag.tag_gate_in,Tag.tag_date_in,Tag.tag_gate_out,Tag.tag_date_out \n"
                + "from (select temp.order_id,temp.product_id,Product.product_name,temp.order_date,temp.order_quantity,temp.status from \n"
                + "(select Order_Detail.order_id,Order_Detail.product_id,Order_Detail.order_quantity,Order_Product.order_date,Order_Product.status \n"
                + "from Order_Detail left join Order_Product \n"
                + "on Order_Detail.order_id = Order_Product.order_id) temp left join Product \n"
                + "on temp.product_id = Product.product_id) op left join Tag on Tag.order_id = op.order_id and Tag.product_id = op.product_id \n"
                + "where op.order_id = '" + orderId + "';";
        try
        {
            conn.executeQuery(query);
            while (conn.rs.next())
            {
                BaoCaoDTO baoCao = new BaoCaoDTO();
                baoCao.setOrder_id(conn.rs.getString(1));
                baoCao.setOrder_date(conn.rs.getString(2));
                baoCao.setStatus(conn.rs.getInt(3));
                baoCao.setProduct_id(conn.rs.getString(4));
                baoCao.setProduct_name(conn.rs.getString(5));
                baoCao.setOrder_quantity(conn.rs.getInt(6));
                baoCao.setTag_id(conn.rs.getString(7));
                baoCao.setTag_gate_in(conn.rs.getString(8));
                baoCao.setTag_date_in(conn.rs.getString(9));
                baoCao.setTag_gate_out(conn.rs.getString(10));
                baoCao.setTag_date_out(conn.rs.getString(11));
                baoCaoList.add(baoCao);
            }
        } catch (SQLException e)
        {
            System.out.println("L?i getList BaoCaoDAO " + e.getMessage());
        }
        try
        {
            conn.getConn().close();
        } catch (SQLException e)
        {
            System.out.println("L?i close BaoCaoDAO" + e.getMessage());
        }
        return baoCaoList;
    }
}
