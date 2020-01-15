package learning;

import com.oscar.jdbc.Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMain {
    public static void main(String args[]) {
//        Car bus=new Bus();
//        System.out.println(bus.weight);
//        System.out.println(bus.name);
        int[] ints = new int[]{-1,0,1,2,-1,-4};
        List<List<Integer>> result = threeSum(ints);
        System.out.println(result.toString());

    }


//    寻找三数相加和为0
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        Map<Integer, int[]> m = new HashMap<Integer, int[]>();
        int length=nums.length;
        for(int i=0;i<length;i++){
            for(int j=i+1;j<length;j++){
                int numi=nums[i];
                int numj=nums[j];
                m.put(numi+numj,new int[]{numi,numj});
            }
        }
        for(int i=0;i<length;i++){
            int target=-nums[i];
            if(m.get(target)!=null&&m.get(target).length>0){
                List<Integer> innerList=new ArrayList<Integer>();
                int[] targetArr=m.get(target);
                int arr0=targetArr[0];
                int arr1=targetArr[1];
                if(arr0<arr1){
                    if(i<arr0){
                        innerList.add(i);
                        innerList.add(arr0);
                        innerList.add(arr1);
                        resultList.add(innerList);
                    }else if(i>arr1){
                        innerList.add(arr0);
                        innerList.add(arr1);
                        innerList.add(i);
                        resultList.add(innerList);
                    }else{
                        innerList.add(arr0);
                        innerList.add(i);
                        innerList.add(arr1);
                        resultList.add(innerList);
                    }
                }else if(arr0>arr1){
                    if(i<arr1){
                        innerList.add(i);
                        innerList.add(arr1);
                        innerList.add(arr0);
                        resultList.add(innerList);
                    }else if(i>arr0){
                        innerList.add(arr1);
                        innerList.add(arr0);
                        innerList.add(i);
                        resultList.add(innerList);
                    }else{
                        innerList.add(arr1);
                        innerList.add(i);
                        innerList.add(arr0);
                        resultList.add(innerList);
                    }
                }
            }
        }
        return resultList;
    }

//  numsi+numsj=target 暴力求解，但由于需要去重所以不会引起hash冲突，使用链表提高效率
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> m=new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++){
            int numi=nums[i];
            int needNum=target-nums[i];
            if(m.get(needNum)==null){
                m.put(numi,i);
                continue;
            }
            int needIndex=m.get(needNum);
            if(i==needIndex){continue;}
            if(i<needIndex){return new int[]{i,needIndex};}
            return new int[]{needIndex,i};
        }
        return null;
    }



}

