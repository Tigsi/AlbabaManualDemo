package wss.alibaba.demo.collection;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wangsisi
 * @email 1874174561@qq.com
 * @date 2021/7/1 17:18
 */

public class Test {

    public static void main(String[] args) {
        List<Pair<String, Double>> pairArrayList = new ArrayList<>(2);
        pairArrayList.add(new Pair<>("version1", 8.3));
        pairArrayList.add(new Pair<>(null, 11.1));
        Map<String, Double> map = pairArrayList.stream().collect(
// 抛出 NullPointerException 异常
                Collectors.toMap(Pair::getKey, Pair::getValue, (v1, v2) -> v2));
        System.out.println(map);
    }
}

/*
 0 new #2 <wss/alibaba/demo/collection/Test>
 3 dup
 4 invokespecial #3 <wss/alibaba/demo/collection/Test.<init>>
 7 astore_1
 8 aload_1
 9 invokevirtual #4 <wss/alibaba/demo/collection/Test.test2>
12 aload_1
13 pop
14 invokestatic #5 <wss/alibaba/demo/collection/Test.test>
17 return




 0 new #2 <wss/alibaba/demo/collection/Test>
 3 dup
 4 invokespecial #3 <wss/alibaba/demo/collection/Test.<init>>
 7 astore_1
 8 aload_1
 9 invokevirtual #4 <wss/alibaba/demo/collection/Test.test2>
12 invokestatic #5 <wss/alibaba/demo/collection/Test.test>
15 return


 */