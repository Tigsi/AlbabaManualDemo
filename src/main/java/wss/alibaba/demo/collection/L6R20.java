package wss.alibaba.demo.collection;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author wangsisi
 * @email 1874174561@qq.com
 * @date 2021/6/4 9:14
 */

public class L6R20 {
    public static void main(String[] args) {
        // 无序性
//        Map<String, String> map = new HashMap<>(16);
        Map<String, String> map = new TreeMap<>();
        map.put("10", "10");
        map.put("3", "3");
        map.put("2", "2");
        map.put("421", "421");
        map.put("24", "24");
        map.put("4334", "4334");
        map.put("433", "433");
        map.put("14", "14");
        map.put("9", "9");
        map.put("41", "41");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry);
        }
    }
}

/*

【参考】合理利用好集合的有序性(sort)和稳定性(order)，避免集合的无序性(unsort)和不稳定性(unorder)带来的负面影响。

说明：有序性是指遍历的结果是按某种比较规则依次排列的。稳定性指集合每次遍历的元素次序是一定的。
如：ArrayList 是 order/unsort；
HashMap 是 unorder/unsort；
TreeSet、TreeMap 是 order/sort。
 */
