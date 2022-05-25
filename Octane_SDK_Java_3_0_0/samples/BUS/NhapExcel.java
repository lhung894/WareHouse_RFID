package BUS;

import DTO.RFIDTagDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Linh
 */
public class NhapExcel
{

    static ArrayList<RFIDTagDTO> list = new ArrayList<>();
    static RFIDTagDTO tagDTO;
    static String value = "";

    public static ArrayList<RFIDTagDTO> getList() throws FileNotFoundException, IOException
    {
        String projDirString = System.getProperty("user.dir");
        String pathString = projDirString + "\\data\\donnhap.xls";
        File file = new File(pathString);
        FileInputStream fis = new FileInputStream(file);
        HSSFWorkbook wb = new HSSFWorkbook(fis);
        HSSFSheet sheet = wb.getSheetAt(0);

        for (int i = 5; i <= 11; ++i)
        {
            Row row = sheet.getRow(i);
            tagDTO = new RFIDTagDTO();
            for (int j = 3; j <= 7; ++j)
            {
                Cell cell = row.getCell(j);
                switch (j)
                {
                    case 3:

                        tagDTO.setTagId(cell.toString());
                        break;

                    case 4:
                        tagDTO.setProductId(cell.toString());
                        break;

                    case 5:
                        tagDTO.setTagGateIn("1");
                        break;

                    case 6:
                        value = cell.toString();
                        break;

                    case 7:
                        value += " " + cell.toString() + ".000";
                        break;
                }
            }
            tagDTO.setTagDateIn(value);
            tagDTO.setTagGateOut("");
            tagDTO.setTagDateOut(null);
            tagDTO.setOrderId("");
            list.add(tagDTO);
        }
        return list;
    }

    public static void main(String args[]) throws IOException
    {
        String projDirString = System.getProperty("user.dir");
        String pathString = projDirString + "\\data\\donnhap.xls";
        File file = new File(pathString);
        FileInputStream fis = new FileInputStream(file);
        HSSFWorkbook wb = new HSSFWorkbook(fis);
        HSSFSheet sheet = wb.getSheetAt(0);

        for (int i = 5; i <= 11; ++i)
        {
            Row row = sheet.getRow(i);
            tagDTO = new RFIDTagDTO();
            for (int j = 3; j <= 7; ++j)
            {
                Cell cell = row.getCell(j);
                switch (j)
                {
                    case 3:

                        tagDTO.setTagId(cell.toString());
                        break;

                    case 4:
                        tagDTO.setProductId(cell.toString());
                        break;

                    case 5:
                        tagDTO.setTagGateIn("1");
                        break;

                    case 6:
                        value = cell.toString();
                        break;

                    case 7:
                        value += " " + cell.toString() + ".000";
                        break;
                }
            }
            tagDTO.setTagDateIn(value);
            tagDTO.setTagGateOut("");
            tagDTO.setTagDateOut(null);
            tagDTO.setOrderId("");
            list.add(tagDTO);
        }

        for (RFIDTagDTO tagDTO : list)
        {
            System.out.println(tagDTO);
        }
    }
}
