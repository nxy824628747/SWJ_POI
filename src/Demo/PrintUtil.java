package Demo;

import javax.activation.MimetypesFileTypeMap;
import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrintQuality;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PrintUtil {
    public static void mai1n(String[] args) throws IOException{

//        new PrintUtil().draw("C:\\Users\\niuxinyu\\Desktop\\顶顶顶顶.pdf", 1);

        File f = new File("C:\\Users\\niuxinyu\\Desktop\\GB∕T 33190-2016 电子文件存储与交换格式.pdf");
        System.out.println("Mime Type of " + f.getName() + " is " +
                new MimetypesFileTypeMap().getContentType(f));
        MimetypesFileTypeMap m=new MimetypesFileTypeMap("C:\\Users\\niuxinyu\\Desktop\\GB∕T 33190-2016 电子文件存储与交换格式.pdf");
        m.addMimeTypes("application/pdf");
        File f2 = new File("C:\\Users\\niuxinyu\\Desktop\\GB∕T 33190-2016 电子文件存储与交换格式.pdf");
        System.out.println(m.getContentType(f2));
    }

    public static void draw(String fileName, int count) {
        InputStream fin = null;
        try {
            DocFlavor dof = DocFlavor.INPUT_STREAM.AUTOSENSE;
            PrintService ps = specifyPrinter("Microsoft Print to PDF");

            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            pras.add(OrientationRequested.LANDSCAPE);

            pras.add(new Copies(count));
            pras.add(PrintQuality.HIGH);
            DocAttributeSet das = new HashDocAttributeSet();

            // 设置打印纸张的大小（以毫米为单位）
            das.add(new MediaPrintableArea(0, 0, 210, 296, MediaPrintableArea.MM));
            fin = new FileInputStream(fileName);

            Doc doc = new SimpleDoc(fin, dof, das);

            DocPrintJob job = ps.createPrintJob();

            job.print(doc, pras);
            fin.close();
            System.out.print("打印成功！文件：" + fileName + "数量为：" + count);
        } catch (
                IOException ie) {
            ie.printStackTrace();
        } catch (
                PrintException pe) {
            pe.printStackTrace();
        } finally {
        }

    }

    public static PrintService specifyPrinter(String printerName) {
        DocFlavor psInFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        PrintService printService[] = PrintServiceLookup.lookupPrintServices(psInFormat, pras);
        PrintService myPrinter = null;
        // 遍历所有打印机如果没有选择打印机或找不到该打印机则调用默认打印机
        for (PrintService printService2 : printService) {
            String svcName = printService2.toString();
            if (svcName.contains(printerName)) {
                myPrinter = printService2;
                break;
            }
        }
        if (myPrinter == null) {
            myPrinter = PrintServiceLookup.lookupDefaultPrintService();
        }
        return myPrinter;
    }
}
