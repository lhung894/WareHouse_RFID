/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.RFIDTagDAO;
import DTO.RFIDTagDTO;
import java.util.ArrayList;

/**
 *
 * @author Linh
 */
public class RFIDTagBUS
{

    RFIDTagDAO tagDAO = new RFIDTagDAO();

    public ArrayList<RFIDTagDTO> getList()
    {
        return tagDAO.getList();
    }

    public boolean insertTag(RFIDTagDTO tag)
    {
        if (tagDAO.insertTag(tag))
        {
            System.out.println("Thêm Tag ok!!!");
            return true;
        }
        System.out.println("Thêm Tag th?t bai!!!");
        return false;
    }

    public boolean updateTagsOut(ArrayList<RFIDTagDTO> tagList)
    {
        if (tagDAO.updateTagsOut(tagList))
        {
            System.out.println("Câp nhât Tag ok!!!");
            return true;
        }
        System.out.println("Câp nhât Tag thât bai!!!");
        return false;
    }

    public int countSanPhamId(String product_id)
    {
        return tagDAO.countProductId(product_id);
    }
}
