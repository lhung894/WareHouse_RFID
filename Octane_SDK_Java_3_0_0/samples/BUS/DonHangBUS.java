/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.LastIdDAO;
import DAO.DonHangDAO;
import DTO.DonHangDTO;
import java.util.ArrayList;

/**
 *
 * @author Linh
 */
public class DonHangBUS
{

    DonHangDAO donHangDAO = new DonHangDAO();
    Utils ult = new Utils();
    LastIdDAO lastIdDAO = new LastIdDAO();

    public ArrayList<DonHangDTO> getList()
    {
        return donHangDAO.getList();
    }

    public boolean insertOrder(DonHangDTO donHang)
    {
        //orderDAO.insertOrder(orderDTO)
        if (donHangDAO.insertOrder(donHang))
        {
            String temp[] = donHang.getOrderId().split("_");
            System.out.println("temp[1] Order: " + temp[1]);
            lastIdDAO.updateOrderId(temp[1]);
            System.out.println("Thêm don ok!!!");
            return true;
        }
        System.out.println("Thêm don th?t bai!!!!");
        return false;
    }

    public boolean updateOrderCompleted(String orderId)
    {
        if (donHangDAO.updateOrderCompleted(orderId))
        {
            System.out.println("C?p nh?t don ok!!!!");
            return true;
        }
        System.out.println("C?p nh?t don that bai!!!");
        return false;
    }
}
