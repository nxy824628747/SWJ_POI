package learning.initTest;public class TestFather {    protected String book="book";    {        System.out.println("我是父类语句块");    }    static{        System.out.println("我是父类静态语句块");    }    TestFather(String s){        System.out.println("我是父类构造函数 "+s);    }}