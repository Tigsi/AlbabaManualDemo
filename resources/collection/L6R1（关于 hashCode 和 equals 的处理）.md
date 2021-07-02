### 【强制】关于 hashCode 和 equals 的处理，遵循如下规则：

- 只要覆写 equals，就必须覆写 hashCode。
- 因为 Set 存储的是不重复的对象，依据 hashCode 和 equals 进行判断，所以 Set 存储的对象必须覆写这两种方法。
- 如果自定义对象作为 Map 的键，那么必须覆写 hashCode 和 equals。

> 说明：String 因为覆写了 hashCode 和 equals 方法，所以可以愉快地将 String 对象作为 key 来使用。