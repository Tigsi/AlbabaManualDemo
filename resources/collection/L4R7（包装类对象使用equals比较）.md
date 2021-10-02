###【强制 ***】所有整型包装类对象之间值的比较，全部使用 equals 方法比较。
> 说明：对于 Integer var = ? 在-128 至 127 之间的赋值，Integer 对象是在 IntegerCache.cache 产生， 会复用已有对象，这个区间内的 Integer 值可以直接使用==进行判断，但是这个区间之外的所有数据，都 会在堆上产生，并不会复用已有对象，这是一个大坑，推荐使用 equals 方法进行判断。

测试类：
```java
public class Test {
    public static void main(String[] args) {
        Integer i = 127;
        Integer j = 127;
        Integer k = 128;
        Integer g = 128;
        System.out.println(i == j);   // true
        System.out.println(k == g);   // false
    }
}
```

Integer#IntegerCache 源码：

```java
/**
 * 存支持对象标识语义的自动装箱之间的值 (-128,127]
 * 缓存在第一次使用时被初始化。缓存的大小可以由 {@code -XX:AutoBoxCacheMax=<size>} 选项控制。
 * 在虚拟机初始化过程中，java.lang.Integer.IntegerCache.high
 * 属性可能会被设置并保存在sun.misc.VM类的私有系统属性中。
 */

private static class IntegerCache {
    static final int low = -128;
    static final int high;
    static final Integer[] cache;

    static {
        // high value may be configured by property
        int h = 127;
        String integerCacheHighPropValue =
                sun.misc.VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
        if (integerCacheHighPropValue != null) {
            try {
                int i = parseInt(integerCacheHighPropValue);
                i = Math.max(i, 127);
                // Maximum array size is Integer.MAX_VALUE
                h = Math.min(i, Integer.MAX_VALUE - (-low) - 1);
            } catch (NumberFormatException nfe) {
                // If the property cannot be parsed into an int, ignore it.
            }
        }
        high = h;

        cache = new Integer[(high - low) + 1];
        int j = low;
        for (int k = 0; k < cache.length; k++)
            cache[k] = new Integer(j++);

        // range [-128, 127] must be interned (JLS7 5.1.7)
        assert IntegerCache.high >= 127;
    }

    private IntegerCache() {
    }
}
```