package BUS;

import DTO.BaoCaoDTO;
import GUI.DashBoard1;
import java.awt.Color;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

/**
 *
 * @author Linh
 */
public class XuatExcel
{

    FileDialog fd = new FileDialog(new JFrame(), "Xuất  File excel", FileDialog.SAVE);

    private String getFile()
    {
        fd.setFile("donxuat.xls");
        fd.setVisible(true);
        String url = fd.getDirectory() + fd.getFile();
        if (url.equals("nullnull"))
        {
            return null;
        }
        return url;
    }

    //Set Color
    public HSSFColor setColor(HSSFWorkbook workbook, Color color)
    {
        HSSFPalette palette = workbook.getCustomPalette();
        HSSFColor hssfColor = null;
        try
        {
            hssfColor = palette.findSimilarColor(color.getRed(), color.getGreen(), color.getBlue());
            System.out.println("mã màu là: " + hssfColor.getIndex());
        } catch (Exception e)
        {
            System.out.println("=====>L?i màu!!!!");
        }

        return hssfColor;
    }

    // Xuất file Excel 
    @SuppressWarnings("empty-statement")
    public void xuatFileExcelDonHang(ArrayList<BaoCaoDTO> baoCaoList)
    {
        fd.setTitle("Xuất đơn hàng ra excel");
        String url = getFile();
        if (url == null)
        {
            return;
        }

        FileOutputStream outFile = null;
        try
        {
            //Create a Work Book
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Đơn Xuất");//Tên sheet

            Color color = Color.decode("#5571C7");
            HSSFColor testColor = setColor(workbook, color);
            HSSFColor colorRowEB = setColor(workbook, Color.decode("#D5DCE9"));
            HSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 24);
            font.setFontName("Courier New");
            font.setBold(true);
            font.setColor(testColor.getIndex());
            HSSFCellStyle styleTitle = workbook.createCellStyle(); // Style cho Title
            styleTitle.setBorderTop(BorderStyle.THIN);
            styleTitle.setBorderBottom(BorderStyle.THIN);
            styleTitle.setBorderRight(BorderStyle.THIN);
            styleTitle.setBorderLeft(BorderStyle.THIN);
            styleTitle.setAlignment(HorizontalAlignment.CENTER);
            styleTitle.setVerticalAlignment(VerticalAlignment.CENTER);
            styleTitle.setFont(font);
            styleTitle.setFillForegroundColor(colorRowEB.getIndex());
            styleTitle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            Row rowTitle = sheet.createRow(1);
            rowTitle.setHeight((short) 800);
            HSSFCell cellTitleCell = (HSSFCell) rowTitle.createCell(1);
            cellTitleCell.setCellValue("BẢNG KÊ PHIẾU XUẤT KHO");
            cellTitleCell.setCellStyle(styleTitle);
            CellRangeAddress cellRangeAddress = new CellRangeAddress(1, 1, 1, 9);
            rowTitle.getSheet().addMergedRegion(cellRangeAddress);
            RegionUtil.setBorderTop(BorderStyle.DOUBLE, cellRangeAddress, sheet);
            RegionUtil.setBorderBottom(BorderStyle.DOUBLE, cellRangeAddress, sheet);
            RegionUtil.setBorderLeft(BorderStyle.DOUBLE, cellRangeAddress, sheet);
            RegionUtil.setBorderRight(BorderStyle.DOUBLE, cellRangeAddress, sheet);

            // Style cho Tháng
            HSSFFont fontThang = workbook.createFont();
            fontThang.setFontHeightInPoints((short) 15);
            fontThang.setFontName("Courier New");
            fontThang.setColor(testColor.getIndex());
            fontThang.setBold(true);
            HSSFCellStyle styleThang = workbook.createCellStyle();
            styleThang.setBorderTop(BorderStyle.THIN);
            styleThang.setBorderBottom(BorderStyle.THIN);
            styleThang.setBorderRight(BorderStyle.THIN);
            styleThang.setBorderLeft(BorderStyle.THIN);
            styleThang.setAlignment(HorizontalAlignment.CENTER);
            styleThang.setVerticalAlignment(VerticalAlignment.CENTER);
            styleThang.setFont(fontThang);

            Row rowThang = sheet.createRow(2);
            rowThang.setHeight((short) 500);
            HSSFCell cellThang = (HSSFCell) rowThang.createCell(3);
            cellThang.setCellValue("Tháng 05/2022");
            cellThang.setCellStyle(styleThang);
            CellRangeAddress cellRangeAddressThang = new CellRangeAddress(2, 2, 3, 7);
            rowThang.getSheet().addMergedRegion(cellRangeAddressThang);
            RegionUtil.setBorderTop(BorderStyle.DOUBLE, cellRangeAddressThang, sheet);
            RegionUtil.setBorderBottom(BorderStyle.DOUBLE, cellRangeAddressThang, sheet);
            RegionUtil.setBorderLeft(BorderStyle.DOUBLE, cellRangeAddressThang, sheet);
            RegionUtil.setBorderRight(BorderStyle.DOUBLE, cellRangeAddressThang, sheet);

            ////Style cho ng nhap
            Color colorNhap = Color.decode("#6076BE");
            HSSFColor testColorNhap = setColor(workbook, colorNhap);
            HSSFFont fontNhap = workbook.createFont();
            fontNhap.setFontHeightInPoints((short) 12);
            fontNhap.setFontName("Courier New");
            fontNhap.setBold(true);
            fontNhap.setColor(testColorNhap.getIndex());
            HSSFCellStyle styleName = workbook.createCellStyle();
            styleName.setAlignment(HorizontalAlignment.LEFT);
            styleName.setVerticalAlignment(VerticalAlignment.CENTER);
            styleName.setFont(fontNhap);

            Row rowNhap = sheet.createRow(4);
            rowNhap.setHeight((short) 600);
            HSSFCell cellNhap = (HSSFCell) rowNhap.createCell(1);
            cellNhap.setCellValue("Người Lập Đơn: " + DashBoard1.jLabel6.getText().toString());
            cellNhap.setCellStyle(styleName);
            CellRangeAddress cellRangeAddress1 = new CellRangeAddress(4, 4, 1, 3);
            rowNhap.getSheet().addMergedRegion(cellRangeAddress1);
            RegionUtil.setBorderTop(BorderStyle.DOUBLE, cellRangeAddress1, sheet);
            RegionUtil.setBorderBottom(BorderStyle.DOUBLE, cellRangeAddress1, sheet);
            RegionUtil.setBorderLeft(BorderStyle.DOUBLE, cellRangeAddress1, sheet);
            RegionUtil.setBorderRight(BorderStyle.DOUBLE, cellRangeAddress1, sheet);

            /// Style for Data
            Color colorRow = Color.decode("#4A5771");
            Color colorRowBack = Color.decode("#D5DCE9");
            Color color2 = Color.decode("#DDDDDD");
            HSSFColor testColor2 = setColor(workbook, color2);
            HSSFColor colorRowE = setColor(workbook, colorRow);
            HSSFFont fontRow = workbook.createFont();
            fontRow.setBold(true);
            fontRow.setColor(colorRowE.getIndex());
            HSSFCellStyle style = workbook.createCellStyle();
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            style.setFont(fontRow);
            style.setFillForegroundColor(testColor2.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            HSSFCellStyle styleDatarow = workbook.createCellStyle();
            styleDatarow.setBorderTop(BorderStyle.THIN);
            styleDatarow.setBorderBottom(BorderStyle.THIN);
            styleDatarow.setBorderRight(BorderStyle.THIN);
            styleDatarow.setBorderLeft(BorderStyle.THIN);
            styleDatarow.setAlignment(HorizontalAlignment.CENTER);
            styleDatarow.setVerticalAlignment(VerticalAlignment.CENTER);

            
            HSSFCellStyle style2 = workbook.createCellStyle(); 
            style2.setBorderTop(BorderStyle.THIN);
            style2.setBorderBottom(BorderStyle.THIN);
            style2.setBorderRight(BorderStyle.THIN);
            style2.setBorderLeft(BorderStyle.THIN);
            style2.setAlignment(HorizontalAlignment.CENTER);
            style2.setVerticalAlignment(VerticalAlignment.CENTER);
            style2.setFillForegroundColor(testColor2.getIndex());
            style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            Row row = sheet.createRow(6);
            row.setHeight((short) 400);
            HSSFCell cell2 = (HSSFCell) row.createCell(1, CellType.STRING);
            cell2.setCellValue("Mã Đơn Xuất");
            cell2.setCellStyle(style);
            HSSFCell cell3 = (HSSFCell) row.createCell(2, CellType.STRING);
            cell3.setCellValue("Ngày Tạo");
            cell3.setCellStyle(style);
            HSSFCell cell4 = (HSSFCell) row.createCell(3, CellType.STRING);
            cell4.setCellValue("Trạng Thái");
            cell4.setCellStyle(style);
            HSSFCell cell5 = (HSSFCell) row.createCell(4, CellType.STRING);
            cell5.setCellValue("Mã Sản Phẩm");
            cell5.setCellStyle(style);
            HSSFCell cell6 = (HSSFCell) row.createCell(5, CellType.STRING);
            cell6.setCellValue("Tên Sản Phẩm");
            cell6.setCellStyle(style);
            HSSFCell cell7 = (HSSFCell) row.createCell(6, CellType.STRING);
            cell7.setCellValue("Đơn Vị Tính");
            cell7.setCellStyle(style);
            HSSFCell cell8 = (HSSFCell) row.createCell(7, CellType.STRING);
            cell8.setCellValue("Tag Sản Phẩm");
            cell8.setCellStyle(style);
            HSSFCell cell9 = (HSSFCell) row.createCell(8, CellType.STRING);
            cell9.setCellValue("Cổng Nhập");
            cell9.setCellStyle(style);
            HSSFCell cell10 = (HSSFCell) row.createCell(9, CellType.STRING);
            cell10.setCellValue("Ngày Nhập");
            cell10.setCellStyle(style);
            HSSFCell cell11 = (HSSFCell) row.createCell(10, CellType.STRING);
            cell11.setCellValue("Cổng Xuất");
            cell11.setCellStyle(style);
            HSSFCell cell12 = (HSSFCell) row.createCell(11, CellType.STRING);
            cell12.setCellValue("Ngày Xuất");
            cell12.setCellStyle(style);

            for (int i = 1; i <= 12; ++i)
            {

                if (i == 5 || i == 2 || i == 9 || i==11)
                {
                    sheet.setColumnWidth(i, 25 * 256);
                } else if (i == 7)
                {
                    sheet.setColumnWidth(i, 25 * 350);
                } else
                {
                    sheet.setColumnWidth(i, 25 * 150);
                }
            }

            int rowPrint = 7, beginOrder = 7, endOrder = 7, beginProduct = 7, endProduct = 7, count = 0;
            String orderId = "", productId = "";
            for (BaoCaoDTO k : baoCaoList)//row
            {
                Row rowData = sheet.createRow(rowPrint);
                rowData.setHeight((short) 400);

                if (!orderId.equals(k.getOrder_id()))
                {
                    if (!productId.equals(k.getProduct_id()))
                    {
                        productId = k.getProduct_id();
                        if (endProduct > beginProduct)
                        {
                            for (int u = 4; u <= 6; u++)
                            {
                                CellRangeAddress cellRangeAddress3 = new CellRangeAddress(beginProduct, endProduct, u, u);
                                rowData.getSheet().addMergedRegion(cellRangeAddress3);
                            }
                        }
                        beginProduct = rowPrint;
                    } else
                    {
                        if (endProduct > beginProduct)
                        {
                            for (int u = 4; u <= 6; u++)
                            {
                                CellRangeAddress cellRangeAddress3 = new CellRangeAddress(beginProduct, endProduct, u, u);
                                rowData.getSheet().addMergedRegion(cellRangeAddress3);
                            }
                        }
                        beginProduct = rowPrint;
                    }
                    orderId = k.getOrder_id();
                    if (endOrder > beginOrder)
                    {
                        for (int u = 1; u <= 3; u++)
                        {
                            CellRangeAddress cellRangeAddress2 = new CellRangeAddress(beginOrder, endOrder, u, u);
                            rowData.getSheet().addMergedRegion(cellRangeAddress2);
                        }
                    }
                    beginOrder = rowPrint;
                    count++;
                } else
                {
                    if (!productId.equals(k.getProduct_id()))
                    {
                        productId = k.getProduct_id();
                        if (endProduct > beginProduct)
                        {
                            for (int u = 4; u <= 6; u++)
                            {
                                CellRangeAddress cellRangeAddress3 = new CellRangeAddress(beginProduct, endProduct, u, u);
                                rowData.getSheet().addMergedRegion(cellRangeAddress3);

                            }
                        }
                        beginProduct = rowPrint;
                    } else
                    {
                        endProduct = rowPrint;
                    }
                    endOrder = rowPrint;
                }

                for (int j = 1; j <= 11; ++j)//column
                {
                    HSSFCell cellData = (HSSFCell) rowData.createCell(j);
                    if (count % 2 == 0)
                    {
                        cellData.setCellStyle(style2);
                    } else
                    {
                        cellData.setCellStyle(styleDatarow);
                    }
                    switch (j)
                    {
                        case 1 ->
                        {
                            cellData.setCellValue(k.getOrder_id());
                        }
                        case 2 ->
                        {
                            cellData.setCellValue(k.getOrder_date());
                        }
                        case 3 ->
                        {
                            if (k.getStatus() == 2)
                            {
                                cellData.setCellValue("Đang Chờ");
                            } else
                            {
                                cellData.setCellValue("Hoàn thành");
                            }
                        }
                        case 4 ->
                        {
                            cellData.setCellValue(k.getProduct_id());
                        }
                        case 5 ->
                        {
                            cellData.setCellValue(k.getProduct_name());
                        }
                        case 6 ->
                        {
                            cellData.setCellValue(k.getOrder_quantity());
                        }
                        case 7 ->
                        {
                            cellData.setCellValue(k.getTag_id());
                        }
                        case 8 ->
                        {
                            cellData.setCellValue(k.getTag_gate_in());
                        }
                        case 9 ->
                        {
                            cellData.setCellValue(k.getTag_date_in());
                        }
                         case 10 ->  {
                            cellData.setCellValue(k.getTag_gate_out());
                        }
                        case 11 ->  {
                            cellData.setCellValue(k.getTag_date_out());
                        }
                    }
                }
                if (rowPrint - 6 == baoCaoList.size())
                {
                    if (endOrder > beginOrder)
                    {
                        for (int u = 1; u <= 3; u++)
                        {
                            CellRangeAddress cellRangeAddress4 = new CellRangeAddress(beginOrder, endOrder, u, u);
                            rowData.getSheet().addMergedRegion(cellRangeAddress4);
                        }
                    }
                    if (endProduct > beginProduct)
                    {
                        System.out.println("ep la" + endProduct + "---" + beginProduct);
                        for (int u = 4; u <= 6; u++)
                        {
                            CellRangeAddress cellRangeAddress5 = new CellRangeAddress(beginProduct, endProduct, u, u);
                            rowData.getSheet().addMergedRegion(cellRangeAddress5);
                        }
                    }
                }
                rowPrint++;
            }
            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            try
            {
                if (outFile != null)
                {
                    outFile.close();
//                    System.exit(0);
                }
            } catch (IOException ex)
            {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
