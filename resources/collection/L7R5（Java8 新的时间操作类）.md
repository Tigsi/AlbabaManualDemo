### 5. 【强制】SimpleDateFormat 是线程不安全的类，一般不要定义为 static 变量，如果定义为 static，必须加锁，或者使用 DateUtils 工具类。

正例：注意线程安全，使用 DateUtils。亦推荐如下处理：
```java
private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>(){
    @Override
    protected DateFormat initialValue(){
            return new SimpleDateFormat("yyyy-MM-dd");
    }
};

```
> 说明：如果是 JDK8 的应用，
> - 可以使用 Instant 代替 Date
> - LocalDateTime 代替 Calendar
> - DateTimeFormatter 代替 SimpleDateFormat
> 
> 官方给出的解释：simple beautiful strong immutable thread-safe。

示例：
```java
public static void main(String[] args) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
    Instant now = Instant.now();
    ZonedDateTime zonedDateTime = now.atZone(ZoneId.systemDefault());
    String format = zonedDateTime.format(dateTimeFormatter);
    System.out.println(format); // 2021-06-22 05:47:14
}
```