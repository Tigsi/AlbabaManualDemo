package wss.alibaba.demo.collection;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("11");
        strings.add("22");
        List<String> strings1 = strings.subList(0, 1);
        strings.add("33");

        strings1.add("44");
        System.out.println(strings1);
        System.out.println(strings);


    }
}
/*
5. 【强制】ArrayList 的 subList 结果不可强转成 ArrayList，否则会抛出 ClassCastException 异 常：java.util.RandomAccessSubList cannot be cast to java.util.ArrayList。
说明：subList()返回的是 ArrayList 的内部类 SubList，并不是 ArrayList 本身，而是 ArrayList 的一个视
图，对于 SubList 的所有操作最终会反映到原列表上。
6. 【强制】使用 Map 的方法 keySet()/values()/entrySet()返回集合对象时，不可以对其进行添
加元素操作，否则会抛出 UnsupportedOperationException 异常。
7. 【强制】Collections 类返回的对象，如：emptyList()/singletonList()等都是 immutable list，
不可对其进行添加或者删除元素的操作。
反例：如果查询无结果，返回 Collections.emptyList()空集合对象，调用方一旦进行了添加元素的操作，就
会触发 UnsupportedOperationException 异常。
Java 开发手册
 14/59
8. 【强制】在 subList 场景中，高度注意对父集合元素的增加或删除，均会导致子列表的遍历、
增加、删除产生 ConcurrentModificationException 异常。
 */