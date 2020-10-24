package learning;

import java.util.ArrayList;
import java.util.List;

public class Leetcode118 {
    public List<List<Integer>> generate(int numRows) {
        return null;
    }

    private void build(List<List<Integer>> re,int numRows,int point){

        if(point==0){
            List current=new ArrayList<Integer>(1);
            current.add(1);
            re.add(current);
            build(re,numRows,point+1);
            return;
        }
        List<Integer> pre = re.get(point-1);

    }
}
