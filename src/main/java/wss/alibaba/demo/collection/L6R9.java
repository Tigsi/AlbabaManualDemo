package wss.alibaba.demo.collection;

import java.util.*;

/**
 * @author wangsisi
 * @email 1874174561@qq.com
 * @date 2021/6/8 9:33
 */
public class L6R9 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(2);
        list.add("guan");
        list.add("bao");
        // 只能返回 Object[]
        // Object[] objects = list.toArray();
        String[] array = list.toArray(new String[10]);
        System.out.println(Arrays.toString(array));
    }
}
/*
【强制】使用集合转数组的方法，必须使用集合的 toArray(T[] array)，传入的是类型完全一致、长度为 0 的空数组。

反例：
直接使用 toArray 无参方法存在问题，此方法返回值只能是 Object[] 类，若强转其它类型数组将出现
ClassCastException 错误。

正例：
List<String> list = new ArrayList<>(2);
list.add("guan");
list.add("bao");
String[] array = list.toArray(new String[0]);

说明：
使用 toArray 带参方法，数组空间大小的 length：
1） 等于 0，动态创建与 size 相同的数组，性能最好。
2） 大于 0 但小于 size，重新创建大小等于 size 的数组，增加 GC 负担。
    解释：如果传入的数组大小小于集合元素的数目，复制前会创建大小合适的新数组，原来的空数组就没用了，空数组的回收会增加 gc 的负担。
3） 等于 size，在高并发情况下，数组创建完成之后，size 正在变大的情况下，负面影响与 2 相同。
    解释：size 正在变大，说明 len 会小于 size 情况同 2。
4） 大于 size，空间浪费，且在 size 处插入 null 值，存在 NPE 隐患。
 */