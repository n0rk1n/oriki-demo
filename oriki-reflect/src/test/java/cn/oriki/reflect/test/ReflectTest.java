package cn.oriki.reflect.test;

import cn.oriki.reflect.demo.Demo;
import org.junit.Test;

public class ReflectTest {

    @Test
    public void test() {
        Class<? extends String> clazz1 = new String().getClass();
        System.out.println(clazz1);// class java.lang.String

        Class<? extends Demo> clazz2 = new Demo().getClass();
        System.out.println(clazz2);// class cn.oriki.reflect.demo.Demo

        Class<? extends int[]> clazz3 = new int[0].getClass();
        System.out.println(clazz3);// class [I
    }

    @Test
    public void test2() {
        Class<String> clazz1 = String.class;
        System.out.println(clazz1);// class java.lang.String

        System.out.println(Demo.class);// class cn.oriki.reflect.demo.Demo

        System.out.println(Object.class);// class java.lang.Object

        System.out.println(int.class);// int

        System.out.println(void.class);// void

        System.out.println(Class.class);// class java.lang.Class

    }

    @Test
    public void test3() throws ClassNotFoundException {
        Class<?> clazz1 = Class.forName("java.lang.String");
        System.out.println(clazz1);// class java.lang.String

        Class<?> clazz2 = Class.forName("cn.oriki.reflect.demo.Demo");
        System.out.println(clazz2);// class cn.oriki.reflect.demo.Demo
    }

    @Test
    public void test4() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName("cn.oriki.reflect.demo.Demo");
        Demo demo = (Demo) clazz.newInstance();

        System.out.println(demo);// cn.oriki.reflect.demo.Demo@bebdb06
    }

}
