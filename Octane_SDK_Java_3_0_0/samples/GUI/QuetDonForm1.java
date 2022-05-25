/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BUS.DonHangBUS;
import BUS.ChiTietDonBUS;
import BUS.SanPhamBUS;
import BUS.RFIDTagBUS;
import DTO.DonHangDTO;
import DTO.ChiTietDonHangDTO;
import DTO.SanPhamDTO;
import DTO.RFIDTagDTO;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author Hung
 */
public class QuetDonForm1 extends javax.swing.JFrame
{

    int rowOrder = -2, rowDetail = -2;
    String orderId = "";
    public static String errorString = "";

    DefaultTableModel tbModelDon, tbModelChiTietDon;
    RFIDTagBUS tagBUS = new RFIDTagBUS();
    DonHangBUS donHangBUS = new DonHangBUS();
    ChiTietDonBUS chiTietDonBUS = new ChiTietDonBUS();
    SanPhamBUS sanPhamBUS = new SanPhamBUS();

    public static ArrayList<RFIDTagDTO> tagList = new ArrayList<>();
    public static HashMap<String, Integer> handleMap = new HashMap<>();
    ArrayList<ChiTietDonHangDTO> chiTietList;
    ArrayList<SanPhamDTO> sanPhamList;

    public QuetDonForm1()
    {
        initComponents();
        createTableDon();
        this.setVisible(false);
        jTableDon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTableChiTietDon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void createTableModelDon(DefaultTableModel model)
    {
        donHangBUS = new DonHangBUS();
        for (DonHangDTO don : donHangBUS.getList())
        {
            Vector row = new Vector();
            row.add(don.getOrderId());
            row.add(don.getOrderDate());

            if (don.getStatus() == 2)
            {
                row.add("Đang Chờ...");
            } else if (don.getStatus() == 3)
            {
                row.add("Hoàn thành");
            }
            model.addRow(row);
        }
    }

    public void createTableDon()
    {
        rowOrder = -2;
        rowDetail = -2;
        tbModelDon.setRowCount(0);
        tbModelChiTietDon.setRowCount(0);
        jBtnQuet.setEnabled(false);
        jBtnHuy.setEnabled(false);
        jBtnSave.setEnabled(false);
        orderId = "";
        tagList.clear();
        handleMap.clear();

        sanPhamList = sanPhamBUS.getList();
        chiTietList = chiTietDonBUS.getList();
        createTableModelDon(tbModelDon);
        jTableDon.setRowSorter(null);
        jTableDon.setAutoCreateRowSorter(true);
        jTableDon.setModel(tbModelDon);
    }

    public void createTableModelChiTietDon(DefaultTableModel model)
    {
        Vector row;
        for (ChiTietDonHangDTO chiTietDon : chiTietList)
        {
            if (chiTietDon.getOrderId().equals(orderId))
            {
                row = new Vector();

                if (tbModelDon.getValueAt(rowOrder, 2).equals("Hoàn thành"))
                {
                    row.add("OK");
                } else
                {
                    row.add("");
                }
                row.add(chiTietDon.getOrderDetailId());
                row.add(chiTietDon.getProductId());

                for (SanPhamDTO sanPham : sanPhamList)
                {
                    if (sanPham.getProductId().equals(chiTietDon.getProductId()))
                    {
                        row.add(sanPham.getProductName());
                    }
                }
                row.add(chiTietDon.getOrderQuantity());
                model.addRow(row);
            }
        }
    }

    public void createTableChiTietDon()
    {
        tbModelChiTietDon.setRowCount(0);
        createTableModelChiTietDon(tbModelChiTietDon);
        jTableChiTietDon.setModel(tbModelChiTietDon);
    }

    public void scanSanPham(String product_id)
    {
        int quantity, count = 0;

        for (int i = 0; i < tbModelChiTietDon.getRowCount(); i++)
        {
            if (product_id.equals(tbModelChiTietDon.getValueAt(i, 2)))
            {
                count++;
            }
        }

        if (count == 0)
        {
            JOptionPane.showMessageDialog(this, "Sản phẩm " + product_id + " không thuộc đơn hàng!");
            jBtnSave.setEnabled(false);
            return;
        }

        for (int i = 0; i < tbModelChiTietDon.getRowCount(); i++)
        {
            if (handleMap.containsKey(tbModelChiTietDon.getValueAt(i, 2)))
            {
                quantity = handleMap.get(tbModelChiTietDon.getValueAt(i, 2));
                if (quantity == Integer.parseInt(String.valueOf(tbModelChiTietDon.getValueAt(i, 4))))
                {
                    tbModelChiTietDon.setValueAt("OK", i, 0);
                } else if (quantity > Integer.parseInt(String.valueOf(tbModelChiTietDon.getValueAt(i, 4))))
                {
                    tbModelChiTietDon.setValueAt("Dư thừa SanPham", i, 0);
                }
            }
        }
    }

    public void checkError()
    {
        if (!errorString.equals(""))
        {
            JOptionPane.showMessageDialog(this, errorString);
            jBtnSave.setEnabled(false);
            errorString = "";
        }
    }

    void clear()
    {
        rowOrder = -2;
        rowDetail = -2;

        tbModelChiTietDon.setRowCount(0);
        jBtnQuet.setEnabled(false);
        jBtnHuy.setEnabled(false);
        jBtnSave.setEnabled(false);

        orderId = "";
        tagList.clear();
        handleMap.clear();
        errorString = "";
        jTableDon.setRowSelectionAllowed(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDon = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableChiTietDon = new javax.swing.JTable();
        jBtnSave = new javax.swing.JButton();
        jBtnQuet = new javax.swing.JButton();
        jBtnHuy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(990, 650));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUÉT ĐƠN XUẤT");
        jLabel1.setToolTipText("");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 243, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("DANH SÁCH ĐƠN");
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 163, 40));

        Vector tableCol = new Vector();
        tableCol.add("ID Đơn");
        tableCol.add("Ngày tạo");
        tableCol.add("Trạng Thái");

        tbModelDon = new DefaultTableModel (tableCol,0)
        {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return false;
            }
        };
        jTableDon.setModel (tbModelDon);
        jTableDon.setShowGrid(true);
        jTableDon.setFocusable(false);
        jTableDon.setIntercellSpacing(new Dimension(0,0));
        jTableDon.setRowHeight(25);
        jTableDon.getTableHeader().setOpaque(false);
        jTableDon.setFillsViewportHeight(true);
        //        jTableDon.getTableHeader().setBackground(new Color(145,209,242));
        //        jTableDon.getTableHeader().setForeground(new Color(51, 51, 51));
        jTableDon.getTableHeader().setFont (new Font("Dialog", Font.BOLD, 13));
        //        jTableDon.setSelectionBackground(new Color(76,121,255));
        jTableDon.setAutoCreateRowSorter(true);
        jTableDon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTableDon.setGridColor(new java.awt.Color(83, 86, 88));
        jTableDon.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jTableDonMouseClicked(evt);
            }
        });
        jTableDon.getColumn (tableCol.elementAt (0)).setPreferredWidth (100);
        jTableDon.getColumn (tableCol.elementAt (1)).setPreferredWidth (150);
        jTableDon.getColumn (tableCol.elementAt (2)).setPreferredWidth (100);
        jTableDon.setAutoResizeMode (javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(jTableDon);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 360, 450));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("CHI TIẾT ĐƠN");
        jLabel3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 163, 40));

        Vector tableColDetail = new Vector();
        tableColDetail.add("Trạng Thái");
        tableColDetail.add("ID CT Đơn");
        tableColDetail.add("ID Sản Phẩm");
        tableColDetail.add("Tên Sản Phẩm");
        tableColDetail.add("Số Lượng");

        tbModelChiTietDon = new DefaultTableModel (tableColDetail,0)
        {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return false;
            }
        };
        jTableChiTietDon.setModel (tbModelChiTietDon);
        jTableChiTietDon.setShowGrid(true);
        jTableChiTietDon.setFocusable(false);
        jTableChiTietDon.setIntercellSpacing(new Dimension(0,0));
        jTableChiTietDon.setRowHeight(25);
        jTableChiTietDon.getTableHeader().setOpaque(false);
        jTableChiTietDon.setFillsViewportHeight(true);
        //        jTableDon.getTableHeader().setBackground(new Color(145,209,242));
        //        jTableDon.getTableHeader().setForeground(new Color(51, 51, 51));
        jTableChiTietDon.getTableHeader().setFont (new Font("Dialog", Font.BOLD, 13));
        //        jTableDon.setSelectionBackground(new Color(76,121,255));
        jTableChiTietDon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTableChiTietDon.setGridColor(new java.awt.Color(83, 86, 88));
        jTableChiTietDon.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jTableChiTietDonMouseClicked(evt);
            }
        });
        jTableChiTietDon.getColumn (tableColDetail.elementAt (0)).setPreferredWidth (120);
        jTableChiTietDon.getColumn (tableColDetail.elementAt (1)).setPreferredWidth (120);
        jTableChiTietDon.getColumn (tableColDetail.elementAt (2)).setPreferredWidth (120);
        jTableChiTietDon.getColumn (tableColDetail.elementAt (3)).setPreferredWidth (150);
        jTableChiTietDon.getColumn (tableColDetail.elementAt (4)).setPreferredWidth (120);
        jTableChiTietDon.setAutoResizeMode (javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(jTableChiTietDon);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 120, 510, 450));

        jBtnSave.setBackground(new java.awt.Color(255, 255, 255));
        jBtnSave.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jBtnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-save-32.png"))); // NOI18N
        jBtnSave.setBorder(null);
        jBtnSave.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnSaveActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 80, -1, -1));

        jBtnQuet.setBackground(new java.awt.Color(255, 255, 255));
        jBtnQuet.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jBtnQuet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-scan-32.png"))); // NOI18N
        jBtnQuet.setBorder(null);
        jBtnQuet.setPreferredSize(new java.awt.Dimension(38, 38));
        jBtnQuet.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnQuetActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnQuet, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, -1, -1));

        jBtnHuy.setBackground(new java.awt.Color(255, 255, 255));
        jBtnHuy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jBtnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-cancel-32.png"))); // NOI18N
        jBtnHuy.setBorder(null);
        jBtnHuy.setPreferredSize(new java.awt.Dimension(38, 38));
        jBtnHuy.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnHuyActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDonMouseClicked

        int row = jTableDon.getSelectedRow();

        if (row != -1)
        {
            rowOrder = row;
            orderId = (String) jTableDon.getValueAt(row, 0);
            createTableChiTietDon();
            if (tbModelDon.getValueAt(rowOrder, 2).equals("Hoàn thành"))
            {
                jBtnHuy.setEnabled(false);
                jBtnQuet.setEnabled(false);
                jBtnSave.setEnabled(false);
                return;
            }
            jBtnQuet.setEnabled(true);
        }

    }//GEN-LAST:event_jTableDonMouseClicked

    private void jTableChiTietDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableChiTietDonMouseClicked
        // TODO add your handling code here:
        int row = jTableChiTietDon.getSelectedRow();

        if (row != -1)
        {
            rowDetail = row;
        }

    }//GEN-LAST:event_jTableChiTietDonMouseClicked

    private void jBtnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSaveActionPerformed
        // TODO add your handling code here:
        int count = 0;
        StringBuilder error = new StringBuilder();

        for (int i = 0; i < tbModelChiTietDon.getRowCount(); i++)
        {
            if (tbModelChiTietDon.getValueAt(i, 0).equals("OK"))
            {
                count++;
            } else if (tbModelChiTietDon.getValueAt(i, 0).equals("Dư thừa SP"))
            {
                error.append("Sản phẩm " + tbModelChiTietDon.getValueAt(i, 2) + " dư thừa!!");
            } else
            {
                error.append("Sản phẩm " + tbModelChiTietDon.getValueAt(i, 2) + " chưa hoàn thành!!!");
            }
        }

        if (!error.toString().equals(""))
        {
            JOptionPane.showMessageDialog(this, error);
            return;
        }

        if (count == tbModelChiTietDon.getRowCount())
        {
            if (donHangBUS.updateOrderCompleted(orderId))
            {
                for (RFIDTagDTO a : tagList)
                {
                    a.setOrderId(orderId);
                }

                if (tagBUS.updateTagsOut(tagList))
                {
                    tbModelDon.setValueAt("Hoàn thành", rowOrder, 2);
                    JOptionPane.showMessageDialog(this, "Quét đơn OK!!!");
                    clear();
                }
            }

        }
    }//GEN-LAST:event_jBtnSaveActionPerformed

    private void jBtnQuetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnQuetActionPerformed
        // TODO add your handling code here:
        if (orderId.equals(""))
        {
            JOptionPane.showMessageDialog(this, "Lỗi Order ID!!!");
            return;
        }

        LoginForm1.flag = 2;
        LoginForm1.tagMap.clear();
        LoginForm1.tagDTOsMR = tagBUS.getList();
        handleMap.clear();

        jBtnHuy.setEnabled(true);
        jBtnQuet.setEnabled(false);
        jBtnSave.setEnabled(true);
    }//GEN-LAST:event_jBtnQuetActionPerformed

    private void jBtnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnHuyActionPerformed
        // TODO add your handling code here:

        createTableChiTietDon();
        LoginForm1.flag = 0;
        LoginForm1.tagMap.clear();
        clear();
        jTableDon.clearSelection();
    }//GEN-LAST:event_jBtnHuyActionPerformed

    public JPanel getjPanel1()
    {
        return jPanel1;
    }

    public void setjPanel1(JPanel jPanel1)
    {
        this.jPanel1 = jPanel1;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnHuy;
    private javax.swing.JButton jBtnQuet;
    private javax.swing.JButton jBtnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableChiTietDon;
    private javax.swing.JTable jTableDon;
    // End of variables declaration//GEN-END:variables
}
