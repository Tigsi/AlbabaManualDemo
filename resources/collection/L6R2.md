### 【强制】判断所有集合内部的元素是否为空，使用 isEmpty()方法，而不是 size()==0 的方式。
> 说明：在某些集合中，前者的时间复杂度为 O(1)，而且可读性更好。
```java
Map<String, Object> map = new HashMap<>(16);
if(map.isEmpty()) {
    System.out.println("no element in this map.");
}
```

### 性能考量
要纠正一个长期以来的误区：size()>0 一定比 isEmpt() 性能差。

ArrayList源码:
```java
public int size() {
    return size;
}
public boolean isEmpty() {
    return size == 0;
}
```
HashMap 源码：
```java
public boolean isEmpty() {
    return size == 0;
}
```

HashSet源码：
```java
public int size() {
    return map.size();
}
public boolean isEmpty() {
    return map.isEmpty();
}
```

ConcurrentHashMap 源码：
```java
public int size() {
    long n = sumCount();
    return ((n < 0L) ? 0 :
            (n > (long)Integer.MAX_VALUE) ? Integer.MAX_VALUE :
            (int)n);
}
public boolean isEmpty() {
    return sumCount() <= 0L; // ignore transient negative values
}
```

其次，有些时候确实它更快，如果你使用了ConcurrentLinkedQueue、NavigableMap、NavigableSet，看源码：

ConcurrentSkipListMap 源码：
```java
public int size() {
    long count = 0;
    for (Node<K,V> n = findFirst(); n != null; n = n.next) {
        if (n.getValidValue() != null)
            ++count;
    }
    return (count >= Integer.MAX_VALUE) ? Integer.MAX_VALUE : (int) count;
}
public boolean isEmpty() {
    return findFirst() == null;
}
```

> 综上所述，isEmpty 的确是更好的选择，如果能用 isEmpty 就用。