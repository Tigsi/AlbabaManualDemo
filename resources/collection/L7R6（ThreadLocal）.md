### 6. 【强制】必须回收自定义的 ThreadLocal 变量，尤其在线程池场景下，线程经常会被复用，如果不清理自定义的 ThreadLocal 变量，可能会影响后续业务逻辑和造成内存泄露等问题。尽量在代理中使用 try-finally 块进行回收。
正例：
```java
objectThreadLocal.set(userInfo);
try {
    // ...
} finally {
    objectThreadLocal.remove();
}
```
ThreadLocal 的 set 方法源码：
```java
public void set(T value) {
    Thread t = Thread.currentThread();
    ThreadLocalMap map = getMap(t);
    if (map != null)
        map.set(this, value);
    else
        createMap(t, value);
}
```
ThreadLocal 的 get 方法源码：
```java
public T get() {
    Thread t = Thread.currentThread();
    ThreadLocalMap map = getMap(t);
    if (map != null) {
        ThreadLocalMap.Entry e = map.getEntry(this);
        if (e != null) {
            @SuppressWarnings("unchecked")
            T result = (T)e.value;
            return result;
        }
    }
    return setInitialValue();
}
```

ThreadLocal 的 remove 方法：
```java
 public void remove() {
     ThreadLocalMap m = getMap(Thread.currentThread());
     if (m != null)
         m.remove(this);
 }
```

ThreadLocal 的 get 方法：
```java
ThreadLocalMap getMap(Thread t) {
    return t.threadLocals;
}
```

详细可参考:https://app.yinxiang.com/fx/2de8ba59-554d-456a-b9c4-59b85a29ce1f

### ThreadLocal为什么建议用static修饰
ThreadLocal 的原理是在 Thread 内部有一个 ThreadLocalMap 的集合对象，他的 key 是 ThreadLocal，value 就是你要存储的变量副本，不同的线程他的 ThreadLocalMap 是隔离开的，如果变量 ThreadLocal 是非 static 的就会造成每次生成实例都要生成不同的 ThreadLocal 对象，虽然这样程序不会有什么异常，但是会浪费内存资源。造成内存泄漏。 所以建议 ThreadLocal 用 static 修饰