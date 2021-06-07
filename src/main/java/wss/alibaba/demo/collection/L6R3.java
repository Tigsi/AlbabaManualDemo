package wss.alibaba.demo.collection;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wangsisi
 * @email 1874174561@qq.com
 * @date 2021/6/7 15:43
 */

public class L6R3{
    public static void main(String[] args) {
        List<Pair<String, Double>> pairArrayList = new ArrayList<>(3);
        pairArrayList.add(new Pair<>("version", 12.10));
        pairArrayList.add(new Pair<>("version", 12.19));
        pairArrayList.add(new Pair<>("version", 6.28));

        // Map<String, Double> pairMap = pairArrayList.stream().collect(Collectors.toMap(Pair::getKey, Pair::getValue, (c1, c2) -> c2));
        // 异常
        Map<String, Double> pairMap = pairArrayList.stream().collect(Collectors.toMap(Pair::getKey, Pair::getValue));
        System.out.println(pairMap);
    }
}

/*
【强制】在使用 java.util.stream.Collectors 类的 toMap()方法转为 Map 集合时，一定要使
用含有参数类型为 BinaryOperator，参数名为 mergeFunction 的方法，否则当出现相同 key
值时会抛出 IllegalStateException 异常。
说明：参数 mergeFunction 的作用是当出现 key 重复时，自定义对 value 的处理策略。
正例：
    List<Pair<String, Double>> pairArrayList = new ArrayList<>(3);
    pairArrayList.add(new Pair<>("version", 12.10));
    pairArrayList.add(new Pair<>("version", 12.19));
    pairArrayList.add(new Pair<>("version", 6.28));

Map<String, Double> map = pairArrayList.stream().collect(
// 生成的 map 集合中只有一个键值对：{version=6.28}
Collectors.toMap(Pair::getKey, Pair::getValue, (v1, v2) -> v2));
反例：
String[] departments = new String[] {"iERP", "iERP", "EIBU"};
// 抛出 IllegalStateException 异常
Map<Integer, String> map = Arrays.stream(departments)
 .collect(Collectors.toMap(String::hashCode, str -> str));
 */