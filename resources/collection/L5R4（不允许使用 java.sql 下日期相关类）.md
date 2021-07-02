### 【强制】不允许在程序任何地方中使用
- java.sql.Date 
- java.sql.Time。
- java.sql.Timestamp。

说明：
第 1 个不记录时间，getHours()抛出异常；

第 2 个不记录日期，getYear()抛出异常；

第 3 个在构造 方法 super((time/1000)*1000)，在 Timestamp 属性 fastTime 和 nanos 分别存储秒和纳秒信息。

反例： 
java.util.Date.after(Date)进行时间比较时，当入参是 java.sql.Timestamp 时，会触发 JDK BUG(JDK9 已修复)，可能导致比较时的意外结果。