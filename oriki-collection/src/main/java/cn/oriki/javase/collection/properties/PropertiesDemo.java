package cn.oriki.javase.collection.properties;

import java.util.Properties;
import java.util.Set;

public class PropertiesDemo {

    public static void main(String[] args) {
        Properties properties = new Properties();

        properties.put("key1", "value1");// 存值
        properties.put("key2", "value2");

        System.out.println(properties);// {key2=value2, key1=value1}

        Set<Object> keySet = properties.keySet();

        for (Object object : keySet) {
            System.out.println((String) object);
        }
        // key1
        // key2
    }

}
