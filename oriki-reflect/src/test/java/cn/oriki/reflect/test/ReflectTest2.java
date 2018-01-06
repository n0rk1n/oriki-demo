package cn.oriki.reflect.test;

import cn.oriki.reflect.demo.Student;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectTest2 {
    private Class<?> clazz = null;

    @Before
    public void before() throws ClassNotFoundException {
        clazz = Class.forName("cn.oriki.reflect.demo.Student");
    }

    /**
     * 获取公开的无参构造方法
     *
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @Test
    public void test() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        Constructor<?> constructor = clazz.getConstructor();

        // 使用构造函数对象的newInstance()方法实例化对象
        Student student = (Student) constructor.newInstance();
        System.out.println(student);// cn.oriki.reflect.demo.Student@77a567e1
    }

    /**
     * 获取私有的有参构造方法
     *
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @Test
    public void test2() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        // 获取私有构造函数的方法
        Constructor<?> constructor = clazz.getDeclaredConstructor(Integer.class);

        /*
         * 反射到类中的私有的成员之后，不能直接使用，必须强制让JVM取消对权限的检查之后，才能使用setAccessible(boolean flag)
         * 将boolean设置为true，即可取消Java的权限检查
         */
        constructor.setAccessible(true);

        // 使用构造函数对象的newInstance()方法实例化对象
        Student student = (Student) constructor.newInstance(new Object[]{1});
        System.out.println(student);// cn.oriki.reflect.demo.Student@394e1a0f
    }

    /**
     * 获取公开的有参构造方法
     *
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @Test
    public void test3() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException

    {
        Constructor<?> constructor = clazz.getConstructor(String.class);

        // 使用构造函数对象的newInstance()方法实例化对象
        Student student = (Student) constructor.newInstance(new Object[]{"xiaoming"});
        System.out.println(student);// cn.oriki.reflect.demo.Student@77a567e1
    }

    /**
     * 获取所有构造方法
     */
    @Test
    public void test4() {
        // 获取公开的构造函数的方法
        Constructor<?>[] constructors = clazz.getConstructors();

        // 打印类中公开构造函数的个数
        System.out.println(constructors.length);// 3

        // 获取所有构造函数的方法（公开和私有的）
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();

        // 打印
        System.out.println(declaredConstructors.length);// 4
    }

}
