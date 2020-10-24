package Demo.ytzz;

import Demo.CurrencyTools;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

public class YTZZExcel3 {
    static String ziboPath = "";
    static int cityPoint = 0;
    static Map<String, City> cityMap = new HashMap<String, City>();
    static Map<String, Set<String>> reMap = new HashMap<String, Set<String>>();

    public static void main(String[] args) throws Exception {
        init();
        Iterator iterator = cityMap.keySet().iterator();
        while (iterator.hasNext()) {
            City city = cityMap.get((String) iterator.next());
            statistic(reMap, getSheet(city.excelPath), city.nameCellNum, city.cityName);
        }
        writeReToExcel(reMap);
    }

    private static final void writeReToExcel(Map<String, Set<String>> map) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet0 = workbook.createSheet("六地市汇总");
        int rowNum = 1;
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String license = (String) iterator.next();
            Set<String> set = map.get(license);
            XSSFRow rowI = sheet0.createRow(rowNum);
            rowNum++;
            XSSFCell cell1 = rowI.createCell(1);
            cell1.setCellValue(license);
            XSSFCell cell2 = rowI.createCell(2);
            XSSFCell cell3 = rowI.createCell(3);
            XSSFCell cell4 = rowI.createCell(4);
            XSSFCell cell5 = rowI.createCell(5);
            if (set.contains("济南")) cell2.setCellValue("有");
            if (set.contains("淄博")) cell3.setCellValue("有");
            if (set.contains("滨州")) cell4.setCellValue("有");
            if (set.contains("临沂")) cell5.setCellValue("有");
        }
        FileOutputStream out = new FileOutputStream("/Users/yourouniu/Desktop/111.xlsx");
        workbook.write(out);
        out.flush();
        out.close();
    }

    private static final void print(Map<String, Set<String>> map) {
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String license = (String) iterator.next();
            Set<String> set = map.get(license);
            System.out.print(license + ":");
            for (String city : set) System.out.print(city + ",");
            System.out.println(" ");
        }
    }

    static final private void init() {
        cityMap.put("淄博", new City("淄博", "/Users/yourouniu/Desktop/电子证照/参考标准/其他地市/淄博.xlsx", 2));
        cityMap.put("临沂", new City("临沂", "/Users/yourouniu/Desktop/电子证照/参考标准/其他地市/临沂.xlsx", 1));
        cityMap.put("济南", new City("济南", "/Users/yourouniu/Desktop/电子证照/参考标准/其他地市/济南.xlsx", 0));
        cityMap.put("滨州", new City("滨州", "/Users/yourouniu/Desktop/电子证照/参考标准/其他地市/滨州/滨州市符合省里要求的证照目录总.xlsx", 2));
//        cityMap.put("德州", new City("德州", "/Users/yourouniu/Desktop/电子证照/参考标准/其他地市/亿云/电子证照材料-德州.xlsx", 2));
//        cityMap.put("潍坊", new City("潍坊", "/Users/yourouniu/Desktop/电子证照/参考标准/其他地市/亿云/电子证照材料-潍坊.xlsx", 1));
    }

    static final private XSSFSheet getSheet(String path) throws Exception {
        File in = new File(path);
        XSSFWorkbook workbook = new XSSFWorkbook(in);
        XSSFSheet sheet = workbook.getSheetAt(0);
        return sheet;
    }

    private static final void statistic(Map<String, Set<String>> map, XSSFSheet sheet, int cellNum, String city) {
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            XSSFRow rowI = sheet.getRow(i);
            XSSFCell cell = rowI.getCell(cellNum);
            String licenseName = CurrencyTools.judgeCell(cell);
            if (map.containsKey(licenseName)) {
                Set<String> cityList = map.get(licenseName);
                cityList.add(city);
                continue;
            }
            Set<String> cityList = new HashSet<String>();
            cityList.add(city);
            map.put(licenseName, cityList);
        }
        cityPoint++;
    }

    static class City {
        String cityName;
        String excelPath;
        int nameCellNum;

        City(String cityName, String excelPath, int nameCellNum) {
            this.cityName = cityName;
            this.excelPath = excelPath;
            this.nameCellNum = nameCellNum;
        }
    }
}
