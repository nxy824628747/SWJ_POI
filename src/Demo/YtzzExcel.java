package Demo;import org.apache.poi.hssf.usermodel.HSSFSheet;import org.apache.poi.hssf.usermodel.HSSFWorkbook;import org.apache.poi.xssf.usermodel.XSSFSheet;import org.apache.poi.xssf.usermodel.XSSFWorkbook;import java.io.File;import java.io.FileInputStream;import java.io.IOException;public class YtzzExcel {    public static void main(String[] args) throws Exception {        XSSFWorkbook target =new XSSFWorkbook(new File("亮证应用-情况汇总 - 20191202.xlsx"));        HSSFWorkbook source =new HSSFWorkbook(new FileInputStream("政务数据归集百日会战工作台账汇总1129.xls"));        XSSFSheet targetSheet=target.getSheetAt(0);        HSSFSheet sourceSheet=source.getSheetAt(5);        int targetX=targetSheet.getLastRowNum();        int sourceX=sourceSheet.getLastRowNum();        for(int i=1;i<=targetX;i++){        }    }}