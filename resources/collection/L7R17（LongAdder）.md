### 17.【参考】volatile 解决多线程内存不可见问题。对于一写多读，是可以解决变量同步问题，但是如果多写，同样无法解决线程安全问题。
> 说明：如果是 count++操作，使用如下类实现：
```java
AtomicInteger count = new AtomicInteger();
count.addAndGet(1);
```
> 如果是 JDK8，推荐使用 LongAdder 对象，比 AtomicLong 性能更好（减少乐观锁的重试次数）。

```java
LongAdder longAdder = new LongAdder();
longAdder.increment();
System.out.println(longAdder.sum());
```
LongAdder 详情了解：https://app.yinxiang.com/fx/fd045d34-9ab8-4bda-b337-e2587d250dc9