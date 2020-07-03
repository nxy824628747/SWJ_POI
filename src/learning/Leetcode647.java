package learning;

public class Leetcode647 {
    //结果计数
    int an = 0;

    /**
     * @Author Niuxy
     * @Date 2020/6/30 10:38 下午
     * @Description 外部循环，以每个元素开头的子串
     * 内部循环，以每个元素结尾的子串
     * 就可以遍历到所有长度大于 1 的可能的子串
     */
    public final int countSubstrings(String s) {
        int[][] cache = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                isSubstring(s, i, j, cache);
            }
        }
        return an + s.length();
    }

    public final boolean isSubstring(String s, int begin, int end, int[][] cache) {
        if (begin == end) {
            return true;
        }
        //缓存加去重
        if (cache[begin][end] != 0) {
            return cache[begin][end] == 1 ? true : false;
        }
        if (begin == end - 1) {
            boolean re = s.charAt(begin) == s.charAt(end);
            //边界计数
            an += re == true ? 1 : 0;
            //边界去重
            cache[begin][end] = re == true ? 1 : 2;
            return re;
        }
        if (s.charAt(begin) != s.charAt(end)) {
            cache[begin][end] = 2;
            return false;
        }
        boolean re = isSubstring(s, begin + 1, end - 1, cache);
        cache[begin][end] = re == true ? 1 : 2;
        //计数
        an += re == true ? 1 : 0;
        return re;
    }


    public final int countSubstringsDP(String s){
        if(s.length()==1){return 1;}
        int[][] dp=new int[s.length()][s.length()];
        int an=0;
        //初始化边界
        for(int i=0;i<s.length()-1;i++){
            dp[i][i]=1;
            an++;
            if(s.charAt(i)==s.charAt(i+1)){
                dp[i][i+1]=1;
                an++;
            }
        }
        // dp 开始
        for(int i=s.length()-1;i>=0;i--){
            for(int j=i+2;j<s.length();j++){
                if(s.charAt(i)==s.charAt(j)){
                    if(dp[i+1][j-1]==1){
                        dp[i][j]=1;
                        an++;
                    }
                }
            }
        }
        return an+1;
    }
}
