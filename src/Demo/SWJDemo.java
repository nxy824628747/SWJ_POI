package Demo;
import java.io.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookType;

public class SWJDemo{
	//����ִ���ʼ
	public static void mai1n(String[] args) throws IOException{
		 /*
		String path=new String();
		//�������ļ�,ȥ��ע�Ϳ�ʹ��
		//FileInputStream PropertiesPath=new FileInputStream("D:/Users/Administrator/workspace/SWJ_POI/�����ļ�.xlsx");
		//XSSFWorkbook PropertiesExcel=new XSSFWorkbook(PropertiesPath);
		//path=PropertiesExcel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
		path="D:/excel�������/������Excel";
		//�ϲ�excel
		CurrencyTools.traverseExcel(path);
		//����ϲ����excel
		FileInputStream oldExcelPath=new FileInputStream("D:/excel�������/����ļ�.xlsx");
		XSSFWorkbook excel=new XSSFWorkbook(oldExcelPath);
	    XSSFCellStyle style1 = excel.createCellStyle();
	    style1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
	   
	    //��ȡ������excel
	    XSSFSheet sheet0=excel.getSheetAt(0);
	    XSSFSheet sheet1=excel.createSheet("inspursheet1");
	    Tools.setStyleToNewSheet(sheet1,style1);
	    Tools.setMessageToNewSheet(sheet0,sheet1,style1);
	    //CurrencyTools.CombineTwoSheet(sheet0,sheet1,style1);
	      */
	     
	   //Tools.dataxie("D:/excel�������/����ļ�.xlsx");
	    //FileOutputStream fileOut1=new FileOutputStream("D:/excel�������/����ļ�.xlsx");
	    //excel.write(fileOut1);
	    //System.out.println("Excel������ɣ����ڹرճ���...");}
		
      //����ִ������
	/*
	//�����ĵ�����ʼ
	public static void main(String args[]) throws IOException{
		HSSFWorkbook excel1=new HSSFWorkbook(new FileInputStream("C:/Users/Administrator/Desktop/�ൺ�б�׼������������к���Աȱ�_�������.xls"));
		HSSFSheet sheet0=excel1.getSheetAt(0);
		for(int i=2;i<=sheet0.getLastRowNum();i++){
			System.out.println("this excel has   " + sheet0.getLastRowNum()+"  rows and this is  "+i);
			HSSFRow myRow = sheet0.getRow(i);
			HSSFCell myCell3 = myRow.getCell(3);
			HSSFCell myCell2 = myRow.getCell(2);
			String content2 = CurrencyTools.judgeCell(myCell2);
			System.out.println(myCell2);
			if(content2.equals("��")){
				System.out.println("this is the first if");
				HSSFCell myCell4 = myRow.getCell(4);
				String content4 = CurrencyTools.judgeCell(myCell4);
				if(content4.equals("��")){
					System.out.println("this is the second if");
					HSSFCell myCell5 = myRow.getCell(5);
					String content5 = CurrencyTools.judgeCell(myCell5);
					if(content5.equals("��")){
						System.out.println("this is the third if");
						HSSFCell myCell6 = myRow.getCell(6);
						String content6 = CurrencyTools.judgeCell(myCell6);
						if(content6.equals("��")){
							System.out.println("this is the fouth if");
							HSSFCell myCell7 = myRow.getCell(7);
							String content7 = CurrencyTools.judgeCell(myCell7);
							if(content7.equals("��")){
								System.out.println("this is the fiveth if");
								HSSFCell myCell8 = myRow.getCell(8);
								String content8 = CurrencyTools.judgeCell(myCell8);
								if(content8.equals("��")){
									System.out.println("this is the sixth if");
									HSSFCell myCell9 = myRow.getCell(9);
									String content9 = CurrencyTools.judgeCell(myCell9);
									if(content9.equals("��")){
										System.out.println("this is the seventh if");
										HSSFCell myCell10 = myRow.getCell(10);
										String content10 = CurrencyTools.judgeCell(myCell10);
										if(content10.equals("��")){
											System.out.println("this is the eighth if");
											HSSFCell myCell11 = myRow.getCell(11);
											String content11 = CurrencyTools.judgeCell(myCell11);
											if(content11.equals("��")){
												System.out.println("this is the eighth if");
												HSSFCell myCell12 = myRow.getCell(12);
												String content12 = CurrencyTools.judgeCell(myCell12);
												if(content12.equals("��")){
													System.out.println("this is the ninth if");
													HSSFCell myCell13 = myRow.getCell(13);
													String content13 = CurrencyTools.judgeCell(myCell13);
													if(content13.equals("��")){
														System.out.println("this is the tenth if");
														HSSFCell myCell14 = myRow.getCell(14);
														String content14 = CurrencyTools.judgeCell(myCell14);
														if(content14.equals("��")){
															myCell2.setCellValue("�����ؾ��޴�����");
															myCell3.setCellValue("��");
														}
														else{
															myCell2.setCellValue(content14);
															myCell3.setCellValue("������");
														}
													}
													else{
														myCell2.setCellValue(content13);
														myCell3.setCellValue("370285000000");
													}
												}
												else{
													myCell2.setCellValue(content12);
													myCell3.setCellValue("370283000000");
												}
											}
											else{
												myCell2.setCellValue(content11);
												myCell3.setCellValue("370282000000");
											}
										}
										else{
											myCell2.setCellValue(content10);
											myCell3.setCellValue("370211000000");
										}
									}
									else{
										myCell2.setCellValue(content9);
										myCell3.setCellValue("370214000000");
									}
								}
								else{
									myCell2.setCellValue(content8);
									myCell3.setCellValue("370212000000");
								}
							}
							else{
								myCell2.setCellValue(content7);
								myCell3.setCellValue("370281000000");
							}
							
						}
						else{
							myCell2.setCellValue(content6);
							myCell3.setCellValue("370213000000");
						}
					}
					else{
						myCell2.setCellValue(content5);
						myCell3.setCellValue("370203000000");
					}
				}
				else{
					System.out.println("this is the second else");
					myCell2.setCellValue(content4);
					myCell3.setCellValue("370202000000");
				}
			}
			else{
				System.out.println("this is the first else");
				myCell3.setCellValue("370200000000");
			}
			i=i+2;
		}
		excel1.write(new FileOutputStream("C:/Users/Administrator/Desktop/�ൺ�б�׼������������к���Աȱ�_�������.xls"));
	}
    */
	//�����ĵ��������
	/*
	public static void main(String args[]) throws FileNotFoundException, IOException{
		HSSFWorkbook excel1=new HSSFWorkbook(new FileInputStream("C:/Users/Administrator/Desktop/111.xls"));
		for(int i=1;i<excel1.getNumberOfSheets();i++){
			CurrencyTools.CombineTwoSheetHssf(excel1.getSheetAt(0), excel1.getSheetAt(1));
		}
		HSSFWorkbook excel1=new HSSFWorkbook(new FileInputStream("C:/Users/Administrator/Desktop/11 - ����.xls"));
		HSSFSheet sheet0=excel1.getSheetAt(0);
		HSSFRow row1=sheet0.getRow(1);
		HSSFCell cell3=row1.getCell(3);
		StringBuilder PXDW=new StringBuilder(CurrencyTools.judgeCell(row1.getCell(0)));
		PXDW.delete(0,9);
		PXDW.delete(PXDW.length()-55,PXDW.length());
		System.out.println(PXDW.toString());
		System.out.println("ִ�����");
		for(int j=0;j<=sheet0.getLastRowNum();j++){
		    HSSFRow rowi = sheet0.getRow(j);
			//int cellnum=row1.getLastCellNum()+1;
			rowi.createCell((short)16).setCellValue(PXDW.toString());
			System.out.println("��  "+j+"+1�������˲���ɹ�");
		}
		HSSFRow row3 = sheet0.getRow(3);
		row3.getCell(row3.getLastCellNum()-1).setCellValue("������");
       // HSSFRow rown = sheet0.getRow(sheet0.getLastRowNum()-1); 
       // HSSFRow rownn = sheet0.getRow(sheet0.getLastRowNum()-2);
       // sheet0.removeRow(rown); 
       // sheet0.removeRow(rownn);
		sheet0.shiftRows(sheet0.getLastRowNum()-2,sheet0.getLastRowNum()-1,2);
		excel1.write(new FileOutputStream("C:/Users/Administrator/Desktop/11 - ����.xls"));
		System.out.println(sheet0.getLastRowNum());
	}
	*/
	}
	}

