package Demo.ytzz;

import Demo.CurrencyTools;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class YTZZExcel2 {
    static String sourcePath = "/Users/yourouniu/Desktop/电子证照/归集工作/电子证照台账/9.26/电子证照台账9.26.xlsx";
    static String targetPath = "/Users/yourouniu/Desktop/电子证照/归集工作/电子证照台账/9.26/20200925在国家目录但不在省直归集目录里面的证照(1)(1).xlsx";
    static String targetPath2 = "/Users/yourouniu/Desktop/电子证照/归集工作/电子证照台账/9.26/111.xlsx";

    public static void main(String[] args) throws Exception {
        XSSFWorkbook source = new XSSFWorkbook(new File(sourcePath));
        XSSFWorkbook target = new XSSFWorkbook(new File(targetPath));
        XSSFSheet sourceSheet = source.getSheetAt(0);
        XSSFSheet targetSheet = target.getSheetAt(0);
        Map<String, License> map = initMap(sourceSheet);
        for (int i = 1; i < targetSheet.getLastRowNum(); i++) {
            System.out.println("处理 " + i + " 行");
            XSSFRow row = targetSheet.getRow(i);
            XSSFCell cell2 = row.getCell(1);
            String licenseCode = CurrencyTools.judgeCell(cell2);
            if (!map.containsKey(licenseCode)) continue;
            License license = map.get(licenseCode);
            row.createCell(3).setCellValue(license.is);
            row.createCell(4).setCellValue(license.org1);
            row.createCell(5).setCellValue(license.org2);
            row.createCell(6).setCellValue(license.reason);
        }
        FileOutputStream outputStream = new FileOutputStream(targetPath2);
        target.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    static class License {
        String code;
        String is;
        String reason;
        String org1;
        String org2;

        License(String code, String is, String reason, String org1, String org2) {
            this.code = code;
            this.is = is;
            this.reason = reason;
            this.org1 = org1;
            this.org2 = org2;
        }
    }

    private static Map<String, License> initMap(XSSFSheet sheet) {
        Map<String, License> map = new HashMap<String, License>();
        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            String code = CurrencyTools.judgeCell(row.getCell(2));
            String is = CurrencyTools.judgeCell(row.getCell(4));
            String reason = CurrencyTools.judgeCell(row.getCell(6));
            String org1 = CurrencyTools.judgeCell(row.getCell(5));
            String org2 = CurrencyTools.judgeCell(row.getCell(7));
            License license = new License(code, is, reason, org1, org2);
            map.put(code, license);
        }
        return map;
    }
}