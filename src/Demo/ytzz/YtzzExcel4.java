package Demo.ytzz;

import Demo.CurrencyTools;
import Demo.utils.LicenseStringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class YtzzExcel4 {
    static final String guoPath = "/Users/yourouniu/Desktop/国家证照类型清单.xlsx";
    static final String shengPath = "/Users/yourouniu/Desktop/省直部门负责的证照数据归集清单.xlsx";
    static final String rePath = "/Users/yourouniu/Desktop/四地市汇总.xlsx";
    static final String ytPath = "/Users/yourouniu/Desktop/yt.xlsx";


    public static void main(String[] args) {
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            List<License> guoList = initGuo(in);
            Set<String> shengSet = initSheng(in);
            in = new FileInputStream(rePath);
            XSSFWorkbook reBook = new XSSFWorkbook(in);
            XSSFSheet sheet0 = reBook.getSheetAt(0);
            //补充类型代码
//            for (int i = 1; i < sheet0.getLastRowNum(); i++) {
////                XSSFRow rowI = sheet0.getRow(i);
////                String licenseName = CurrencyTools.judgeCell(rowI.getCell(1));
////                System.out.println(licenseName);
////                for (int j = 0; j < guoList.size(); j++) {
////                    if (LicenseStringUtils.isSame(guoList.get(j).name, licenseName)) {
////                        rowI.createCell(2).setCellValue(guoList.get(j).code);
////                        rowI.createCell(3).setCellValue(guoList.get(j).name);
////                    }
////                }
////            }
            //补充省证照
            for (int i = 1; i < sheet0.getLastRowNum(); i++) {
                XSSFRow rowI = sheet0.getRow(i);
                String licenseCode = CurrencyTools.judgeCell(rowI.getCell(2));
                if (licenseCode == null || licenseCode.length() == 0) continue;
                if (shengSet.contains(licenseCode)) rowI.createCell(4).setCellValue("是");
            }
            out = new FileOutputStream(rePath);
            reBook.write(out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static List<License> initGuo(FileInputStream in) throws Exception {
        in = new FileInputStream(guoPath);
        XSSFWorkbook workbook = new XSSFWorkbook(in);
        XSSFSheet sheet0 = workbook.getSheetAt(0);
        List<License> guoLicenseList = new LinkedList<License>();
        for (int i = 3; i < sheet0.getLastRowNum(); i++) {
            XSSFRow rowI = sheet0.getRow(i);
            String licenseName = CurrencyTools.judgeCell(rowI.getCell(1));
            String licenseCode = CurrencyTools.judgeCell(rowI.getCell(2));
            guoLicenseList.add(new License(licenseName, licenseCode));
        }
        System.out.println("guo:" + guoLicenseList.size());
        in.close();
        return guoLicenseList;
    }

    private static Set<String> initSheng(FileInputStream in) throws Exception {
        in = new FileInputStream(shengPath);
        XSSFWorkbook workbook = new XSSFWorkbook(in);
        XSSFSheet sheet0 = workbook.getSheetAt(0);
        Set<String> shengLicenseList = new HashSet<String>();
        for (int i = 3; i < sheet0.getLastRowNum(); i++) {
            XSSFRow rowI = sheet0.getRow(i);
            String licenseCode = CurrencyTools.judgeCell(rowI.getCell(3));
            shengLicenseList.add(licenseCode);
        }
        System.out.println("sheng:" + shengLicenseList.size());
        in.close();
        return shengLicenseList;
    }

    static class License {
        String name;
        String code;

        License(String name, String code) {
            this.name = name;
            this.code = code;
        }
    }

}
