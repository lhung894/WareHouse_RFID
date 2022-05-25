/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;
import java.util.ArrayList;

/**
 *
 * @author Linh
 */
public class SanPhamBUS
{

    SanPhamDAO sanPhamDAO = new SanPhamDAO();

    public ArrayList<SanPhamDTO> getList()
    {
        return sanPhamDAO.getList();
    }

    public boolean updateProduct(SanPhamDTO sanPham)
    {
        if (sanPhamDAO.updateProductQuantity(sanPham))
        {
            System.out.println("Câp nhât san phâm ok!!!");
            return true;
        }
        System.out.println("Câp nhât san phâm that bai!!!");
        return false;
    }

    public boolean updateProducts(ArrayList<SanPhamDTO> sanPhamList)
    {
        if (sanPhamDAO.updateProductsQuantity(sanPhamList))
        {
            System.out.println("Câp nhât san phâm ok!!!");
            return true;
        }
        System.out.println("Câp nhât san phâm that bai!!!");
        return false;
    }
}
