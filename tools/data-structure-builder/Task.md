#  task
### 1. 理清层级及服务间调用
### 2. 确定几个关键对象
2.1 DataStructureProxyFactory
生成 DataStructureProxyCreator
并调用 DataStructureProxyCreator 的newInstant()方法，获得代理类的实例
2.2 DataTreeStructureCarrier
承装代理类实体的载体，通过carrier的getResult方法获得结果
树状结构问题
 确定最上层的策略。
 1. null 值策略
 2. 字符串  empty值策略
 3. 数字  0值策略