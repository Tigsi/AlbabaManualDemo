### 14.【强制】定义 DO/DTO/VO 等 POJO 类时，不要设定任何属性默认值。
反例：POJO 类的 createTime 默认值为 new Date()，但是这个属性在数据提取时并没有置入具体值，在 更新其它字段时又附带更新了此字段，导致创建时间被修改成当前时间。

### 15.【强制】序列化类新增属性时，请不要修改 serialVersionUID 字段，避免反序列失败；如果完全不兼容升级，避免反序列化混乱，那么请修改 serialVersionUID 值。
> 说明：注意 serialVersionUID 不一致会抛出序列化运行时异常。

### 16.【强制】构造方法里面禁止加入任何 业务逻辑，如果有初始化逻辑，请放在 init 方法中。

### 17.【强制】POJO 类必须写 toString 方法。使用 IDE 中的工具：source> generate toString、时，如果继承了另一个 POJO 类，注意在前面加一下 super.toString。
> 说明：在方法执行抛出异常时，可以直接调用 POJO 的 toString()方法打印其属性值，便于排查问题。

### 18.【强制】禁止在 POJO 类中，同时存在对应属性 xxx 的 isXxx()和 getXxx()方法。
> 说明：框架在调用属性 xxx 的提取方法时，并不能确定哪个方法一定是被优先调用到的。