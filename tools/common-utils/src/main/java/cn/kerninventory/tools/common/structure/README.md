# 数据结构工具
## 1.1 设想
本意是想封装一些关于数据结构/图表的工具，例如不同规则下的排序，树状图，数据分组等等，
目前仅提供了生成树状结构的工具。
## 1.2 树状图
如下使用方法：

假设我们有Organisation表, id 主键， pid 表示上级, name表示名称, 该表自关联。

我们根据表创建对象OrganisationBO
```java
package com.onesport.common.utils.structure;
public class OrganisationBO {

    private Long id;

    private Long pid;

    private String name;
    
    //以下省略get set 和 构造器 方法

}

```

对于这类层级结构我们非常的熟悉，其他的还有例如菜单，大小类别等。 

通常我们需要在服务端封装成树状结构返回给客户端的browser

我们可以使用Sapling接口和Tree类实现这一功能

1.Bean 类 实现 Sapling接口并指定Key泛型。 

然后实现方法subNode和rootNode，这两个方法分别需要返回对象的id和上级id。
泛型的指定是非必须的，底层使用equal方法对上下级node进行匹配，如果你的Key字段类型是相同的基础类型，一般来说结果是一样的。
```java
package com.onesport.common.utils.structure;
public class OrganisationBO implements Sapling<Long>{

    private Long id;

    private Long pid;

    private String name;
    
    @Override
    public Long subNode() {
        return id;
    }

    @Override
    public Long rootNode() {
        return pid;
    }
}

```

2.调用的Tree类的静态方法of,

of方法有两组参数一组， 第一个Collection对象就是我们需要组成树状结构的数组对象本身，

第二个参数上有区别:

Sapling root  你可以直接传入一个对象来指定你的最底层

RootStrategy rootStrategy 或者通过底层策略来从第一个传入的Collection对象中寻找最底层，
RootStrategy是一个函数式接口，因此在代码书写上会是比较优雅且灵活的。


如下展示： 
```java
//模拟数据获取,仅演示
List<OrganisationBO> organisations = getOrgs();
Tree<OrganisationBO> tree = Tree.of(organisations, e -> e.rootNode() == null);

```
以上的Sapling 接口 和 Tree 类实现树状结构的演示。
## 1.3 可能的改进
1. Tree类中的递归实现时间复杂度：
假设Collection.size() = n ， 层级为X 那么时间复杂度就是
T(n)  ~ x^x * T(n)
处理较大数据量时可能在性能上稍差，需要加以改进 


## 1.4 关于Seed接口
Seed接口本身无意义，seed翻译是种子嘛，凡事有因才有果，先埋个种子，大家一起浇浇水呗。
