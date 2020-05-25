package Demo;

import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.print.PrintService;

import org.apache.pdfbox.pdmodel.PDDocument;

public class ServicePrint {
    public static void mai1n(String[] args) throws Exception {
        print();
    }


    public static void print() throws Exception {
        PDDocument document = null;
        try {
            System.out.println("start print------------>");
            String inputFile = "C:/Users/niuxinyu/Desktop/原pdf签章.pdf";//文件

            String printerName = "ImagePrinter Pro";//打印机名包含字串

            document = PDDocument.load(new File(inputFile));

            PrinterJob printJob = PrinterJob.getPrinterJob();
            printJob.setJobName(new File(inputFile).getName());

            if (printerName != null) {
                PrintService[] printService = PrinterJob.lookupPrintServices();//获得本台电脑连接的所有打印机
                for (int i = 0; i < printService.length; i++) {
                    System.out.println(printService[i].getName());
                    if (printService[i].getName().contains(printerName)) {
                        System.out.println();
                        System.out.println(printService[i].getName() + "--------------------------------------");
                        System.out.println();
                        printJob.setPrintService(printService[i]);
                        break;
                    }
                }
            }
            printJob.print();
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}