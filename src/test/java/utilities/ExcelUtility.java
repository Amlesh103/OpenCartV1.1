package utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {

        public  FileInputStream fi;
        public  FileOutputStream fo;
        public  XSSFWorkbook wb;
        public  XSSFSheet ws;
        public  XSSFRow row;
        public  XSSFCell cell;
        public  CellStyle style;
        String path;

        public ExcelUtility(String path){
            this.path=path;
        }

        public  int getRowCount(String sheetname) throws IOException {
            fi = new FileInputStream(path);
            wb = new XSSFWorkbook(fi);
            ws = wb.getSheet(sheetname);
            int rowCount = ws.getLastRowNum();
            wb.close();
            fi.close();
            return rowCount;
        }

        public  int getCellCount(String sheetname, int rownum) throws IOException{
            fi = new FileInputStream(path);
            wb = new XSSFWorkbook(fi);
            ws = wb.getSheet(sheetname);
            int colCount = ws.getRow(rownum).getLastCellNum();
            wb.close();
            fi.close();
            return colCount;
        }

        public  String getCellData(String sheetname, int rownum, int colnum) throws IOException{
            fi = new FileInputStream(path);
            wb = new XSSFWorkbook(fi);
            ws = wb.getSheet(sheetname);
            row = ws.getRow(rownum);
            cell = row.getCell(colnum);

            String data;
            try{
                //data = cell.toString();   /* Instead of dataformatter we can still use toString() method also*/
                DataFormatter formatter = new DataFormatter();
                data = formatter.formatCellValue(cell); //Returns the formatted value of a cell as String regardless of cell type
            } catch (Exception e) {
                data = "";
            }
            wb.close();
            fi.close();
            return data;
        }

        public  void setCellData(String sheetname, int rownum, int cellnum, String data) throws IOException{

            fi = new FileInputStream(path);
            wb = new XSSFWorkbook(fi);
            ws = wb.getSheet(sheetname);
            row = ws.getRow(rownum);

            cell = row.createCell(cellnum);
            cell.setCellValue(data);
            fo = new FileOutputStream(path);
            wb.write(fo);
            wb.close();
            fi.close();
            fo.close();

        }

        public  void fillGreenColor(String sheetname, int rownum, int cellnum) throws IOException {
            fi = new FileInputStream(path);
            wb = new XSSFWorkbook(fi);
            ws = wb.getSheet(sheetname);
            row = ws.getRow(rownum);
            cell = row.getCell(cellnum);

            style = wb.createCellStyle();
            style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            cell.setCellStyle(style);
            fo = new FileOutputStream(path);
            wb.write(fo);
            wb.close();
            fi.close();
            fo.close();
        }

        public  void fillRedColor(String sheetname, int rownum, int cellnum) throws IOException{
            fi = new FileInputStream(path);
            wb = new XSSFWorkbook(fi);
            ws = wb.getSheet(sheetname);
            row = ws.getRow(rownum);
            cell = row.getCell(cellnum);

            style = wb.createCellStyle();
            style.setFillForegroundColor(IndexedColors.RED.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            cell.setCellStyle(style);
            fo = new FileOutputStream(path);
            wb.write(fo);
            wb.close();
            fi.close();
            fo.close();
        }




}
