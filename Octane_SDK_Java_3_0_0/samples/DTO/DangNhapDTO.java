/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Linh
 */
public class DangNhapDTO
{

    String hoten;
    String tk;
    String mk;

    public DangNhapDTO(String hoten, String tk, String mk)
    {
        this.hoten = hoten;
        this.tk = tk;
        this.mk = mk;
    }

    public String getHoten()
    {
        return hoten;
    }

    public void setHoten(String hoten)
    {
        this.hoten = hoten;
    }

    public String getTk()
    {
        return tk;
    }

    public void setTk(String tk)
    {
        this.tk = tk;
    }

    public String getMk()
    {
        return mk;
    }

    public void setMk(String mk)
    {
        this.mk = mk;
    }

}
