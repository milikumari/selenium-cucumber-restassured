package com.acceptance.tests.utils;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bouncycastle.asn1.cms.Time;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class ExcelSheetHelper {


    public static void main(String[] args) throws Exception {

        File file = new File(System.getProperty("user.dir") + "/excelFiles/test.xls");
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        String cellValue = sheet.getRow(0).getCell(0).toString();
        int rowCount = sheet.getPhysicalNumberOfRows();

        for (int i = 0; i < rowCount; i++) {
            XSSFRow row = sheet.getRow(i);

            int cellCount = row.getPhysicalNumberOfCells();
            for (int j = 0; j < cellCount; j++) {
                XSSFCell cell = row.getCell(j);
                String stringValueOfCell = getCellValue(cell);
                System.err.println("my cell vale for is -> " + stringValueOfCell);
            }
            System.err.println("my row number is -> " + i);
        }



        workbook.close();
        fis.close();
    }


    private static String getCellValue(XSSFCell cell) {

        switch (cell.getCellType()) {

            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case STRING:
            default:
                return cell.getStringCellValue();
        }
    }

    private static void createNewExcelSheet() throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet();
        sheet.createRow(0);
        sheet.getRow(0).createCell(0).setCellValue("hello");
        sheet.getRow(0).createCell(1).setCellValue("world");

        sheet.createRow(1);
        sheet.getRow(1).createCell(0).setCellValue("HVR");
        sheet.getRow(1).createCell(1).setCellValue("tutorial");

        File file = new File(System.getProperty("user.dir") + "/excelFiles/test.xls");
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(file);
        workbook.close();
    }
}
