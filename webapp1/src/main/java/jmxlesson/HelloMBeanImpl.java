package jmxlesson;

public class HelloMBeanImpl implements HelloMBean {
    private int n;

    @Override
    public void sayHello(){
        System.out.println("hello!!!");
    }

    @Override
    public int getN() {
        return n;
    }

    @Override
    public void setN(int n) {
        this.n = n;
        System.out.println(this.n);
    }
}
