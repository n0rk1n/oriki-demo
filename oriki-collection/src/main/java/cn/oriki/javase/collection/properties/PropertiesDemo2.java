package cn.oriki.javase.collection.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesDemo2 {

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();

        properties.load(new FileInputStream(new File("test.properties")));

        Set<Map.Entry<Object, Object>> entrySet = properties.entrySet();

        for (Map.Entry<Object, Object> entry : entrySet) {
            System.out.println(entry);
        }
        // key2=value2
        // key1=value1
    }

}
