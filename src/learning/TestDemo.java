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
}
