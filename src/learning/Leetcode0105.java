package learning;

public class Leetcode0105 {
    public static void main(String[] args) {
        String a = "pale";
        String b = "ple";
        oneEditAway(a, b);
    }

    public static boolean oneEditAway(String first, String second) {
        int firstLen=first.length();
        int secondLen=second.length();
        //保证 first 更长
        if(firstLen<secondLen){
            return oneEditAway(second,first);
        }
        //长度差大于 1 ，不可能只相差一个字符
        if(firstLen-secondLen>1){
            return false;
        }
        for(int i=0;i<secondLen;i++){
            //长度相同则比较剩余元素，长度不同尝试在 second 插入字符
            if(first.charAt(i)!=second.charAt(i)){
                return first.substring(i+1).equals(second.substring(firstLen==secondLen?i:i+1));
            }
        }
        return true;
    }
}
