package Demo.utils;

public class LicenseStringUtils extends StringCompareUtil {
    static final String[] redundances = new String[]{"中华人民共和国", "中国", "证书", "证照", "书", "审批表",
            "申请表", "表", "(", ")", "（", "）", "批准", "登记", "书", "经营", "许可证"};

    static public boolean isSame(String str0, String str1) {
        return StringCompareUtil.isSame(str0, str1, 0.75, redundances);
    }
}
