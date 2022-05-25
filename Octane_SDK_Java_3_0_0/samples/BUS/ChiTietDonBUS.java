/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.LastIdDAO;
import DAO.ChiTietDonDAO;
import DTO.ChiTietDonHangDTO;
import java.util.ArrayList;

/**
 *
 * @author Linh
 */
public class ChiTietDonBUS
{

    ChiTietDonDAO chiTietDonDAO = new ChiTietDonDAO();
    Utils ult = new Utils();
    LastIdDAO lastIdDAO = new LastIdDAO();

    public ArrayList<ChiTietDonHangDTO> getList()
    {
        return chiTietDonDAO.getList();
    }

    public boolean insertOrderDetail(ArrayList<ChiTietDonHangDTO> chiTietDonList)
    {
        ult.createOrderDetailId(chiTietDonList);
        if (chiTietDonDAO.insertOrderDetail(chiTietDonList))
        {
            String temp[] = chiTietDonList.get(chiTietDonList.size() - 1).getOrderDetailId().split("_");
            System.out.println("temp[1] OrderDetail: " + temp[1]);
            lastIdDAO.updateOrderDetailId(temp[1]);
            return true;
        }
        return false;
    }
}
