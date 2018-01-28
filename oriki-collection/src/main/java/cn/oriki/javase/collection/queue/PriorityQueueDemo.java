package cn.oriki.javase.collection.queue;

import java.util.PriorityQueue;

public class PriorityQueueDemo {
    public static void main(String[] args) {
        PriorityQueue<String> queue = new PriorityQueue<>();

        queue.add("1");
        queue.add("4");
        queue.add("3");
        queue.add("2");
        queue.add("5");

        String remove = queue.remove();
        System.out.println(remove);// 1

        String remove2 = queue.remove();
        System.out.println(remove2);// 2
    }
}
