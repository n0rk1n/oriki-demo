package cn.oriki.reflect.test;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTest3 {
    private Class<?> clazz = null;

    @Before
    public void before() throws ClassNotFoundException {
        clazz = Class.forName("cn.oriki.reflect.demo.Person");
    }

    /**
     * 获取公开的非静态方法，并运行
     *
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void test() throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, InstantiationException {
        Method method = clazz.getMethod("method", Integer.class);

        Object object = method.invoke(clazz.newInstance(), 1);// object为返回值 // 执行了公开的非静态方法，参数为：1
        System.out.println(object);// null
    }

    /**
     * 获取公开的静态方法，并运行
     *
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    @Test
    public void test2() throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, InstantiationException {
        Method method = clazz.getMethod("method2");

        @SuppressWarnings("all")
        Object object = method.invoke(null, null);// 执行了公开的静态方法

        System.out.println(object);// null

    }

    /**
     * 获取私有的非静态方法，并运行
     *
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    @Test
    public void test3() throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, InstantiationException {
        Method method = clazz.getDeclaredMethod("method3");

        // 设置取消权限校验
        method.setAccessible(true);

        Object object = method.invoke(clazz.newInstance());// 执行了私有的非静态方法
        System.out.println(object);// null
    }

    /**
     * 获取私有的静态方法，并运行
     *
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    @Test
    public void test4() throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, InstantiationException {
        Method method = clazz.getDeclaredMethod("method4");

        // 设置取消权限校验
        method.setAccessible(true);

        Object object = method.invoke(null);// 执行了私有的静态方法
        System.out.println(object);// null
    }

    /**
     * 获取所有方法
     *
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    @Test
    public void test5() throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, InstantiationException {
        // 获取所有公开的方法
        Method[] methods = clazz.getMethods();

        // 打印公开方法的数量
        System.out.println(methods.length);// 11

        for (Method method : methods) {
            System.out.println(method.getName());
        }
        // method2
        // method
        // wait
        // wait
        // wait
        // equals
        // toString
        // hashCode
        // getClass
        // notify
        // notifyAll

        // 获取所有的方法
        Method[] declaredMethods = clazz.getDeclaredMethods();

        // 打印方法的数量
        System.out.println(declaredMethods.length);// 4

        for (Method method : declaredMethods) {
            System.out.println(method.getName());
        }
        // method2
        // method3
        // method4
        // method
    }

}
