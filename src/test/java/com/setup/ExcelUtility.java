package com.setup;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.*;

public class ExcelUtility {

    
    public static List<String[]> readEMIData(String filePath, String sheetName) throws IOException {
        List<String[]> dataList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            DataFormatter formatter = new DataFormatter();

            if (sheet == null) {
                throw new RuntimeException("Sheet " + sheetName + " not found in Excel file.");
            }

            int rowCount = sheet.getLastRowNum();

            
            for (int i = 1; i <= rowCount; i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String loanAmount = formatter.formatCellValue(row.getCell(0));
                String interestRate = formatter.formatCellValue(row.getCell(1));

                dataList.add(new String[]{loanAmount, interestRate});
            }
        }

        return dataList;
    }

   
    public static void writeEMIResult(String filePath, String sheetName, int rowNumber, String emiResult) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }

        Row row = sheet.getRow(rowNumber);
        if (row == null) row = sheet.createRow(rowNumber);

        Cell cell = row.getCell(2);
        if (cell == null) cell = row.createCell(2);

        cell.setCellValue(emiResult);

        fis.close();

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
        }

        workbook.close();
    }
}

