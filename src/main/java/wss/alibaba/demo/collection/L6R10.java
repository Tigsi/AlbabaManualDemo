package wss.alibaba.demo.collection;

import java.util.ArrayList;
import java.util.List;

public class L6R10 {
    public static void main(String[] args) {
        List<String> l1 = new ArrayList<>();
        l1.add("1");
        List<String> l2 = new ArrayList<>();
        l2.add("2");
        l1.addAll(l2);
//         l1.addAll(null); // 异常
        System.out.println(l1);

    }
}

/*
.【强制】在使用 Collection 接口任何实现类的 addAll()方法时，都要对输入的集合参数进行 NPE 判断。
说明：在 ArrayList#addAll 方法的第一行代码即 Object[] a = c.toArray(); 其中 c 为输入集合参数，如果为 null，则直接抛出异常

ArrayList#addAll 源码
public boolean addAll(Collection<? extends E> c) {
    Object[] a = c.toArray();
    int numNew = a.length;
    ensureCapacityInternal(size + numNew);  // Increments modCount
    System.arraycopy(a, 0, elementData, size, numNew);
    size += numNew;
    return numNew != 0;
}

 */