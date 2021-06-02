package wss.alibaba.demo.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author wangsisi
 * @email 1874174561@qq.com
 * @date 2021/6/2 17:14
 */

public class L6R14 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

//        for (String item : list) {
//            if ("2".equals(item)) {
//                list.remove(item);
//            }
//        }

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if ("1".equals(item)) {
                iterator.remove();
            }
        }
        System.out.println(list);
    }
}