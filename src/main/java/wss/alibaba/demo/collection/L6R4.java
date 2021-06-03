package wss.alibaba.demo.collection;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wangsisi
 * @email 1874174561@qq.com
 * @date 2021/6/3 15:01
 */

public class L6R4 {
    public static void main(String[] args) {
        List<Pair<String, Double>> pairList = new ArrayList<>(2);
        pairList.add(new Pair<>("version1", 8.3));
        pairList.add(new Pair<>("version2", null));
        Map<String, Double> stringDoubleMap = pairList.stream().collect(Collectors.toMap(Pair::getKey, Pair::getValue, (v1, v2) -> v2));
        System.out.println(stringDoubleMap);
    }

}

/*
【强制】在使用 java.util.stream.Collectors 类的 toMap()方法转为 Map 集合时，一定要注意当 value 为 null 时会抛 NPE 异常。

说明：在 java.util.HashMap 的 merge 方法里会进行如下的判断：
if (value == null || remappingFunction == null)
throw new NullPointerException();

反例：
List<Pair<String, Double>> pairArrayList = new ArrayList<>(2);
pairArrayList.add(new Pair<>("version1", 8.3));
pairArrayList.add(new Pair<>("version2", null));

// 抛出 NullPointerException 异常
Map<String, Double> stringDoubleMap = pairArrayList.stream().collect(Collectors.toMap(Pair::getKey, Pair::getValue, (v1, v2) -> v2));
 */
