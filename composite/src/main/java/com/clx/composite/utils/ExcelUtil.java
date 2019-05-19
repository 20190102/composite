package com.clx.composite.utils;

import com.clx.composite.exception.ExcelException;
import com.clx.composite.model.DO.ExcelDO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理excel的工具类
 */
public class ExcelUtil {

    /**
     * 解析excel文件，保存在redis中
     *
     * @param file
     */
    public static List<ExcelDO> excelResolver(MultipartFile file) {
        List<ExcelDO> list = new ArrayList<>();

        if (!file.getOriginalFilename().endsWith(".xlsx")) {
            throw new ExcelException("文件格式错误，请使用xlsx文件");
        }
        //获取excel文件流
        try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {

            //获取工作表
            Sheet sheet = workbook.getSheetAt(0);
            //获取有效行数
            int rowNum = sheet.getLastRowNum();

            //获取标题行的有效列数
            int cellNum = sheet.getRow(0).getLastCellNum();
            for (int i = 1; i < rowNum; i++) {
                ExcelDO excel = new ExcelDO();
                //获取行
                Row row = sheet.getRow(i);

                for (int j = 0; j < cellNum; j++) {
                    Cell cell = row.getCell(j);
                    try {
                        switch (j) {
                            case 0:
                                excel.setEmail(cell.getStringCellValue());
                                break;
                            case 1:
                                excel.setPassword(getValue(cell));
                                break;
                            case 2:
                                excel.setUsername(getValue(cell));
                                break;
                            case 3:
                                excel.setBirthday(ConvertUtil.dataFormat(cell.getDateCellValue()));
                                break;
                            case 4:
                                excel.setSex(cell.getStringCellValue());
                                break;
                            case 5:
                                excel.setPermission(String.valueOf((int) cell.getNumericCellValue()));
                                break;
                        }
                    } catch (Exception e) {
                        throw new ExcelException("第" + (i + 1) + "行，第" + (j + 1) + "列格式错误");
                    }
                }
                list.add(excel);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExcelException("文件异常");
        }

        return list;

    }

    public static String getValue(Cell cell) {
        if ("NUMERIC".equals(cell.getCellTypeEnum().toString())) {
            return String.valueOf((int) cell.getNumericCellValue());
        }
        return cell.getStringCellValue();
    }
}
