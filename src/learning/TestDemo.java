package learning;

public class TestDemo {
    Inner inner=null;
    Thread thread = new Thread(
            ()->{
                //inner未被初始化情况不考虑
                if(inner==null){return;}
                //inner 初始化，但构造函数未执行完
                System.out.println(inner.getName());
            }
    );
    Thread initThread=new Thread(

    );

    public static void main(String[] args){

    }


    class Inner{
        private String name;
        private String age;
        Inner(){
            this.age="18";
            this.name="innerClassName";
        }
        public String getName(){
            return this.name;
        }
    }

    public static class Leetcode1048 {
        public int longestStrChain(String[] words) {
            return 0;
        }

        private boolean isStr(String str1, String str2) {
            if (Math.abs(str1.length() - str2.length()) > 1) {
                return false;
            }
            if (str1.length() < str2.length()) {
                String temp = str1;
                str1 = str2;
                str2 = temp;
            }
            boolean isChanged = false;
            int point1 = 0;
            int point2 = 0;
            while (point2 < str2.length()) {
                if (str1.charAt(point1) != str2.charAt(point2)) {
                    if (isChanged) {
                        return false;
                    }
                    isChanged = true;
                    point1++;
                    continue;
                }
                point1++;
                point2++;
            }
            return true;
        }
    }
}
