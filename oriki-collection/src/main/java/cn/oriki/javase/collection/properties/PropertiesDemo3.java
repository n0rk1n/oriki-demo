package cn.oriki.javase.collection.properties;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesDemo3 {

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();

        properties.setProperty("key1", "value1");
        properties.setProperty("key1", "value11");// 故意制造重复数据进行测试
        properties.setProperty("key2", "value2");

        properties.store(new FileOutputStream(new File("test.properties")), "this is comments");
    }

}
