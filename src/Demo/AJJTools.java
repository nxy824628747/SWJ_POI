package Demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AJJTools {
    //为一个excel中的所有sheet增加列：申请人     开始
	public static void YuChuLi(String path) throws FileNotFoundException, IOException{
    	   HSSFWorkbook excel1=new HSSFWorkbook(new FileInputStream(path));
    	   int sheetNum=excel1.getNumberOfSheets();
    	  //System.out.println(path+"  have  "+ sheetNum + "  sheets");
    	   for(int k=0;k<sheetNum;k++){
    	   HSSFSheet sheet0=excel1.getSheetAt(k);
    	   System.out.println("正在处理   ： sheet"+k);
   		   HSSFRow row1=sheet0.getRow(1);

   		   StringBuilder PXDW=new StringBuilder();
   		   try{
   		   PXDW.append(CurrencyTools.judgeCell(row1.getCell(0)));
          }
          catch(Exception e){
        	  PXDW.append("123");
          }
   		   System.out.println("Stringbuilder长度      ："+PXDW.length());
   		   if(PXDW.length()<5){
   			   System.out.println("the sheet  "+k+" of "+path+"  is inspursheet!");
   		   }
   		   else if(PXDW.length()>50){
   			   String pxdwString=PXDW.toString();
   			   int gaizhang=pxdwString.indexOf("（盖章）：");
   			   System.out.println(PXDW.toString());
   			   System.out.println("index of gaizhang  is   :" +gaizhang);
   			   int banjimingcheng = pxdwString.indexOf("班级名");
   			   System.out.println("index of banjimingcheng  is   :" +banjimingcheng);
   			   int zong = pxdwString.length();
   			   System.out.println("index of zong  is   :" +zong);
   			PXDW.delete(banjimingcheng,zong);
		    PXDW.delete(0,gaizhang+5);
		    PXDW.toString().trim();
   		   }
   		   else{
   			try{
   	   		   PXDW.append(CurrencyTools.judgeCell(row1.getCell(1)));
   	   		   PXDW.append(CurrencyTools.judgeCell(row1.getCell(2)));
   	   		   PXDW.append(CurrencyTools.judgeCell(row1.getCell(3)));
   	   		   PXDW.append(CurrencyTools.judgeCell(row1.getCell(4)));
   	   		   PXDW.append(CurrencyTools.judgeCell(row1.getCell(5)));
   	   	       PXDW.append(CurrencyTools.judgeCell(row1.getCell(6)));
   	           PXDW.append(CurrencyTools.judgeCell(row1.getCell(7)));
               PXDW.append(CurrencyTools.judgeCell(row1.getCell(8)));
   	           PXDW.append(CurrencyTools.judgeCell(row1.getCell(9)));
   	           PXDW.append(CurrencyTools.judgeCell(row1.getCell(10)));
   	           String pxdwString=PXDW.toString();
			   int gaizhang=pxdwString.indexOf("（盖章）：");
			   System.out.println(PXDW.toString());
			   System.out.println("index of gaizhang  is   :" +gaizhang);
			   int banjimingcheng = pxdwString.indexOf("班级名");
			System.out.println("index of banjimingcheng  is   :" +banjimingcheng);
			   int zong = pxdwString.length();
			   System.out.println("index of zong  is   :" +zong);
   	          }catch(Exception e){
   	        	  
   	          }
   			   String pxdwString=PXDW.toString();
			   int gaizhang=pxdwString.indexOf("（盖章）：");
			   System.out.println(PXDW.toString());
			   System.out.println("index of gaizhang  is   :" +gaizhang);
			   int banjimingcheng = pxdwString.indexOf("班级名");
			   System.out.println("index of banjimingcheng  is   :" +banjimingcheng);
			   int zong = pxdwString.length();
			   System.out.println("index of zong  is   :" +zong);
			PXDW.delete(banjimingcheng,zong);
		    PXDW.delete(0,gaizhang+5);
   		   }
   			
   			System.out.println(PXDW.toString());
   		    for(int j=0;j<=sheet0.getLastRowNum();j++){
   		    HSSFRow rowi = sheet0.getRow(j);
   			//int cellnum=row1.getLastCellNum()+1;
   		    try{
   		    	String str=PXDW.toString().replace(" ","");
   			rowi.createCell((short)16).setCellValue(str);
   			System.out.println("第  "+j+"+1行申请人插入成功");
   		    }catch(Exception e){
   		    	System.out.println("the last row is added");
   		      }
   		    }

    	   }
    	   excel1.write(new FileOutputStream(path));
	}
	 //为一个excel中的所有sheet增加列：申请人     结束
	
	
	//将一个workbook中的所有sheet合并成一个标准的inspursheet 开始
	/*
	public static void combineSheet(String path) throws FileNotFoundException, IOException{
		//新建inspursheet开始 
		 HSSFWorkbook excel1=new HSSFWorkbook(new FileInputStream(path));
		 HSSFSheet inspursheet=excel1.createSheet("inspursheet");
		 HSSFRow row0=inspursheet.createRow(0);
		 row0.createCell(0).setCellValue("流水号");
		 row0.createCell(1).setCellValue("审批事项名称");
		 row0.createCell(2).setCellValue("申请人");
		 row0.createCell(3).setCellValue("申请人类型");
		 row0.createCell(4).setCellValue("申请人身份证号");
		 row0.createCell(5).setCellValue("申报日期");
		 row0.createCell(6).setCellValue("办结日期");
		 row0.createCell(7).setCellValue("办理人");
		 row0.createCell(8).setCellValue("扩展列1");
		 row0.createCell(9).setCellValue("扩展列2");
		 //新建inspursheet结束
		 
		 for(int i=0;i<excel1.getNumberOfSheets()-1;i++){
			 System.out.println(excel1.getNumberOfSheets());
			 HSSFSheet oldsheet = excel1.getSheetAt(i);
			 
			 int inspurrows = inspursheet.getLastRowNum();
			 try{
			 if(CurrencyTools.judgeCell(oldsheet.getRow(3).getCell(14)).contains("申请")){
			 for(int j=4;j<oldsheet.getLastRowNum();j++){
		 System.out.println(oldsheet.getLastRowNum());
		        HSSFRow newinspurRow=inspursheet.createRow(inspurrows+j-3);
		       // if(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(2)).equals("审核人：?????????????年??月??日"))
		        
		      try{
		        if(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(2)).indexOf("审核人：")!=-1)
		        {
		        	j=j+5;
		        }
		        else{
		        try{
		 
		        newinspurRow.createCell(0).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(0)));
		        newinspurRow.createCell(1).setCellValue("特种作业人员操作资格认定"); 
		        newinspurRow.createCell(2).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(1)));
		        newinspurRow.createCell(3).setCellValue("自然人");
		        newinspurRow.createCell(4).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(5)));
		        	for(int k=0;k<17;k++){
		        		if(CurrencyTools.judgeCell(oldsheet.getRow(3).getCell(k)).contains("申请")){
		        			newinspurRow.createCell(5).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(k)));
		        		}
		        		if(CurrencyTools.judgeCell(oldsheet.getRow(3).getCell(k)).contains("办结")){
		        			newinspurRow.createCell(6).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(k)));
		        		}
		        	}
		        
		        newinspurRow.createCell(7).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(16)));
		        newinspurRow.createCell(8).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(7)));
		        newinspurRow.createCell(9).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(8)));
		        		}
		        catch(Exception e){
		        	j=j+1;
		         }
		        }
		      }
		      catch(Exception e){
		    	  System.out.println("kong");
		      }
			 }			 
			 } else{
			 for(int j=4;j<oldsheet.getLastRowNum();j++){
				 System.out.println(oldsheet.getLastRowNum());
				        HSSFRow newinspurRow=inspursheet.createRow(inspurrows+j-3);
				       // if(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(2)).equals("审核人：?????????????年??月??日"))
				        
				      try{
				        if(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(2)).indexOf("审核人：")!=-1)
				        {
				        	j=j+5;
				        }
				        else{
				        try{
				 
				        newinspurRow.createCell(0).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(0)));
				        newinspurRow.createCell(1).setCellValue("特种作业人员操作资格认定"); 
				        newinspurRow.createCell(2).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(1)));
				        newinspurRow.createCell(3).setCellValue("自然人");
				        newinspurRow.createCell(4).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(5)));
				        for(int k=0;k<17;k++){
			        		if(CurrencyTools.judgeCell(oldsheet.getRow(3).getCell(k)).contains("申请")){
			        			newinspurRow.createCell(5).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(k)));
			        		}
			        		if(CurrencyTools.judgeCell(oldsheet.getRow(3).getCell(k)).contains("办结")){
			        			newinspurRow.createCell(6).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(k)));
			        		}
			        	}
				        try{
				        	if(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(16)).contains("   ")){
				        		newinspurRow.createCell(7).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(6)));
				        	}else{
				        		
				        		newinspurRow.createCell(7).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(16)));
				        	}
				        }catch(Exception e){
				        	newinspurRow.createCell(7).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(6)));
				        }
				        newinspurRow.createCell(8).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(7)));
				        newinspurRow.createCell(9).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(8)));
				        		}
				        catch(Exception e){
				        	j=j+1;
				         }
				        }
				      }
				      catch(Exception e){
				    	  System.out.println("kong");
				      }
					 }			
		 }
			 }catch(Exception e){
				 System.out.println("this is a inspursheet~");
			 }
		 }
		        excel1.write(new FileOutputStream(path));
	}*/
	
	
	public static void combineSheet(String path) throws FileNotFoundException, IOException{
		//新建inspursheet开始 
		 HSSFWorkbook excel1=new HSSFWorkbook(new FileInputStream(path));
		 HSSFSheet inspursheet=excel1.createSheet("inspursheet");
		 HSSFRow row0=inspursheet.createRow(0);
		 row0.createCell(0).setCellValue("流水号");
		 row0.createCell(1).setCellValue("审批事项名称");
		 row0.createCell(2).setCellValue("申请人");
		 row0.createCell(3).setCellValue("申请人类型");
		 row0.createCell(4).setCellValue("申请人身份证号");
		 row0.createCell(5).setCellValue("申报日期");
		 row0.createCell(6).setCellValue("办结日期");
		 row0.createCell(7).setCellValue("办理人");
		 row0.createCell(8).setCellValue("扩展列1");
		 row0.createCell(9).setCellValue("扩展列2");
		 //新建inspursheet结束
		 int liushuihao = 0;
		 int shenqingren = 0;
		 int shenfenzhenghao = 0;
		 int shenqingshijian = 0;
		 int banjieshijian = 0;
		 int kuozhan1 = 0;
		 int kuozhan2 = 0;
		 for(int i=0;i<excel1.getNumberOfSheets()-1;i++){
			 System.out.println(excel1.getNumberOfSheets());
			 HSSFSheet oldsheet = excel1.getSheetAt(i);
			 int rowNUM=oldsheet.getLastRowNum();
			 int inspurrows = inspursheet.getLastRowNum();

			 for(int j=0;j<rowNUM;j++){
				
				 HSSFRow newinspurRow=inspursheet.createRow(inspurrows+j);
				 HSSFRow rowj=oldsheet.getRow(j);
				 
				 try{
					 rowj.getCell(0).getNumericCellValue();
					 System.out.println(rowj.getCell(0).getNumericCellValue());
					 
					 for(int k=0;k<rowj.getLastCellNum();k++){
						 
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
						
						 newinspurRow.createCell(0).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(liushuihao)));
					     newinspurRow.createCell(1).setCellValue("特种作业人员操作资格认定"); 
					     newinspurRow.createCell(2).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(shenqingren)));
					     newinspurRow.createCell(3).setCellValue("自然人");
					     newinspurRow.createCell(4).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(shenfenzhenghao)));
					     newinspurRow.createCell(7).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(16)));
					     newinspurRow.createCell(8).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(kuozhan1)));
					     newinspurRow.createCell(9).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(kuozhan2)));
					     try{
					    	 if(oldsheet.getRow(j).getCell(shenqingshijian).getDateCellValue().toString().contains("2017-")||
				            oldsheet.getRow(j).getCell(shenqingshijian).getDateCellValue().toString().contains("2016-")||
				            oldsheet.getRow(j).getCell(shenqingshijian).getDateCellValue().toString().contains("2017/")||
				            oldsheet.getRow(j).getCell(shenqingshijian).getDateCellValue().toString().contains("2017年")||
				            oldsheet.getRow(j).getCell(shenqingshijian).getDateCellValue().toString().contains("2016/")||
				            oldsheet.getRow(j).getCell(shenqingshijian).getDateCellValue().toString().contains("2016年"))
					    	{
				        	 Date date = oldsheet.getRow(j).getCell(shenqingshijian).getDateCellValue();
				        	 java.text.SimpleDateFormat formatter = new SimpleDateFormat( "YYYY-MM-dd");
				        	 String date1 = formatter.format(date);
				        	 newinspurRow.createCell(5).setCellValue(date1);
				         }else if(oldsheet.getRow(j).getCell(shenqingshijian).getDateCellValue().toString().contains("1900")){
				        	 newinspurRow.createCell(5).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(shenqingshijian)));
				         }else{
				        	 newinspurRow.createCell(5).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(shenqingshijian)));
				         }
					    	 }catch(Exception e){
					    		 newinspurRow.createCell(5).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(shenqingshijian)));
				         }
					     try{
					     if(oldsheet.getRow(j).getCell(shenqingshijian).getDateCellValue().toString().contains("2017-")||
						            oldsheet.getRow(j).getCell(shenqingshijian).getDateCellValue().toString().contains("2016-")||
						            oldsheet.getRow(j).getCell(shenqingshijian).getDateCellValue().toString().contains("2017/")||
						            oldsheet.getRow(j).getCell(shenqingshijian).getDateCellValue().toString().contains("2017年")||
						            oldsheet.getRow(j).getCell(shenqingshijian).getDateCellValue().toString().contains("2016/")||
						            oldsheet.getRow(j).getCell(shenqingshijian).getDateCellValue().toString().contains("2016年"))
							    	{
				        	 Date date = oldsheet.getRow(j).getCell(banjieshijian).getDateCellValue();
				        	 java.text.SimpleDateFormat formatter = new SimpleDateFormat( "YYYY-MM-dd");
				        	 String date1 = formatter.format(date);
				        	 newinspurRow.createCell(6).setCellValue(date1);
				         }else if(oldsheet.getRow(j).getCell(banjieshijian).getDateCellValue().toString().contains("1900")){
				        	 newinspurRow.createCell(6).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(banjieshijian)));
				         }else{
				        	 newinspurRow.createCell(6).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(banjieshijian)));
				         }
					    	 }catch(Exception e){
					    		 newinspurRow.createCell(6).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(j).getCell(banjieshijian)));
				         }
				         
					 }
				 }catch(Exception e){
					 System.out.println("this row has null of variable data!");
				 }
			 }
			 
		 }
			 
			 excel1.write(new FileOutputStream(path));
			}
		 
	//将一个workbook中的所有sheet合并成一个标准的inspursheet 结束

	
	//对一个文件夹中的所有excel进行处理，并将结果写入一个已存在的INSPUR.xls   开始
	public static void combinExcel(String path) throws IOException{
	    File file=new File(path);
	    String names[]=new String[file.list().length];
	    names=file.list();
	    System.out.println("WorkBooks are  : "+names.length);
	    HSSFWorkbook INSPURexcel=new HSSFWorkbook(new FileInputStream(path+"/INSPUR.xls"));
	    HSSFSheet INSPURsheet=INSPURexcel.getSheetAt(0);
        for(int i=0;i<names.length;i++){
	    	try{
        	System.out.println(path+"/"+names[i]);
	    	System.out.println("正在处理文件   ： "+path+"/"+names[i]);
	        AJJTools.YuChuLi(path+"/"+names[i]);
	    	AJJTools.combineSheet(path+"/"+names[i]);
	    	}catch(Exception e){
	    		
	    	}
	    }

	    
	    HSSFRow row0=INSPURsheet.createRow(0);
    	 row0.createCell(0).setCellValue("流水号");
		 row0.createCell(1).setCellValue("审批事项名称");
		 row0.createCell(2).setCellValue("申请人");
		 row0.createCell(3).setCellValue("申请人类型");
		 row0.createCell(4).setCellValue("申请人身份证号");
		 row0.createCell(5).setCellValue("申报日期");
		 row0.createCell(6).setCellValue("办结日期");
		 row0.createCell(7).setCellValue("办理人");
		 row0.createCell(8).setCellValue("扩展列1");
		 row0.createCell(9).setCellValue("扩展列2");
		 INSPURexcel.write(new FileOutputStream(path+"/INSPUR.xls"));
		 HSSFWorkbook INSPURexcel2=new HSSFWorkbook(new FileInputStream(path+"/INSPUR.xls"));
		  HSSFSheet INSPURsheet2=INSPURexcel2.getSheetAt(0);
		 try{
		 
	    for(int j=0;j<names.length;j++){
	    	System.out.println("正在处理         :    "+path+"/"+names[j]);
	    	HSSFWorkbook excel1=new HSSFWorkbook(new FileInputStream(path+"/"+names[j]));
	    	int INSPURrowsNum=INSPURsheet2.getLastRowNum();
	    	int sheetNum=excel1.getNumberOfSheets();
	    	HSSFSheet oldsheet=excel1.getSheetAt(sheetNum-1);
	    	System.out.println("the inspursheet of INSPURexcel has "+ INSPURrowsNum+"   rows" );
	    	for(int k=0;k<oldsheet.getLastRowNum();k++){
	    		HSSFRow inspurrow = INSPURsheet2.createRow(INSPURrowsNum+k+1);

		    		for(int l=0;l<10;l++){
		    			try{
		    			inspurrow.createCell(l).setCellValue(CurrencyTools.judgeCell(oldsheet.getRow(k+1).getCell(l)));
		    			}
		    			catch(Exception e){
		    				inspurrow.createCell(l).setCellValue("空行");
		    			}
		    		}
	    	}
	    }
	    }catch(Exception e){
	    	
	    }
	    INSPURexcel2.write(new FileOutputStream(path+"/INSPUR.xls"));
	}
	//对一个文件夹中的所有excel进行处理，并将结果写入一个已存在的INSPUR.xls     结束
}
