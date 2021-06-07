package wss.alibaba.demo.collection;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangsisi
 * @email 1874174561@qq.com
 * @date 2021/6/7 15:30
 */

public class L6R21 {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("1");
        strings.add("2");
        strings.add("3");

        // 去重比较有效简洁的方法
        List<String> distinctStrings1 = strings.stream().distinct().collect(Collectors.toList());
        List<String> distinctStrings2 = new ArrayList<>(new LinkedHashSet<>(strings));
        System.out.println(distinctStrings1);
        System.out.println(distinctStrings2);

        // 其他：利用双重for循环、contains方法、HashSet+for循环等
    }

}

/*
21.【参考】利用 Set 元素唯一的特性，可以快速对一个集合进行去重操作，
避免使用 List 的 contains() 进行遍历去重或者判断包含操作。
 */