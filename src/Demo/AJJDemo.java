package Demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import learning.Car;
import org.apache.jute.Index;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class AJJDemo {
    public static void mai1n(String args[]) throws FileNotFoundException, IOException {
    	/*处理单个excel   开始
    	 //String path="C:/Users/Administrator/Desktop/11 - 副本.xls";
    	 //AJJTools.YuChuLi("C:/Users/Administrator/Desktop/AJJ123456/370201004-01-17-0001电工作业特种作业人员发证登记表.xls");
    	 //AJJTools.combineSheet(path);
    	  *  处理单个excel     结束
    	  */

    	 
    	/*处理一个文件夹中所有excel    开始
    	 AJJTools.combinExcel("C:/Users/Administrator/Desktop/AJJ");
    	 处理一个文件夹中所有excel结束
    	 */
    	 
    	 /*测试用
    	 HSSFWorkbook excel1=new HSSFWorkbook(new FileInputStream("C:/Users/Administrator/Desktop/666.xls"));
    	 HSSFSheet sheet1=excel1.getSheetAt(0);
    	 HSSFRow row23=sheet1.getRow(22);
    	 System.out.println(CurrencyTools.judgeCell(row23.getCell(2)));
    	 
    	  
    	 
    	 String path="C:/Users/Administrator/Desktop/AJJ";
    	// HSSFWorkbook excel1=new HSSFWorkbook(new FileInputStream("C:/Users/Administrator/Desktop/AJJ/370201018-01-17-0096.xls"));
    	 AJJTools2.datajiaxie(path+"/2046-2771待处理.xls");
		 
    	 HSSFSheet oldsheet=excel1.getSheetAt(0);
		 HSSFRow row3=oldsheet.getRow(2);
		 System.out.println(CurrencyTools.judgeCell(row3.getCell(13)));
		 System.out.println(CurrencyTools.judgeCell(row3.getCell(14)));
		
		 int rowNUM=oldsheet.getLastRowNum();
		 int liushuihao = 0;
		 int shenqingren = 0;
		 int shenfenzhenghao = 0;
		 int shenqingshijian = 0;
		 int banjieshijian = 0;
		 int kuozhan1 = 0;
		 int kuozhan2 = 0;
		 for(int j=0;j<rowNUM;j++){
			 HSSFRow rowj=oldsheet.getRow(j);
			 try{
				 rowj.getCell(0).getNumericCellValue();
				 System.out.println(rowj.getCell(0).getNumericCellValue());
				 
				 for(int k=0;k<rowj.getLastCellNum();k++){
					 System.out.println( rowj.getLastCellNum());
					 if(CurrencyTools.judgeCell(oldsheet.getRow(j-1).getCell(k)).contains("序")||CurrencyTools.judgeCell(oldsheet.getRow(j-2).getCell(k)).contains("序")){
						 liushuihao = k;
						 System.out.println("xuhao lie is :"+k);
					 }
					 if(CurrencyTools.judgeCell(oldsheet.getRow(j-2).getCell(k)).contains("姓")||CurrencyTools.judgeCell(oldsheet.getRow(j-1).getCell(k)).contains("姓")){
						 shenqingren = k;
						 System.out.println("shenqingren lie is :"+k);
					 }
					 if(CurrencyTools.judgeCell(oldsheet.getRow(j-2).getCell(k)).contains("身份")||CurrencyTools.judgeCell(oldsheet.getRow(j-1).getCell(k)).contains("身份")){
						 shenfenzhenghao = k;
						 System.out.println("shenfenzhenghao lie is :"+k);
					 }
					 
					 if(CurrencyTools.judgeCell(oldsheet.getRow(j-2).getCell(k)).contains("作业")||CurrencyTools.judgeCell(oldsheet.getRow(j-1).getCell(k)).contains("作业")){
						 kuozhan1 = k;
						 System.out.println("kuozhan1 lie is :"+k);
					 }
					 if(CurrencyTools.judgeCell(oldsheet.getRow(j-2).getCell(k)).contains("准操")||CurrencyTools.judgeCell(oldsheet.getRow(j-1).getCell(k)).contains("准操")){
						 kuozhan2 = k;
						 System.out.println("kuozhan2 lie is :"+k);
						 }
				    if(CurrencyTools.judgeCell(oldsheet.getRow(j-2).getCell(k)).contains("申请时间")||CurrencyTools.judgeCell(oldsheet.getRow(j-1).getCell(k)).contains("申请时间")){
							 shenqingshijian = k;
							 System.out.println("shenqingshijian lie is :"+k);
						 }
				    if(CurrencyTools.judgeCell(oldsheet.getRow(j-2).getCell(k)).contains("办结时间")||CurrencyTools.judgeCell(oldsheet.getRow(j-1).getCell(k)).contains("办结时间")){
							 banjieshijian = k;
							 System.out.println("banjieshijian lie is :"+k);
						 }
					 
				 }
			 }catch(Exception e ){}
			 }
			 */

        String path = "C:/Users/Administrator/Desktop/AJJ";
        AJJTools.combinExcel("C:/Users/Administrator/Desktop/AJJ");
        System.out.println("combineINSPURexce1     is   completed ");
        AJJTools2.forINSPURexcel(path);
        System.out.println("forINSPURexce1     is   completed ");
        AJJTools2.nums(path);
        System.out.println("Num had been set to excel");
        AJJTools2.forINSPURexcel(path);
        System.out.println("forINSPURexce2     is   completed ");
        AJJTools2.dataMain(path);
        System.out.println("data main is completed");
        AJJTools2.dataDian(path);
        AJJTools2.dataDian(path);
        System.out.println("some of this is completed");
        //AJJTools2.hengchuli(path);
        //AJJTools2.hengchuli(path);
        //AJJTools2.datajiaxie(path);
        //AJJTools2.dataquxiexian(path);
        System.out.println("all of this is completed");

    }

}
