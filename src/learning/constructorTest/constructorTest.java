package learning.constructorTest;

public class constructorTest {
    public static void main(String[] args) throws Exception{
        Class clazz=Inner.class;
        Inner inner=(Inner)clazz.newInstance();
        System.out.println(inner.id);
    }

    static class Inner{
        Integer id;
        Inner(Integer id){
            this.id=id;
        }
    }
}
