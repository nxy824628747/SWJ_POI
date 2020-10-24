package Demo.ytzz;

import Demo.CurrencyTools;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class YTZZExcel5 {
    public static void main(String[] args) throws Exception {
        String path = "/Users/yourouniu/Desktop/电子证照/归集工作/电子证照台账/10.3/六地市汇总.xlsx";
        XSSFWorkbook book = new XSSFWorkbook(new File(path));
        XSSFSheet sheet0 = book.getSheetAt(0);
        XSSFSheet sheet1 = book.getSheetAt(1);
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 1; i < sheet1.getLastRowNum(); i++) {
            XSSFRow rowI = sheet1.getRow(i);
            String name = CurrencyTools.judgeCell(rowI.getCell(1));
            String code = CurrencyTools.judgeCell(rowI.getCell(3));
            map.put(name, code);
        }
        for (int i = 1; i < sheet0.getLastRowNum(); i++) {
            XSSFRow rowI = sheet0.getRow(i);
            String name = CurrencyTools.judgeCell(rowI.getCell(1));
            if (map.containsKey(name)) {
                rowI.createCell(3).setCellValue(map.get(name));
            }
        }
        FileOutputStream outputStream = new FileOutputStream(path);
        book.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
