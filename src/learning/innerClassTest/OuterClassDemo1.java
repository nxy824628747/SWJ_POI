package learning.innerClassTest;import javax.swing.plaf.synth.SynthLabelUI;public class OuterClassDemo1 {    String sss = "aaa";    double a = 1.22321;    private static Object o0 = new Object();    private  void test() {        Object o = new Object();        class OuterClassDemo2 extends OuterClassDemo1{            Object oi;            private OuterClassDemo2 init(Object object){                this.oi=object;                return this;            }        }        OuterClassDemo2 demo2=new OuterClassDemo2().init(o);    }}