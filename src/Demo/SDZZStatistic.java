package Demo;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class SDZZStatistic {
    static String excelPath = "/Users/yourouniu/Desktop/各地市接入情况.xlsx";
    static String qdPath = "/Users/yourouniu/Desktop/青岛部门证照列表.xlsx";
    static String sdPath = "/Users/yourouniu/Desktop/省直部门负责的证照数据归集清单.xlsx";

    public static void main(String[] args) throws Exception {
        XSSFWorkbook sheng = new XSSFWorkbook(new FileInputStream(sdPath));
        Map<String, String> licenseMap = new HashMap<String, String>();
        XSSFSheet shengSheet = sheng.getSheetAt(0);
        for (int i = 3; i < shengSheet.getLastRowNum(); i++) {
            XSSFRow rowI = shengSheet.getRow(i);
            String name = CurrencyTools.judgeCell(rowI.getCell(2));
            String code = CurrencyTools.judgeCell(rowI.getCell(3));
            if (code == null || code.length() == 0 || licenseMap.containsKey(code)) {
                continue;
            }
            licenseMap.put(code, name);
        }
        XSSFWorkbook other = new XSSFWorkbook(new FileInputStream(excelPath));
        XSSFSheet otherSheet = other.getSheetAt(1);
        for (int i = 1; i < otherSheet.getLastRowNum(); i++) {
            XSSFRow rowI = otherSheet.getRow(i);
            String code = CurrencyTools.judgeCell(rowI.getCell(3));
            System.out.println(code);
            if (licenseMap.containsKey(code)) {
                rowI.createCell(5).setCellValue("是");
            }
        }

        FileOutputStream outputStream = new FileOutputStream(excelPath);
        other.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    public static void main2(String[] args) throws Exception {
        XSSFWorkbook sd = new XSSFWorkbook(new FileInputStream(excelPath));
        XSSFSheet sheet0 = sd.getSheetAt(0);
        int size = sheet0.getLastRowNum();
        Map<String, LicenseInfo> map = new HashMap<String, LicenseInfo>();
        for (int i = 1; i < size; i++) {
            XSSFRow rowI = sheet0.getRow(i);
            String licenseName = CurrencyTools.judgeCell(rowI.getCell(2));
            String city = CurrencyTools.judgeCell(rowI.getCell(4));
            if (map.containsKey(licenseName)) {
                if (map.get(licenseName).city.contains(city)) {
                    continue;
                }
                LicenseInfo info = map.get(licenseName);
                info.num += 1;
                info.city += city;
                continue;
            }
            String orgName = CurrencyTools.judgeCell(rowI.getCell(1));
            String licenseCode = CurrencyTools.judgeCell(rowI.getCell(3));
            map.put(licenseName, new LicenseInfo(licenseName, orgName, licenseCode, city));
        }
        XSSFSheet sheet1 = sd.getSheetAt(1);
        int rowPoint = 1;
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            LicenseInfo info = map.get((String) iterator.next());
            System.out.println(info.name);
            XSSFRow row = sheet1.createRow(rowPoint);
            row.createCell(0).setCellValue(rowPoint);
            row.createCell(1).setCellValue(info.orgName);
            row.createCell(2).setCellValue(info.name);
            row.createCell(3).setCellValue(info.licenseCode);
            row.createCell(4).setCellValue(info.num);
            rowPoint++;
        }
        FileOutputStream outputStream = new FileOutputStream(excelPath);
        sd.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    //根据青岛的部门添加各地市证照的部门
    public static void main1(String[] args) throws Exception {
        XSSFWorkbook sd = new XSSFWorkbook(new FileInputStream(excelPath));
        XSSFWorkbook qd = new XSSFWorkbook(new FileInputStream(qdPath));
        List<License> list = buildList(qd.getSheetAt(0));
        XSSFSheet sdSheet = sd.getSheetAt(0);
        int size = sdSheet.getLastRowNum();
        for (int i = 1; i < size; i++) {
            XSSFRow rowI = sdSheet.getRow(i);
            String name = CurrencyTools.judgeCell(rowI.getCell(1));
            if (name.length() > 2) {
                name = name.substring(0, 2);
            }
            System.out.println(name);
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).name.contains(name)) {
                    rowI.createCell(0).setCellValue(list.get(j).orgName);
                    break;
                }
            }
        }
        FileOutputStream outputStream = new FileOutputStream(excelPath);
        sd.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    static class LicenseInfo extends License {
        int num;
        String licenseCode;
        String city;

        LicenseInfo(String name, String orgName, String licenseCode, String city) {
            super(name, orgName);
            this.licenseCode = licenseCode;
            this.city = city;
            this.num = 1;
        }
    }

    static class License {
        String name;
        String orgName;

        License(String name, String orgName) {
            this.name = name;
            this.orgName = orgName;
        }
    }

    //构建青岛市证照及部门对应 list
    private static List<License> buildList(XSSFSheet sheet) {
        int size = sheet.getLastRowNum();
        List<License> list = new ArrayList<License>(size);
        int i = 0;
        while (i < size) {
            XSSFRow rowI = sheet.getRow(i);
            String name = CurrencyTools.judgeCell(rowI.getCell(1));
            String orgName = CurrencyTools.judgeCell(rowI.getCell(2));
            if (!orgName.contains("行政审批")) {
                list.add(new License(name, dealOrgName(orgName)));
            }
            i++;
        }
        return list;
    }

    //处理部门名称，去掉区划
    private static String dealOrgName(String orgName) {
        System.out.println(orgName);
        int point = 0;
        if (orgName.contains("市")) {
            point = orgName.lastIndexOf('市');
        } else if (orgName.contains("区")) {
            point = orgName.lastIndexOf('区');
        } else if (orgName.contains("政府")) {
            point = orgName.lastIndexOf('府');
        }
        return orgName.substring(point + 1);
    }

    //处理空值
    public static void main0(String[] args) throws Exception {
        XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(excelPath));
        dealNull(excel.getSheetAt(0));

        FileOutputStream outputStream = new FileOutputStream(excelPath);
        excel.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    private static void dealNull(XSSFSheet sheet) {
        String pre = "";
        int rowNum = sheet.getLastRowNum();
        for (int i = 1; i < rowNum; i++) {
            XSSFRow rowI = sheet.getRow(i);
            XSSFCell cell0 = rowI.getCell(0);
            String licenseName = CurrencyTools.judgeCell(cell0);
            System.out.println(licenseName);
            if (licenseName != null && licenseName.length() > 0) {
                pre = licenseName;
            } else {
                cell0.setCellValue(pre);
            }
            dealNullCode(sheet.getRow(i - 1), rowI);
        }
    }

    private static void dealNullCode(XSSFRow preRow, XSSFRow row) {
        String preName = CurrencyTools.judgeCell(preRow.getCell(0));
        String name = CurrencyTools.judgeCell(row.getCell(0));
        String preCode = CurrencyTools.judgeCell(preRow.getCell(1));
        String code = CurrencyTools.judgeCell(row.getCell(1));
        if ((code != null && code.length() > 0) || preName != name) {
            return;
        }
        row.getCell(1).setCellValue(preCode);
    }
}
