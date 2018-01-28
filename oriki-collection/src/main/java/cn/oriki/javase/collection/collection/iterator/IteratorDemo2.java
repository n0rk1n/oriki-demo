package cn.oriki.javase.collection.collection.iterator;

import java.util.ArrayList;
import java.util.Collection;

public class IteratorDemo2 {

    public static void main(String[] args) {
        Collection<String> collection = new ArrayList<>();

        for (String object : collection) {
            System.out.println(object);
        }
    }
}
