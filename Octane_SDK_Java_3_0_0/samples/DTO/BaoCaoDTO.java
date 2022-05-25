/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Linh
 */
public class BaoCaoDTO
{

    private String order_id;
    private String order_date;
    private String product_id;
    private String product_name;
    private int order_quantity;
    private String tag_id;
    private String tag_gate_in;
    private String tag_date_in;
    private String tag_gate_out;
    private String tag_date_out;
    private int status;

    public BaoCaoDTO()
    {
    }

    public BaoCaoDTO(String order_id, String order_date, int status, String product_id, String product_name, int order_quantity, String tag_id, String tag_gate_in, String tag_date_in, String tag_gate_out, String tag_date_out)
    {
        this.order_id = order_id;
        this.order_date = order_date;
        this.status = status;
        this.product_id = product_id;
        this.product_name = product_name;
        this.order_quantity = order_quantity;
        this.tag_id = tag_id;
        this.tag_gate_in = tag_gate_in;
        this.tag_date_in = tag_date_in;
        this.tag_gate_out = tag_gate_out;
        this.tag_date_out = tag_date_out;
    }

    public String getOrder_id()
    {
        return order_id;
    }

    public void setOrder_id(String order_id)
    {
        this.order_id = order_id;
    }

    public String getOrder_date()
    {
        return order_date;
    }

    public void setOrder_date(String order_date)
    {
        this.order_date = order_date;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getProduct_id()
    {
        return product_id;
    }

    public void setProduct_id(String product_id)
    {
        this.product_id = product_id;
    }

    public String getProduct_name()
    {
        return product_name;
    }

    public void setProduct_name(String product_name)
    {
        this.product_name = product_name;
    }

    public int getOrder_quantity()
    {
        return order_quantity;
    }

    public void setOrder_quantity(int order_quantity)
    {
        this.order_quantity = order_quantity;
    }

    public String getTag_id()
    {
        return tag_id;
    }

    public void setTag_id(String tag_id)
    {
        this.tag_id = tag_id;
    }

    public String getTag_gate_out()
    {
        return tag_gate_out;
    }

    public void setTag_gate_out(String tag_gate_out)
    {
        this.tag_gate_out = tag_gate_out;
    }

    public String getTag_date_out()
    {
        return tag_date_out;
    }

    public void setTag_date_out(String tag_date_out)
    {
        this.tag_date_out = tag_date_out;
    }

    public String getTag_gate_in()
    {
        return tag_gate_in;
    }

    public void setTag_gate_in(String tag_gate_in)
    {
        this.tag_gate_in = tag_gate_in;
    }

    public String getTag_date_in()
    {
        return tag_date_in;
    }

    public void setTag_date_in(String tag_date_in)
    {
        this.tag_date_in = tag_date_in;
    }

    @Override
    public String toString()
    {
        return "BaoCaoDTO{" + "order_id=" + order_id + ", order_date=" + order_date + ", status=" + status + ", product_id=" + product_id + ", product_name=" + product_name + ", order_quantity=" + order_quantity + ", tag_id=" + tag_id + ", tag_gate_in=" + tag_gate_in + ", tag_date_in=" + tag_date_in + ", tag_gate_out=" + tag_gate_out + ", tag_date_out=" + tag_date_out + '}';
    }

}
