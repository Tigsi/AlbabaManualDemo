### 12.【强制】禁止使用构造方法 BigDecimal(double)的方式把 double 值转化为 BigDecimal 对象。
> 说明：BigDecimal(double)存在精度损失风险，在精确计算或值比较的场景中可能会导致业务逻辑异常。

如：BigDecimal g = new BigDecimal(0.1F); 实际的存储值为：0.10000000149

正例：优先推荐入参为 String 的构造方法，或使用 BigDecimal 的 valueOf 方法，此方法内部其实执行了
Double 的 toString，而 Double 的 toString 按 double 的实际能表达的精度对尾数进行了截断。
- BigDecimal recommend1 = new BigDecimal("0.1");
- BigDecimal recommend2 = BigDecimal.valueOf(0.1);