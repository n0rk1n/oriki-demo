package cn.oriki.reflect.test;

import cn.oriki.reflect.demo.Car;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Date;

public class ReflectTest4 {
    private Class<?> clazz = null;

    @Before
    public void before() throws ClassNotFoundException {
        clazz = Class.forName("cn.oriki.reflect.demo.Car");
    }

    /**
     * 获取公开非静态成员变量
     *
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @Test
    public void test() throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException {
        Field field = clazz.getField("id");

        Car car = (Car) clazz.newInstance();

        // 赋值
        field.set(car, 1);

        // 取值
        System.out.println(car.getId());// 1
    }

    /**
     * 获取公开静态成员变量
     *
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Test
    public void test2()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Field field = clazz.getField("name");

        // 赋值
        field.set(null, "BMW");

        // 取值
        System.out.println(Car.name);// BMW
    }

    /**
     * 获取私有非静态成员变量
     *
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @Test
    public void test3() throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException {
        Field field = clazz.getDeclaredField("price");

        // 设置取消权限检查
        field.setAccessible(true);

        // 赋值
        Car car = (Car) clazz.newInstance();
        field.set(car, 1000.0);

        // 取值
        System.out.println(car.getPrice());// 1000.0
    }

    /**
     * 获取私有静态成员变量
     *
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    @Test
    public void test4()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Field field = clazz.getDeclaredField("createtime");

        // 设置取消权限检查
        field.setAccessible(true);

        // 赋值
        field.set(null, new Date());

        // 取值
        System.out.println(Car.getCreatetime());// Sat Oct 14 15:55:35 CST 2017
    }

    /**
     * 获取所有成员变量
     */
    @Test
    public void test5() {
        // 获取公开的成员变量
        Field[] fields = clazz.getFields();
        // 打印成员变量数量
        System.out.println(fields.length);// 2

        for (Field field : fields) {
            System.out.println(field.getName());
        }
        // id
        // name

        // 获取所有的成员变量
        Field[] declaredFields = clazz.getDeclaredFields();
        // 打印成员变量数量
        System.out.println(declaredFields.length);// 4

        for (Field field : declaredFields) {
            System.out.println(field.getName());
        }
        // id
        // name
        // price
        // createtime
    }

}
