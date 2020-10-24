package Demo.utils;

import java.text.DecimalFormat;

public class MathUtils {
    //保留两位小数的除法
    public static Double txfloat(int a, int b) {
        DecimalFormat df = new DecimalFormat("0.00");//设置保留位数
        return Double.valueOf(df.format((float) a / b));
    }
}
