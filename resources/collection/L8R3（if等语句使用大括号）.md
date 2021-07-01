### 3. 【强制】在 if/else/for/while/do 语句中必须使用大括号。
   >说明：即使只有一行代码，也禁止不采用大括号的编码方式：if (condition) statements;
   >
4. 【强制】三目运算符 condition? 表达式 1 : 表达式 2 中，高度注意表达式 1 和 2 在类型对齐
   时，可能抛出因自动拆箱导致的 NPE 异常。
   说明：以下两种场景会触发类型对齐的拆箱操作：
   1） 表达式 1 或表达式 2 的值只要有一个是原始类型。
   2） 表达式 1 或表达式 2 的值的类型不一致，会强制拆箱升级成表示范围更大的那个类型。
   反例：
   Integer a = 1;
   Integer b = 2;
   Integer c = null;
   Boolean flag = false;
   // a*b 的结果是 int 类型，那么 c 会强制拆箱成 int 类型，抛出 NPE 异常
   Integer result=(flag? a*b : c);