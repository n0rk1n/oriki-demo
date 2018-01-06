package cn.oriki.reflect.demo;

import java.util.Date;

public class Car {

    public Integer id;
    public static String name;
    private Double price;
    private static Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Car.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public static Date getCreatetime() {
        return createtime;
    }

    public static void setCreatetime(Date createtime) {
        Car.createtime = createtime;
    }

}
