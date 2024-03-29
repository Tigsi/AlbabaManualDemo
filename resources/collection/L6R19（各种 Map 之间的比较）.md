##### 【推荐】高度注意 Map 类集合 K/V 能不能存储 null 值的情况，如下表格：

|集合类| Key |Value| Super| 说明|
| --- | --- | --- | --- | --- |
| Hashtable | 不允许为 null | 不允许为 null | Dictionary | 线程安全 |
| ConcurrentHashMap | 不允许为 null | 不允许为 null | AbstractMap | 锁分段技术（JDK8:CAS）|
| TreeMap | 不允许为 null | 允许为 null | AbstractMap | 线程不安全 |
| HashMap| 允许为 null | 允许为 null | AbstractMap | 线程不安全 |

> 由于 HashMap 的干扰，很多人认为 ConcurrentHashMap 是可以置入 null 值，而事实上，存储 null 值时会抛出 NPE 异常