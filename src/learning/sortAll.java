package learning;import java.util.*;public class sortAll {    Set<List<Integer>> s=new HashSet<List<Integer>>();    public List<List<Integer>> permute(int[] nums) {        if(nums==null){            return null;        }        int length=nums.length;        List<Integer> tempList=new ArrayList<Integer>(length);        for(int j=0;j<length;j++){            tempList.add(nums[j]);        }        s.add(tempList);        for(int i=0;i<length-1;i++){            permute(nums,i);        }        List<List<Integer>> l=new LinkedList<List<Integer>>();        Stack<Integer> s=new Stack<Integer>();        s.pop();        s.get(s.size()-1);        if(s.lastElement()>1){        }        Iterator it=s.iterator();        while(it.hasNext()){            l.add((List<Integer>)it.next());        }        Map m=new HashMap();        return l;    }    public final void permute(int[] nums,int tar) {        int length=nums.length;        if(tar==length-1){            return;        }        List<Integer> l=new ArrayList<Integer>(length);        for(int i=tar+1;i<length;i++){            int temp=nums[tar];            nums[tar]=nums[i];            nums[i]=temp;            List<Integer> tempList=new ArrayList<Integer>(length);            for(int j=0;j<length;j++){                tempList.add(nums[j]);            }            s.add(tempList);            permute(nums,tar+1);            nums[i]=nums[tar];            nums[tar]=temp;        }    }    private final void  getStatic(String staticStr){    }        public static void main(String[] args){            int[] nums={1,2,3,4,5};            sortAll s=new sortAll();            s.permute(nums,0);            Set ss=s.s;            Iterator it=ss.iterator();            while(it.hasNext()){                System.out.println(it.next());            }        }}