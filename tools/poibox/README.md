# POIBox 
### POIBox 对象解析
每一个POIBox持有了一个Excel工作簿对象，所有的操作都是在一个Workbook内。如果需要操作多个Workbook，需要重新进行实例化。当使用reset()方法时，将影响任何在该POIBox对象内所有未完成的操作。

1. styler  

风格工具， 对POI的CellStyle接口的具体实现，通过连续赋值写法简便了构造一个风格的过程，同时提供了一些企业业务中常用的风格。每一个Styler持有一个CellStyle风格对象。
2. Fonter 

字体工具， 对POI的Font接口的具体实现，同样通过连续赋值写法简便了构造一个字体的过程。每一个Fonter持有一个Font对象。
3. layout 

布局工具， 提供了单元格合并、单元格高度及宽度自动/手动设定的一些接口，对POI基础接口而言，是对一些接口进行合并使用以更符合企业业务中Excel操作，以此带来便利性。
4. DataBox

数据处理对象，每一个DataBox持有一个Sheet对象，这一点需要非常明确，参考Excel的Sheet页可以理解这一点。提供了针对于Sheet页的模板绘制，数据获取，数据映射简单Java对象等功能，基于Java注解配置的形式。
### POIData 配置讲解
    
    