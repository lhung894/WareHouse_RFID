/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Linh
 */
public class ChiTietDonHangDTO
{

    private String orderDetailId;
    private String orderId;
    private String productId;
    private int orderQuantity;

    public ChiTietDonHangDTO()
    {
    }

    public ChiTietDonHangDTO(String orderDetailId, String orderId, String productId, int orderQuantity)
    {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.productId = productId;
        this.orderQuantity = orderQuantity;
    }

    public String getOrderDetailId()
    {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId)
    {
        this.orderDetailId = orderDetailId;
    }

    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getProductId()
    {
        return productId;
    }

    public void setProductId(String productId)
    {
        this.productId = productId;
    }

    public int getOrderQuantity()
    {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity)
    {
        this.orderQuantity = orderQuantity;
    }

    @Override
    public String toString()
    {
        return "ChiTietDonHangDTO{" + "orderDetailId=" + orderDetailId + ", orderId=" + orderId + ", productId=" + productId + ", orderQuantity=" + orderQuantity + "}\n";
    }
}
