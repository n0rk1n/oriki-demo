package cn.oriki.javase.collection.collection.iterator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class IteratorDemo {

    public static void main(String[] args) {
        Collection<String> collection = new ArrayList<>();
        Iterator<String> iterator = collection.iterator();

        while(iterator.hasNext()){
            String object = iterator.next();
            System.out.println(object);
        }
    }
}
