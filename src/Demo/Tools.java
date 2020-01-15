package Demo;
import java.io.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Tools {
	
	public static String getCellValue(XSSFCell cell){
		if(cell==null){
			return null;
		}
		else{
		return cell.toString();
		}
	}
	//商务局创建表头并设置列宽
	public static void setStyleToNewSheet(XSSFSheet newsheet,XSSFCellStyle style){
	    XSSFRow newRrow=newsheet.createRow((short)0);
	    XSSFCell cell0=newRrow.createCell((short)0);
	    cell0.setCellValue("许可证号");
	    cell0.setCellStyle(style);
	    XSSFCell cell1=newRrow.createCell((short)1);
	    cell1.setCellValue("商品名称编码");
	    cell1.setCellStyle(style);
	    XSSFCell cell2=newRrow.createCell((short)2);
	    cell2.setCellValue("商品名称");
	    cell2.setCellStyle(style);
	    XSSFCell cell3=newRrow.createCell((short)3);
	    cell3.setCellValue("进口使用单位组织机构代码");
	    cell3.setCellStyle(style);
	    XSSFCell cell4=newRrow.createCell((short)4);
	    cell4.setCellValue("进口使用单位名称");
	    cell4.setCellStyle(style);
	    XSSFCell cell5=newRrow.createCell((short)5);
	    cell5.setCellValue("申请进口单位组织机构代码");
	    cell5.setCellStyle(style);
	    XSSFCell cell6=newRrow.createCell((short)6);
	    cell6.setCellValue("申请进口单位名称");
	    cell6.setCellStyle(style);
	    newsheet.setColumnWidth(0,3500);
	    newsheet.setColumnWidth(1,3500);
	    newsheet.setColumnWidth(2,7000);
	    newsheet.setColumnWidth(3,7000);
	    newsheet.setColumnWidth(4,7000);
	    newsheet.setColumnWidth(5,7000);
	    newsheet.setColumnWidth(6,7000);	
	}
	
	//商务局专用数据处理
	public static void setMessageToNewSheet(XSSFSheet oldsheet,XSSFSheet newsheet,XSSFCellStyle newStyle) throws IOException{
		int iterator=oldsheet.getLastRowNum()/2;
		//System.out.println("the iterator is : "+iterator+"   and the Row's Number is :"+oldsheet.getLastRowNum());
		System.out.println("商务局专网导出文件处理 ");
		for(int i=1;i<=iterator;i++){
			XSSFRow newRrow=newsheet.createRow((short)i);
			XSSFCell cell0=newRrow.createCell((short)0);
			cell0.setCellStyle(newStyle);
			cell0.setCellStyle(newStyle);
			XSSFCell cell1=newRrow.createCell((short)1);
			cell1.setCellStyle(newStyle);
			XSSFCell cell2=newRrow.createCell((short)2);
			cell2.setCellStyle(newStyle);
			XSSFCell cell3=newRrow.createCell((short)3);
			cell3.setCellStyle(newStyle);
			XSSFCell cell4=newRrow.createCell((short)4);
			cell4.setCellStyle(newStyle);
			XSSFCell cell5=newRrow.createCell((short)5);
			cell5.setCellStyle(newStyle);
			XSSFCell cell6=newRrow.createCell((short)6);
			cell6.setCellStyle(newStyle);
			XSSFCell cell7=newRrow.createCell((short)7);
			cell7.setCellStyle(newStyle);
			//System.out.println(getCellValue(oldsheet.getRow(2*i-1).getCell((short)1))+"         "+oldsheet.getLastRowNum());
			//String test=getCellValue(oldsheet.getRow(2*i-1).getCell((short)1));
			/*cell1.setCellValue(oldsheet.getRow(2*i-1).getCell((short)1).getNumericCellValue());
			cell2.setCellValue(oldsheet.getRow(2*i).getCell((short)1).getStringCellValue());
			cell3.setCellValue(oldsheet.getRow(2*i-1).getCell((short)2).getNumericCellValue());
			cell4.setCellValue(oldsheet.getRow(2*i).getCell((short)2).getStringCellValue());
			cell5.setCellValue(oldsheet.getRow(2*i-1).getCell((short)3).getNumericCellValue());
			cell6.setCellValue(oldsheet.getRow(2*i).getCell((short)3).getStringCellValue());*/
			cell1.setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(2*i-1).getCell((short)1)));
			cell2.setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(2*i).getCell((short)1)));
			cell3.setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(2*i-1).getCell((short)2)));
			cell4.setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(2*i).getCell((short)2)));
			cell5.setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(2*i-1).getCell((short)3)));
			cell6.setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(2*i).getCell((short)3)));
			cell7.setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(2*i-1).getCell((short)4)));
			
			//单独处理第一列
		    newRrow.createCell((short)0).setCellValue(oldsheet.getRow(2*i-1).getCell((short)0).getStringCellValue());
		    System.out.println("正在插入第   "+i+" 行");
		    System.out.println("共插入   "+i+" 条数据");
		}
		    
		    System.out.println("商务局专网导出文件处理已完成，结果文件   ：  D:/excel处理程序/结果文件.xlsx/InspurSheet");
	}
	public static void dataxie(String path) throws FileNotFoundException, IOException{
		XSSFWorkbook excel1=new XSSFWorkbook(new FileInputStream(path)); 
		XSSFSheet sheet0=excel1.getSheetAt(1);
		int rowNUM=sheet0.getLastRowNum();
		for(int i=0;i<rowNUM;i++){
			XSSFRow rowi=sheet0.getRow(i);
			XSSFCell cell7=rowi.getCell(7);
			StringBuilder data=new StringBuilder(CurrencyTools.judgeCell(cell7));
			try{
			data.insert(4,"/");
			data.insert(7,"/");
			cell7.setCellValue(data.toString());
			System.out.println(data.toString());
			}
			catch(Exception e){}

		}
		excel1.write(new FileOutputStream(path));
	}

}
