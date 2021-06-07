package wss.alibaba.demo.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangsisi
 * @email 1874174561@qq.com
 * @date 2021/6/3 16:57
 */

public class L6R18 {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>(4);
        map.put("1", "one");
        map.put("2", "two");
        // 正解1
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        // 正解2
        map.forEach((k, v) -> System.out.println(k + " " + v));

    }
}
/*
【推荐】使用 entrySet 遍历 Map 类集合 KV，而不是 keySet 方式进行遍历。

说明：keySet 其实是遍历了 2 次，一次是转为 Iterator 对象，另一次是从 hashMap 中取出 key 所对应的value。
而 entrySet 只是遍历了一次就把 key 和 value 都放到了 entry 中，效率更高。
如果是 JDK8，使用Map.forEach 方法。

正例：
values()返回的是 V 值集合，是一个 list 集合对象；
keySet()返回的是 K 值集合，是一个 Set 集合对象；
entrySet()返回的是 K-V 值组合集合
 */