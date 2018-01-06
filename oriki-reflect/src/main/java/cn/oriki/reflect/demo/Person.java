package cn.oriki.reflect.demo;

public class Person {

    public void method(Integer id) {
        System.out.println("执行了公开的非静态方法,参数为：" + id);
    }

    public static void method2() {
        System.out.println("执行了公开的静态方法");
    }

    private void method3() {
        System.out.println("执行了私有的非静态方法");
    }

    private static void method4() {
        System.out.println("执行了私有的静态方法");
    }
}
