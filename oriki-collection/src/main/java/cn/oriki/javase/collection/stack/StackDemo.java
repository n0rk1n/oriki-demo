package cn.oriki.javase.collection.stack;

import java.util.Stack;

/**
 * 栈集的演示
 */
public class StackDemo {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        // 压栈操作
        stack.push("1");

        // 弹栈操作,数据被弹出（如果栈为空，请不要调用）
        String pop = stack.pop();
        System.out.println(pop);// 1

        stack.push("1");
        // peek操作返回栈顶元素，但不弹出（如果栈为空，请不要调用）
        String peek = stack.peek();
        System.out.println(peek);// 1

        boolean isEmpty = stack.empty();
        System.out.println(isEmpty);// false
    }
}
