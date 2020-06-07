package learning.lru;

public class Demo {
    public  static void main(String[] args){
//        LRUCache cache=new LRUCache(2);
//        System.out.println(cache.get(0));
//        cache.put(1,1); //1
//        cache.put(2,2); // 1,2
//        System.out.println(cache.get(1));
//        cache.put(3,3); //3,1
//        System.out.println(cache.get(2));
//        cache.put(4,4); //3,4
//        System.out.println(cache.get(1));
//        System.out.println(cache.get(3));
//        System.out.println(cache.get(4));

        LRUCache cache=new LRUCache(1);
        cache.put(2,1);
        System.out.println(cache.get(2));
    }
}
