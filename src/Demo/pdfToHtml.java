package Demo;

public class pdfToHtml {

    public static void ma1in(String[] args){
        pdf2html("C:\\Users\\niuxinyu\\Desktop\\111.pdf","C:\\Users\\niuxinyu\\Desktop","222.html");
    }

    public static boolean pdf2html(String pdfFile,
                                   String destDir, String htmlFileName) {

        Runtime rt = Runtime.getRuntime();
        StringBuilder command = new StringBuilder();
        if (destDir != null && !"".equals(destDir.trim()))// 生成文件存放位置,需要替换文件路径中的空格
            command.append("--dest-dir ").append(destDir.replace(" ", "\" \""))
                    .append(" ");
        command.append("--optimize-text 1 ");// 尽量减少用于文本的HTML元素的数目 (default: 0)
        command.append("--zoom 1.4 ");
        command.append("--process-outline 0 ");// html中显示链接：0——false，1——true
        command.append("--font-format woff ");// 嵌入html中的字体后缀(default ttf)
        // ttf,otf,woff,svg
        command.append(pdfFile.replace(" ", "\" \"")).append(" ");// 需要替换文件路径中的空格
        if (htmlFileName != null && !"".equals(htmlFileName.trim())) {
            command.append(htmlFileName);
            if (htmlFileName.indexOf(".html") == -1)
                command.append(".html");
        }
        try {
            Process p = rt.exec(command.toString());
            int w = p.waitFor();
            int v = p.exitValue();
            if (w == 0 && v == 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
