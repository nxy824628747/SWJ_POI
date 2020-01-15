package Demo;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SCZZDemo {
    public static void ma1in(String args[]) {
        try {
            JdbcTemplate jdt = CurrencyTools.getOracleJdbcTemplate("172.18.131.22:1521/LCZW", "license", "license");
            //输入文件路劲
            XSSFWorkbook excel1 = new XSSFWorkbook(new FileInputStream
                    ("C:\\Users\\niuxinyu\\Desktop\\111.xlsx"));
            XSSFSheet sheet0 = excel1.getSheetAt(0);
            for (int i = 2; i <= sheet0.getLastRowNum(); i++) {
                String num = "";
                //要处理的sheet
                XSSFRow rowi = sheet0.getRow(i);
                //取条件的列值
                XSSFCell cell1 = rowi.getCell(1);
                String unitName = CurrencyTools.getCellValue(cell1);
                //查询sql
                String sql = "select a.units_name,count(distinct t.license_type_code) num from license_type t join LICENSE_APPROVEITEMS a \n" +
                        "on  a.license_type_code=t.license_type_code join LICENSE_TYPE_UNITS u\n" +
                        "on a.units_code=u.units_code \n" +
                        "where t.STATE='effective' and a.approveitem_code not like 'A-%'\n" +
                        "and a.units_name like '%" + unitName + "%' group by a.units_name";
                List<Map<String, Object>> result = jdt.queryForList(sql);
                if (result != null && result.size() > 0) {
                    num = result.get(0).get("num").toString();
                } else {
                    num = "0";
                }
                //写入数据的列值
                rowi.getCell(7).setCellValue(num);
                System.out.println("已处理完成 " + i + " 行;值为 ：" + num);
            }
            //输出文件路劲
            excel1.write(new FileOutputStream(
                    "C:\\Users\\niuxinyu\\Desktop\\111.xlsx"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
