/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.BaoCaoDAO;
import DTO.BaoCaoDTO;
import DTO.DonHangDTO;
import DTO.ChiTietDonHangDTO;
import DTO.SanPhamDTO;
import DTO.RFIDTagDTO;
import java.util.ArrayList;

/**
 *
 * @author Linh
 */
public class BaoCaoBUS
{

    private BaoCaoDAO baoCaoDAO;

    public ArrayList<BaoCaoDTO> createBaoCao(ArrayList<DonHangDTO> donList)
    {
        baoCaoDAO = new BaoCaoDAO();
        ArrayList<BaoCaoDTO> baoCaoDTOs = new ArrayList<>();
        for (DonHangDTO don : donList)
        {
            baoCaoDTOs.addAll(baoCaoDTOs.size(), baoCaoDAO.getBaoCaosByOrderId(don.getOrderId()));
        }
        System.out.println("Danh sách báo cáo: " + baoCaoDTOs);
        return baoCaoDTOs;
    }
}
