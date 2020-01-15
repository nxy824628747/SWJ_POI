package Demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.charts.XSSFDateAxis;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class CurrencyTools {

    //单元格内容数据格式校验并取值
    public static String judgeCell(Cell cell) {
        String cellValue = new String();
        try {
            String className = cell.getClass().getName();
            double DcellValue;
            try {
                cellValue = cell.getStringCellValue();
            } catch (Exception e1) {
                //String cellClass=cell.getCellType();
                try {
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                                double value = cell.getNumericCellValue();
                                Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
                                cellValue = sdf1.format(date);
                            } else {
                                DcellValue = cell.getNumericCellValue();
                                //cellValue.valueOf(DcellValue);
                                NumberFormat numberFormat = NumberFormat.getNumberInstance();
                                numberFormat.setGroupingUsed(false); //设置了以后不会有千分位
                                cellValue = numberFormat.format(DcellValue);
                            }
                    }
                } catch (Exception e2) {
                    cellValue = " ";
                    System.out.println("此单元格无内容");
                }

            }
        } catch (Exception e) {
        }
        return cellValue;
    }
	/*public static String judgeCell(Cell cell){
		String cellValue=cell.getStringCellValue().toString();
		return cellValue;
	}*/

    //按sheet合并(不带表头)
    public static void CombineTwoSheet(XSSFSheet sheet0, XSSFSheet sheet1, XSSFCellStyle newStyle) {
        try {
            int Sheet0rowCount = sheet0.getLastRowNum();
            int Sheet1rowCount = sheet1.getLastRowNum();
            int Sheet1cellCount = sheet1.getRow(0).getLastCellNum();
            //将sheet1插入到sheet0中
            for (int i = 1; i <= Sheet1rowCount; i++) {
                System.out.println(i + "   of combine");
                XSSFRow RrowToSheet0 = sheet0.createRow((short) Sheet0rowCount + i);
                for (int j = 0; j < Sheet1cellCount; j++) {
                    XSSFCell newcell = RrowToSheet0.createCell((short) j);
                    newcell.setCellStyle(newStyle);
                    newcell.setCellValue(judgeCell(sheet1.getRow(i).getCell((short) j)));
                }
            }
        } catch (Exception e) {
        }
    }

    public static void CombineTwoSheetHssf(HSSFSheet sheet0, HSSFSheet sheet1) {
        int Sheet0rowCount = sheet0.getLastRowNum();
        int Sheet1rowCount = sheet1.getLastRowNum();
        int Sheet1cellCount = sheet1.getRow(0).getLastCellNum();
        //将sheet1插入到sheet0中
        for (int i = 1; i <= Sheet1rowCount; i++) {
            HSSFRow RrowToSheet0 = sheet0.createRow((short) Sheet0rowCount + i);
            for (int j = 0; j < Sheet1cellCount; j++) {
                HSSFCell newcell = RrowToSheet0.createCell((short) j);
                // newcell.setCellStyle(newStyle);
                newcell.setCellValue(judgeCell(sheet1.getRow(i).getCell((short) j)));
                sheet1.getRow(i).getCell((short) j).getClass().getName();
            }
        }
    }

    //遍历指定文件夹中的excel并合并为一个
    public static void traverseExcel(String path) throws IOException {
        File file = new File(path);
        String names[] = new String[file.list().length];
        names = file.list();
        if (file.list().length > 1) {
            //int quantity=names.length;
            //excel轮训参数准备
            FileOutputStream Inspur = new FileOutputStream("D:/excel处理程序/结果文件.xlsx");
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFCellStyle style1 = wb.createCellStyle();
            style1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            XSSFSheet sheet0 = wb.createSheet();
            XSSFWorkbook excel1 = new XSSFWorkbook(new FileInputStream(path + "/" + names[1]));
            //根据第一个表格列数设置列宽
            int cellIterator = excel1.getSheetAt(0).getRow(0).getLastCellNum();
            for (int j = 0; j < cellIterator; j++) {
                sheet0.setColumnWidth(j, 7000);
            }
            //根据第一个表格第一行为总表添加表头
            XSSFRow row00 = excel1.getSheetAt(0).getRow(0);
            XSSFRow row01 = sheet0.createRow(0);
            for (int c = 0; c < cellIterator; c++) {
                XSSFCell cell010 = row01.createCell(c);
                String value = CurrencyTools.judgeCell(row00.getCell(c));
                cell010.setCellValue(value);
                cell010.setCellStyle(style1);
            }
            //excel轮训开始
            int quantity = names.length;
            for (int i = 0; i < quantity; i++) {
                System.out.println("正在处理文件   ： " + path + "/" + names[i]);
                XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(path + "/" + names[i]));
                //XSSFSheet sheetNew=excel.getSheetAt(0);
                CombineTwoSheet(sheet0, excel.getSheetAt(0), style1);
            }
            wb.write(Inspur);
            Inspur.close();
            System.out.println("EXCEL文件已合并完成，结果文件路径       ：  D:/excel处理程序/结果文件。xlsx");
        } else {
            System.out.println("处理单个文件   ：   " + path + "/" + names[0]);
        }
    }

    public static String getCellValue(XSSFCell cell) {
        String value;
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                //如果为时间格式的内容
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    //注：format格式 yyyy-MM-dd hh:mm:ss 中小时为12小时制，若要24小时制，则把小h变为H即可，yyyy-MM-dd HH:mm:ss
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    value = sdf.format(HSSFDateUtil.getJavaDate(cell.
                            getNumericCellValue())).toString();
                    break;
                } else {
                    value = new DecimalFormat("0").format(cell.getNumericCellValue());
                }
                break;
            case HSSFCell.CELL_TYPE_STRING: // 字符串
                value = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                value = cell.getBooleanCellValue() + "";
                break;
            case HSSFCell.CELL_TYPE_FORMULA: // 公式
                value = cell.getCellFormula() + "";
                break;
            case HSSFCell.CELL_TYPE_BLANK: // 空值
                value = "";
                break;
            case HSSFCell.CELL_TYPE_ERROR: // 故障
                value = "非法字符";
                break;
            default:
                value = "未知类型";
                break;
        }
        return value;
    }
    //	注入ip地址，账户密码获取oracle连接
    public static JdbcTemplate getOracleJdbcTemplate (String dbUrl, String account, String password) throws IOException{
        JdbcTemplate jtOracle=null;
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        StringBuffer connUrl=new StringBuffer();
        connUrl.append("jdbc:oracle:thin:@"+dbUrl);
        ds.setUrl(connUrl.toString());
        ds.setUsername(account);
        ds.setPassword(password);
        jtOracle= new JdbcTemplate(ds);
        System.out.println("Successed to get Connection!");
        return jtOracle;
    }
}
