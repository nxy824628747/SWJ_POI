package print;
import javax.print.DocFlavor;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;


public class VPrinter {
    /**
     * @param args
     */
    public static void main(String[] args) {
        //声明JPD虚拟打印机
        try {
            Class.forName("com.hg.jpd.JpdPrintService");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        // 构建打印请求属性集
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        // 设置打印格式
        DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
        // 查找所有的可用打印服务
        PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
        // 定位默认的打印服务
        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
        if (defaultService != null) {
            PrintService service = ServiceUI.printDialog(null, 200, 200,
                    printService, defaultService, flavor, pras);
            if (service != null) {
                try {
                    service.createPrintJob().print(new SimpleDoc(new PrintDemo(), flavor, null), pras);
                } catch (PrintException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

