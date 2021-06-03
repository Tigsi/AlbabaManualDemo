package wss.alibaba.demo.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangsisi
 * @email 1874174561@qq.com
 * @date 2021/6/3 10:15
 */

public class L6R13 {
    public static void main(String[] args) {
        List<String> generics = null;
        List notGenerics = new ArrayList(10);
        notGenerics.add(new Object());
        notGenerics.add(new Integer(1));
        generics = notGenerics;
        if (generics.get(0) instanceof String) {
            String string = generics.get(0);
        }
    }
}

/*
【强制】在无泛型限制定义的集合赋值给泛型限制的集合时，在使用集合元素时，需要进行
instanceof 判断，避免抛出 ClassCastException 异常。
说明：毕竟泛型是在 JDK5 后才出现，考虑到向前兼容，编译器是允许非泛型集合与泛型集合互相赋值。

List<String> generics = null;
List notGenerics = new ArrayList(10);
notGenerics.add(new Object());
notGenerics.add(new Integer(1));
generics = notGenerics;
// 此处抛出 ClassCastException 异常
String string = generics.get(0);
 */
