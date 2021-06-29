### 12.【强制】多线程并行处理定时任务时，Timer 运行多个 TimeTask 时，只要其中之一没有捕获抛出的异常，其它任务便会自动终止运行，使用 ScheduledExecutorService 则没有这个问题。

Timer 有一下三个缺陷：
1. Timer的任务出现异常可能导致其他任务不被执行
2. 执行耗时的TimerTask会影响其它TimerTask的执行
3. Timer的执行时基于系统的绝对时间的

以上三个问题在 ScheduledExecutorService 中都得以解决。

详情参考：https://app.yinxiang.com/fx/90a1a4ed-49b1-46db-ac6e-5b4fd20d5e30