### 【强制】获取当前毫秒数：

```java
System.currentTimeMillis(); // 正确
        new Date().getTime()。 // 错误
```

Date 源码：

```java
public Date(){
        this(System.currentTimeMillis());
        }
```

已经很明显了，new Date()所做的事情其实就是调用了System.currentTimeMillis()。如果仅仅是需要或者毫秒数，那么完全可以使用System.currentTimeMillis()去代替new Date()
，效率上会高一点。
> 说明：如果想获取更加精确的纳秒级时间值，使用 System.nanoTime 的方式。在 JDK8 中，针对统计时间 等场景，推荐使用 Instant 类。

### Instant 的应用

##### 获取时间戳

```java
Instant now=Instant.now();
System.out.println(now.getEpochSecond()); // 秒  1568568760
System.out.println(now.toEpochMilli()); // 毫秒  1568568760316
```

##### 获取 LocalDateTime

```java
Instant ins=Instant.ofEpochSecond(1623727238);    // 以指定时间戳创建Instant
ZonedDateTime zonedDateTime=ins.atZone(ZoneId.systemDefault());   // 获取 ZonedDateTime
LocalDateTime localDateTime=zonedDateTime.toLocalDateTime();      // 获取 LocalDateTime
```

##### 总结
Instant表示高精度时间戳，它可以和ZonedDateTime、LocalDateTime以及Long互相转换