### ****集合泛型定义时，在 JDK7 及以上，使用 diamond 语法或全省略。
> 说明：菱形泛型，即 diamond，直接使用<>来指代前边已经指定的类型。
正例：
```java
// diamond 方式，即<>
HashMap<String, String> userCache = new HashMap<>(16);
// 全省略方式
ArrayList<User> users = new ArrayList(10);
```