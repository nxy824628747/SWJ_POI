package Demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.collections4.functors.ExceptionTransformer;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class AJJTools2 {
   
	//删去空行       开始
	public static void forINSPURexcel(String path) throws FileNotFoundException, IOException{
	   HSSFWorkbook INSPURexcel=new HSSFWorkbook(new FileInputStream(path+"/INSPUR.xls"));
	   HSSFSheet inspurSheet=INSPURexcel.getSheetAt(0);
	   int rowsNUM=inspurSheet.getLastRowNum();
	   for(int i=0;i<rowsNUM;i++){
		  System.out.println("is doing the  "+i+"   rows of INSPURexcel");
		   HSSFRow rowi=inspurSheet.getRow(i);
		   try{
		   rowi.getCell(9).getStringCellValue();
		   if(rowi.getCell(2).getStringCellValue().equals("空行")){
			   inspurSheet.shiftRows(i+1, rowsNUM, -1);
			   rowsNUM=rowsNUM-1;
			   i=i-1;
		   }
		   else if(rowi.getCell(2).getStringCellValue().length()==0&&rowi.getCell(4).getStringCellValue().length()==0){
			   inspurSheet.shiftRows(i+1, rowsNUM, -1);
			   rowsNUM=rowsNUM-1;
			   i=i-1;
		   }
		   }catch(Exception e){
			  if(i==rowsNUM-1){
				  inspurSheet.shiftRows(rowsNUM, rowsNUM, 0);
			  }else{
			   inspurSheet.shiftRows(i+1, rowsNUM, -1);
			   rowsNUM=rowsNUM-1;
			   i=i-1;
			  }
		   }
		   
	   }
	   INSPURexcel.write(new FileOutputStream(path+"/INSPUR.xls"));
   }
   //删去空行     结束
	
	
	//顺序添加流水号     开始
	public static void nums(String path) throws FileNotFoundException, IOException{
		HSSFWorkbook INSPURexcel=new HSSFWorkbook(new FileInputStream(path+"/INSPUR.xls"));
		HSSFSheet inspurSheet=INSPURexcel.getSheetAt(0);
		int rowsNUM=inspurSheet.getLastRowNum();
		for(int i=1;i<=rowsNUM;i++){
			HSSFRow rowi=inspurSheet.getRow(i);
			rowi.createCell(0).setCellValue(i+15500);
		}
      INSPURexcel.write(new FileOutputStream(path+"/INSPUR.xls"));
	}
	//顺序添加流水号     结束
	
	//处理日期  开始
	public static void dataMain(String path) throws FileNotFoundException, IOException{
		HSSFWorkbook INSPURexcel = new HSSFWorkbook(new FileInputStream(path+"/INSPUR.xls"));
		HSSFSheet inspursheetHssfSheet=INSPURexcel.getSheetAt(0);
		int rowNUM=inspursheetHssfSheet.getLastRowNum();
		for(int i=1;i<rowNUM;i++){
			HSSFRow inspurROW=inspursheetHssfSheet.getRow(i);
			//第五列处理(完善前)
			/*HSSFCell cell5 = inspurROW.getCell(5);
			String cell5String=null;
			try{
				cell5String=cell5.getStringCellValue();
			}
			catch(Exception e){
				cell5String="88888888";
			}
			StringBuilder cell5StringBuilder=new StringBuilder(cell5String);
			//System.out.println(cell5StringBuilder.length());
			if(cell5StringBuilder.length()<8){
				cell5StringBuilder.insert(0,"2017-");
				//System.out.println(cell5StringBuilder);
				cell5.setCellValue(cell5StringBuilder.toString());
			}
			*/
			//第六列处理(完善前)
		/*	HSSFCell cell6= inspurROW.getCell(6);
			String cell6String=null;
			try{
				cell6String=cell6.getStringCellValue();
			}
			catch(Exception e){
				cell6String="88888888";
			}
			StringBuilder cell6StringBuilder=new StringBuilder(cell6String);
			System.out.println(cell5StringBuilder.length());
			if(cell6StringBuilder.length()<8){
				cell6StringBuilder.insert(0,"2017-");
				System.out.println(cell5StringBuilder);
				cell6.setCellValue(cell6StringBuilder.toString());
			}
			*/
			//第六列处理
			HSSFCell cell6= inspurROW.getCell(6);
			String cell6String=null;
			try{
				cell6String=CurrencyTools.judgeCell(cell6);
			}
			catch(Exception e){
				cell6String="空值";
			}
	       if(cell6String.indexOf("2017")==-1&&cell6String.indexOf("2016")==-1){
	    	   System.out.println("this data has not year 2017  ");
	    	   System.out.println("this data has not year 2016");
		       System.out.println("insert 2017 to  "+i+"   row ");
		    	   StringBuilder cell6StringBuilder=new StringBuilder(cell6String);
					cell6StringBuilder.insert(0,"2017/");
			   System.out.println(cell6StringBuilder);
					try{
					cell6.setCellValue(cell6StringBuilder.toString());
					}catch(Exception e){
				System.out.println("the cell6 of row   "+i+"   is null");
					}
	       }
	       
	       //第五列处理
			HSSFCell cell5= inspurROW.getCell(5);
			String cell5String=null;
			try{
				cell5String=CurrencyTools.judgeCell(cell5);
			}
			catch(Exception e){
				cell5String="空值";
			}
	       if(cell5String.indexOf("2017")==-1&&cell5String.indexOf("2016")==-1){
	    	   System.out.println("this data has not year 2017  ");
	    	   System.out.println("this data has not year 2016");
		       System.out.println("insert 2017 to  "+i+"   row ");
		    	   StringBuilder cell5StringBuilder=new StringBuilder(cell5String);
					cell5StringBuilder.insert(0,"2017/");
			   System.out.println(cell5StringBuilder);
					try{
					cell5.setCellValue(cell5StringBuilder.toString());
					}catch(Exception e){
				System.out.println("the cell5 of row   "+i+"   is null");
					}
	       }
		}
		INSPURexcel.write(new FileOutputStream(path+"/INSPUR.xls"));
	}
	//处理日期  结束
	
	//处理日期中的“.”  开始
	public static void dataDian(String path) throws FileNotFoundException, IOException{
		HSSFWorkbook INSPURexcel = new HSSFWorkbook(new FileInputStream(path+"/INSPUR.xls"));
		HSSFSheet inspursheetHssfSheet=INSPURexcel.getSheetAt(0);
		int rowNUM=inspursheetHssfSheet.getLastRowNum();
		for(int i=1;i<rowNUM;i++){
			System.out.println("datadian  :  "+i);
			HSSFRow rowi = inspursheetHssfSheet.getRow(i);
		  //第五列处理
		   HSSFCell cell5 = rowi.getCell(5);
		   try{
			   String cell5String = CurrencyTools.judgeCell(cell5);
			   StringBuilder cell5StringBuilder = new StringBuilder(cell5String);
			   int dian=cell5String.indexOf(".");
			   cell5StringBuilder.deleteCharAt(dian);
			   cell5StringBuilder.insert(dian,"/");
			   System.out.println("the data after did is  :"+cell5StringBuilder);
			   cell5.setCellValue(cell5StringBuilder.toString());
		   }catch(Exception e){
			   
		        }
		   
		   //第六列处理
		   HSSFCell cell6 = rowi.getCell(6);
		   try{
			   String cell6String = CurrencyTools.judgeCell(cell6);
			   StringBuilder cell6StringBuilder = new StringBuilder(cell6String);
			   int dian=cell6String.indexOf(".");
			   cell6StringBuilder.deleteCharAt(dian);
			   cell6StringBuilder.insert(dian,"/");
			   System.out.println("the data after doing is  :"+cell6StringBuilder);
			   cell6.setCellValue(cell6StringBuilder.toString());
		   }catch(Exception e){
			   
		        }
		  
		   }
		INSPURexcel.write(new FileOutputStream(path+"/INSPUR.xls"));
		}
	
	//处理日期中的“.”  结束
	public static void hengchuli(String path) throws FileNotFoundException, IOException{
		HSSFWorkbook INSPURexcel = new HSSFWorkbook(new FileInputStream(path+"/INSPUR.xls"));
		HSSFSheet inspursheetHssfSheet=INSPURexcel.getSheetAt(0);
		int rowNUM=inspursheetHssfSheet.getLastRowNum();
		for(int i=0;i<rowNUM;i++){
			System.out.println("hengchuli  :  "+i);
			try{
				HSSFRow rowi=inspursheetHssfSheet.getRow(i);
				HSSFCell cell5=rowi.getCell(5);
				HSSFCell cell6=rowi.getCell(6);
				if(CurrencyTools.judgeCell(cell5).contains("-")){
					int heng=CurrencyTools.judgeCell(cell5).indexOf("-");
					StringBuilder hengBuilder=new StringBuilder(CurrencyTools.judgeCell(cell5));
					hengBuilder.deleteCharAt(heng);
					hengBuilder.insert(heng,"/");
					System.out.println("the data after doing is  :"+hengBuilder);
					cell5.setCellValue(hengBuilder.toString());
				}
				if(CurrencyTools.judgeCell(cell6).contains("-")){
					int heng=CurrencyTools.judgeCell(cell6).indexOf("-");
					StringBuilder hengBuilder=new StringBuilder(CurrencyTools.judgeCell(cell6));
					hengBuilder.deleteCharAt(heng);
					hengBuilder.insert(heng,"/");
					System.out.println("the data after doing is  :"+hengBuilder);
					cell6.setCellValue(hengBuilder.toString());
				}
			}catch(Exception e){
				
			}
			INSPURexcel.write(new FileOutputStream(path+"/INSPUR.xls"));
		}
	}
	
	public static void datajiaxie(String path) throws FileNotFoundException, IOException{
		
		HSSFWorkbook INSPURexcel = new HSSFWorkbook(new FileInputStream(path));
		HSSFSheet inspursheetHssfSheet=INSPURexcel.getSheetAt(0);
		int rowNUM=inspursheetHssfSheet.getLastRowNum();
		
		for(int i=0;i<rowNUM;i++){
			System.out.println("datajiaxie  :  "+i);
			try{	
			HSSFRow rowi=inspursheetHssfSheet.getRow(i);
		    HSSFCell cell5 = rowi.getCell(5);
			HSSFCell cell6 = rowi.getCell(6);
			String shijian5=CurrencyTools.judgeCell(cell5);
			if(!shijian5.contains("/")){
		      StringBuilder shijianb5=new StringBuilder(shijian5);
		      shijianb5.insert(4,"/");
		      shijianb5.insert(7,"/");
		      cell5.setCellValue(shijianb5.toString());
			}
			String shijian6=CurrencyTools.judgeCell(cell6);
			if(!shijian6.contains("/")){
			      StringBuilder shijianb6=new StringBuilder(shijian6);
			      shijianb6.insert(4,"/");
			      shijianb6.insert(7,"/");
			      cell6.setCellValue(shijianb6.toString());
				}
		}catch(Exception e){
			
		}
		}
		INSPURexcel.write(new FileOutputStream(path));
	}
	public static void dataquxiexian(String path) throws FileNotFoundException, IOException{
		HSSFWorkbook INSPURexcel = new HSSFWorkbook(new FileInputStream(path+"/INSPUR.xls"));
		HSSFSheet inspursheetHssfSheet=INSPURexcel.getSheetAt(0);
		int rowNUM=inspursheetHssfSheet.getLastRowNum();
		for(int i=0;i<rowNUM;i++){
			System.out.println("dataquxiexian  :  "+i);
			HSSFRow rowi=inspursheetHssfSheet.getRow(i);
		    HSSFCell cell5 = rowi.getCell(5);
			HSSFCell cell6 = rowi.getCell(6);
			String shijian5=CurrencyTools.judgeCell(cell5);
			String shijian6=CurrencyTools.judgeCell(cell6);
			int shijian5mo = shijian5.length();
			int shijian6mo = shijian6.length();
			StringBuilder shijian5b=new StringBuilder(shijian5);
			StringBuilder shijian6b=new StringBuilder(shijian6);
			if(shijian5.endsWith("/")){
				shijian5b.deleteCharAt(shijian5mo-1);
				cell5.setCellValue(shijian5b.toString());
			}
			if(shijian6.endsWith("/")){
				shijian6b.deleteCharAt(shijian6mo-1);
				cell6.setCellValue(shijian6b.toString());
			}
			
		}
		INSPURexcel.write(new FileOutputStream(path+"/INSPUR.xls"));
	}

}
