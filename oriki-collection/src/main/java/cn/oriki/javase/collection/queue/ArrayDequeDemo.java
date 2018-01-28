package cn.oriki.javase.collection.queue;

import java.util.ArrayDeque;

public class ArrayDequeDemo {

    public static void main(String[] args) {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();

        arrayDeque.add("1");//默认向尾部添加

        String remove = arrayDeque.remove();//默认取出头部元素
        System.out.println(remove);
    }
}
